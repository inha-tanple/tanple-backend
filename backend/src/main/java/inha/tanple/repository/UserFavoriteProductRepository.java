package inha.tanple.repository;

import inha.tanple.domain.Product;
import inha.tanple.domain.User;
import inha.tanple.domain.UserFavoriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserFavoriteProductRepository extends JpaRepository<UserFavoriteProduct, Long> {


    UserFavoriteProduct findByUserAndProduct(User user, Product product);

    boolean existsByUserAndProduct(User user, Product product);
}