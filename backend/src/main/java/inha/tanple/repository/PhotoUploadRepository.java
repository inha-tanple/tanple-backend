package inha.tanple.repository;

import inha.tanple.domain.Member;
import inha.tanple.domain.PhotoUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoUploadRepository extends JpaRepository<PhotoUpload, Long> {
    List<PhotoUpload> findPhotoUploadsByMember(Member member);
}