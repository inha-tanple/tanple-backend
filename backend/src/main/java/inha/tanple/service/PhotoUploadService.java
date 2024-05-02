package inha.tanple.service;

import inha.tanple.domain.Member;
import inha.tanple.domain.PhotoUpload;
import inha.tanple.domain.Product;
import inha.tanple.exception.ResourceNotFoundException;
import inha.tanple.repository.PhotoUploadRepository;
import inha.tanple.repository.ProductRepository;
import inha.tanple.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoUploadService {

    private final PhotoUploadRepository photoUploadRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public PhotoUpload upload(Long userId, Long productBarcode, String photoUrl) {

        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Product product = productRepository.findById(productBarcode)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productBarcode));

        PhotoUpload photoUpload = new PhotoUpload();
        photoUpload.setMember(member);
        photoUpload.setProduct(product);
        photoUpload.setPhotoUrl(photoUrl);


        return photoUploadRepository.save(photoUpload);
    }

    public List<PhotoUpload> getPhotoUploadsByMemberId(Long memberId) {
        return photoUploadRepository.findByMemberId(memberId);
    }


}
