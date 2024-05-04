package inha.tanple.repository;

import inha.tanple.domain.Member;
import inha.tanple.domain.Product;
import inha.tanple.domain.MemberFavoriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFavoriteProductRepository extends JpaRepository<MemberFavoriteProduct, Long> {

    MemberFavoriteProduct findByMemberAndProduct(Member member, Product product);

    boolean existsByMemberAndProduct(Member member, Product product);
}