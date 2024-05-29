package inha.tanple.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import inha.tanple.domain.Member;
import inha.tanple.domain.Product;
import inha.tanple.domain.MemberFavoriteProduct;
import inha.tanple.exception.ResourceNotFoundException;
import inha.tanple.repository.ProductRepository;
import inha.tanple.repository.UserFavoriteProductRepository;
import inha.tanple.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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


    public List<Product> getProducts(int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        List<Product> products = productRepository.findAll(pageable).getContent();
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

    // ************************

    public Product mapToEntity(String[] record) {

        Product product = new Product();
        product.setProductBarcode(Long.parseLong(record[0]));
        product.setCompany(record[1]);
        product.setProductName(record[2]);
        product.setPrice(Integer.parseInt(record[3]));
        product.setBusinessRegistrationNumber(record[4]);
        product.setEarnedCredit(Integer.parseInt(record[5]));
        product.setCertificationCategory(record[6]);
        product.setEarningRate(Float.parseFloat(record[7]));
        product.setRegisterStartDate(record[8]);
        product.setRegisterEndDate(record[9]);
        return product;
    }

    @PostConstruct
    @Transactional
    public void loadData() throws IOException, CsvException {

        // CSV 파일 읽어오기
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("product_list.csv");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        CSVReader reader = new CSVReader(inputStreamReader);
        List<String[]> records = reader.readAll();

        // CSV 데이터를 Entity 로 매핑하여 insert
        for (String[] record : records) {
            // 첫 번째 레코드는 헤더이므로 skip
            if (record[0].equals("바코드")) continue;

            Product product = this.mapToEntity(record);
            productRepository.save(product);
        }
    }
}