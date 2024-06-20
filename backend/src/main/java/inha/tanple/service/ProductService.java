package inha.tanple.service;

import inha.tanple.domain.Member;
import inha.tanple.domain.Product;
import inha.tanple.domain.MemberFavoriteProduct;
import inha.tanple.exception.ResourceNotFoundException;
import inha.tanple.repository.ProductRepository;
import inha.tanple.repository.UserFavoriteProductRepository;
import inha.tanple.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final UserFavoriteProductRepository userFavoriteProductRepository;


    public Product getProduct(Long productBarcode) {
        Product product = productRepository.findById(productBarcode)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return product;
    }


    public List<Product> getProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Transactional
    public void toggleFavorite(Long memberId, Long productBarcode) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        Product product = productRepository.findById(productBarcode)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        boolean isFavorite = userFavoriteProductRepository.existsByMemberAndProduct(member, product);

        if (isFavorite) {
            MemberFavoriteProduct memberFavoriteProduct = userFavoriteProductRepository.findByMemberAndProduct(member,
                    product);
            userFavoriteProductRepository.delete(memberFavoriteProduct);
        } else {
            // TODO: 빌더패턴으로 바꾸기
            MemberFavoriteProduct memberFavoriteProduct = new MemberFavoriteProduct();
            memberFavoriteProduct.setMember(member);
            memberFavoriteProduct.setProduct(product);
            userFavoriteProductRepository.save(memberFavoriteProduct);
        }
    }

}