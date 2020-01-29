package com.lana.logon.repository.product;

import com.lana.logon.model.product.ProductImage;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductImageRepo extends CrudRepository<ProductImage, Integer> {
    Optional<ProductImage> findFirstByProductId(Integer id);

    Optional<Iterable<ProductImage>> findAllByProductId(Integer id);
}
