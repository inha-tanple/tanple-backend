package inha.tanple.repository;

import inha.tanple.domain.Member;
import inha.tanple.domain.PhotoUpload;
import inha.tanple.domain.PhotoUploadStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoUploadRepository extends JpaRepository<PhotoUpload, Long> {
    List<PhotoUpload> findPhotoUploadsByMember(Member member);
    //상태를 기준으로 데이터 가져오기를 추가하였습니다.
    List<PhotoUpload> findPhotoUploadsByMemberAndPhotoUploadStatus(Member member, PhotoUploadStatus photoUploadStatus);
}