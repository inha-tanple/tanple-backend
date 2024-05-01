package inha.tanple.controller.v1;

import inha.tanple.domain.Product;
import inha.tanple.dto.response.ProductResponseDto;
import inha.tanple.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @Operation(summary = "물품 리스트를 조건에 맞추어 가져옵니다.")
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getProducts(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "20") int limit) {
        List<Product> products = productService.getProducts(offset, limit);

        List<ProductResponseDto> dtos = products.stream()
                .map(p -> new ProductResponseDto(p.getBarcode(), p.getName(), p.getPrice()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "ID에 해당하는 물품 정보를 가져옵니다.")
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long productId) {
        Product product = productService.getProduct(productId);

        ProductResponseDto dto = new ProductResponseDto(product);

        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "물품 즐겨찾기를 등록/해제합니다. (수정 필요)")
    @PostMapping("/{productId}/favorite")
    public ResponseEntity<Void> toggleFavorite(@PathVariable Long productId) {

        // 현재 인증된 사용자 ID 가져오기 (예: userId = 1L)
        Long userId = 1L;

        // TODO: 토글 말고 lazy한 업데이트 기획
        productService.toggleFavorite(productId, userId);
        return ResponseEntity.ok().build();
    }


}