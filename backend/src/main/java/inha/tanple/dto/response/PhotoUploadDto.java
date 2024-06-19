package inha.tanple.dto.response;

import inha.tanple.domain.Member;
import inha.tanple.domain.PhotoUpload;
import inha.tanple.domain.PhotoUploadStatus;
import inha.tanple.domain.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoUploadDto {

    private Long photo_upload_id;

    private Long productBarcode;

    private String photoUrl;

    private PhotoUploadStatus photoUploadStatus; // ["PENDING", "SUCCESS", "FAIL"],


    public PhotoUploadDto(PhotoUpload photoUpload) {
        this.photo_upload_id = photoUpload.getId();
        this.productBarcode = photoUpload.getProduct().getProductBarcode();
        this.photoUrl = photoUpload.getPhotoUrl();
        this.photoUploadStatus = photoUpload.getPhotoUploadStatus();
    }
}
