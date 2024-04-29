package inha.tanple.service;

import inha.tanple.domain.Product;
import inha.tanple.domain.User;
import inha.tanple.domain.UserFavoriteProduct;
import inha.tanple.exception.ResourceNotFoundException;
import inha.tanple.repository.ProductRepository;
import inha.tanple.repository.UserFavoriteProductRepository;
import inha.tanple.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final UserFavoriteProductRepository userFavoriteProductRepository;

    public Product getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return product;
    }

    public List<Product> getProducts(int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        List<Product> products = productRepository.findAll(pageable).getContent();
        return products;
    }

    @Transactional
    public void toggleFavorite(Long userId, Long productId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        boolean isFavorite = userFavoriteProductRepository.existsByUserAndProduct(user, product);


        if (isFavorite) {
            UserFavoriteProduct userFavoriteProduct = userFavoriteProductRepository.findByUserAndProduct(user, product);
            userFavoriteProductRepository.delete(userFavoriteProduct);
        } else {
            // TODO: 빌더패턴으로 바꾸기
            UserFavoriteProduct userFavoriteProduct = new UserFavoriteProduct();
            userFavoriteProduct.setUser(user);
            userFavoriteProduct.setProduct(product);
            userFavoriteProductRepository.save(userFavoriteProduct);
        }
    }
}