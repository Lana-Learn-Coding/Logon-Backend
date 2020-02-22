package com.lana.logon.controller;

import com.lana.logon.dto.product.rate.ProductRateCount;
import com.lana.logon.dto.product.rate.ProductRateDto;
import com.lana.logon.model.product.ProductRate;
import com.lana.logon.repository.product.ProductRateRepo;
import com.lana.logon.security.AuthUserDetails;
import com.lana.logon.util.mapper.ProductMapper;
import io.github.perplexhub.rsql.RSQLJPASupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/rates")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductRateController {

    private final ProductRateRepo productRateRepo;
    private final ProductMapper productMapper;

    @Autowired
    public ProductRateController(ProductRateRepo productRateRepo,
                                 ProductMapper productMapper) {
        this.productRateRepo = productRateRepo;
        this.productMapper = productMapper;
    }

    @GetMapping
    public ResponseEntity<Page<ProductRateDto>> getAllRates(@RequestParam(defaultValue = "") String query,
                                                            Pageable pageable) {
        try {
            return ResponseEntity.ok(
                    productRateRepo
                            .findAll(RSQLJPASupport.toSpecification(query), pageable)
                            .map(productMapper::productRateToProductRateDto)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductRateDto> getRate(@PathVariable Integer id,
                                                  @AuthenticationPrincipal AuthUserDetails authenticatedUser) {
        Optional<ProductRate> found = productRateRepo.findById(id);
        if (!found.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        if (!found.get().getUser().getUsername().equals(authenticatedUser.getUsername())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(productMapper.productRateToProductRateDto(found.get()));
    }

    // TODO: Should return new rate
    @PostMapping
    public ResponseEntity<Void> rateProduct(@RequestBody ProductRateDto productRateDto,
                                            @AuthenticationPrincipal AuthUserDetails authenticatedUser,
                                            UriComponentsBuilder uriBuilder) {
        if (productRateDto.getProduct().getId() == null || productRateDto.getUser().getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        if (!productRateDto.getUser().getId().equals(authenticatedUser.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        ProductRate saved = productRateRepo.save(productMapper.productRateDtoToProductRate(productRateDto));
        URI savedUri = uriBuilder.pathSegment("api", "rates", saved.getId().toString()).build().toUri();
        return ResponseEntity.created(savedUri).build();
    }

    @GetMapping("/count")
    public ResponseEntity<ProductRateCount> getAvgRate(@RequestParam("productid") Integer id) {
        return ResponseEntity.ok(productRateRepo.getRateCountsByProductId(id));
    }
}
