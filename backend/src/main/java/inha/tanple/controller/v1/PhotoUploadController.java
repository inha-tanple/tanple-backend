package inha.tanple.controller.v1;

import inha.tanple.domain.PhotoUpload;
import inha.tanple.dto.response.PhotoUploadDto;
import inha.tanple.exception.ResourceNotFoundException;
import inha.tanple.service.PhotoUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@Tag(name = "사진 제출", description = "영수증 사진을 제출하고 조회하는 엔드포인트입니다.")
public class PhotoUploadController {

    private final PhotoUploadService photoUploadService;

    @Operation(summary = "사진 업로드", description = "사진을 서버에 업로드합니다.")
    @PostMapping("/v1/photos")
    public String uploadImages(@RequestParam("barcodes") List<Long> barcodes,
                               @RequestParam("photos") List<MultipartFile> photos) {
        if (barcodes.size() != photos.size()) {
            // 바코드 수와 사진 수가 일치하지 않으면 에러 처리
            return "Error: Number of barcodes and photos do not match";
        }
        Long user_id = 1000L;

        for (int i = 0; i < photos.size(); i++) {
            MultipartFile photo = photos.get(i);
            Long barcode = barcodes.get(i);
            try {
                photoUploadService.saveFile(photo, barcode, user_id);
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
                return "NO MEMBER";
            } catch (IOException e) {
                e.printStackTrace();
                return "NO S3 ACCESS";
            }
        }
        return "OK";
    }


    @Operation(summary = "업로드 리스트 조회", description = "업로드한 사진 리스트를 가져옵니다.")
    @GetMapping("/v1/photos")
    public List<PhotoUploadDto> getPhotos() {


        System.out.println("PhotoUploadController.getPhotos");
        // TODO: 현재 인증된 사용자 ID 가져오기 (예: userId = 1000L)
        Long userId = 1000L;

        List<PhotoUpload> photoUploads = photoUploadService.getPhotoUploads(userId);


        List<PhotoUploadDto> dtoList = photoUploads.stream()
                .map(p -> new PhotoUploadDto(p))
                .collect(Collectors.toList());

        return dtoList;
    }

    @Operation(summary = "리스트 상태 업데이느", description = "Pending 상태인 데이터를 Success로 바꿉니다.")
    @GetMapping("/v1/photos/updateStatus")
    public String updatePhotoStatus() {
        System.out.println("PhotoUploadController.updatePhotoStatus");
        Long userId = 1000L;

        //Success로 상태 업데이트
        try{
            photoUploadService.updateStatusPendingToSuccess(userId);
        }catch(ResourceNotFoundException e){ // 에러 처리
            e.printStackTrace();
            return "No pending photo";
        }

        return "Update success";
    }

}
