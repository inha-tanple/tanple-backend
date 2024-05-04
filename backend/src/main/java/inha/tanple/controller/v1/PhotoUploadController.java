package inha.tanple.controller.v1;

import inha.tanple.domain.PhotoUpload;
import inha.tanple.service.PhotoUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Tag(name = "사진 제출", description = "제출한 사진을 다루는 API 엔드포인트입니다.")
public class PhotoUploadController {

    private final PhotoUploadService photoUploadService;

    @Operation(summary = "사진 업로드", description = "사진을 서버에 업로드합니다.")
    @PostMapping("/v1/photos")
    public void uploadPhoto(@RequestBody Long productBarcode, @RequestBody String photoBytes) {
        // TODO: 현재 인증된 사용자 ID 가져오기 (예: userId = 1000L)
        Long userId = 1000L;

        String photoUrl = ""; // photoBytes -> photoUrl
        PhotoUpload photoUpload = photoUploadService.upload(userId, productBarcode, photoUrl);

        // TODO: 파이썬 사진 서버에 요청 넣음
        // request python server using uploadId
    }

    @Operation(summary = "업로드 리스트 조회", description = "업로드한 사진 리스트를 가져옵니다.")
    @GetMapping("/v1/photos")
    public List<PhotoUpload> getPhotos() {
        // TODO: 현재 인증된 사용자 ID 가져오기 (예: userId = 1000L)
        Long userId = 1000L;

        List<PhotoUpload> photoUploads = photoUploadService.getPhotoUploadsByMemberId(userId);

        return photoUploads;
    }

}
