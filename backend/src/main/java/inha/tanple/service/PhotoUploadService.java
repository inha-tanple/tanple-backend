package inha.tanple.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import inha.tanple.domain.Member;
import inha.tanple.domain.PhotoUpload;
import inha.tanple.domain.PhotoUploadStatus;
import inha.tanple.domain.Product;
import inha.tanple.exception.ResourceNotFoundException;
import inha.tanple.repository.PhotoUploadRepository;
import inha.tanple.repository.ProductRepository;
import inha.tanple.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotoUploadService {

    private final PhotoUploadRepository photoUploadRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public void saveFile(MultipartFile file, Long barcode, long userId) throws ResourceNotFoundException, IOException {

        // load entity
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Product product = productRepository.findById(barcode)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + barcode));
        LocalDateTime now = LocalDateTime.now();


        // put on S3
        String timestamp = now.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        String originalFilename = file.getOriginalFilename();
        int lastIndex = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(lastIndex);
        String fileName = "tanple/photos/" + userId + "/" + timestamp + "_" + barcode + ext;
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());

        amazonS3Client.putObject(
                new PutObjectRequest(bucket, fileName, file.getInputStream(), metadata)
                        .withCannedAcl(CannedAccessControlList.Private)
        );


        // log on database
        PhotoUpload photoUpload = new PhotoUpload();
        photoUpload.setMember(member);
        photoUpload.setProduct(product);
        String url = amazonS3Client.getUrl(bucket, fileName).toString();
        photoUpload.setPhotoUrl(url);
        photoUpload.setPhotoUploadStatus(PhotoUploadStatus.PENDING);

        photoUploadRepository.save(photoUpload);
    }

    public List<PhotoUpload> getPhotoUploads(Long memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow();

        List<PhotoUpload> photoUploadsByMember = photoUploadRepository.findPhotoUploadsByMember(member);

        System.out.println("photoUploadsByMember.size() = " + photoUploadsByMember.size());
        
        return photoUploadsByMember;
    }

}
