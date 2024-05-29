package inha.tanple.controller.v1;

import inha.tanple.domain.Product;
import inha.tanple.dto.response.ProductResponseDto;
import inha.tanple.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "물품 관리", description = "물품을 관리하기 위한 API 엔드포인트입니다.")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "물품 조회", description = "물품 리스트를 조건에 맞추어 가져옵니다.")
    @GetMapping("/v1/products")
    public List<ProductResponseDto> getProducts() {
        List<Product> products = productService.getProducts();

        List<ProductResponseDto> dtoList = products.stream()
                .map(p -> new ProductResponseDto(p))
                .collect(Collectors.toList());

        return dtoList;
    }

    @Operation(summary = "물품 세부 조회", description = "ID에 해당하는 물품 정보를 가져옵니다.")
    @GetMapping("/v1/products/{productId}")
    public ProductResponseDto getProduct(@PathVariable Long productId) {
        Product product = productService.getProduct(productId);

        return new ProductResponseDto(product);
    }

    @Operation(summary = "물품 즐겨찾기 갱신", description = "물품 즐겨찾기를 등록/해제합니다. (수정 필요)")
    @PostMapping("/v1/products/{productId}/favorite")
    public String toggleFavorite(@PathVariable Long productId) {

        // 현재 인증된 사용자 ID 가져오기 (예: userId = 1L)
        Long userId = 1000L;

        // TODO: 토글 말고 lazy 한 업데이트 기획
        productService.toggleFavorite(productId, userId);

        return "OK";
    }

}