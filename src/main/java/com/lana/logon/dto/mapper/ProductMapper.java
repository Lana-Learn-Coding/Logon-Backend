package com.lana.logon.dto.mapper;

import com.lana.logon.dto.ProductDto;
import com.lana.logon.model.product.Product;
import com.lana.logon.model.product.ProductImage;
import com.lana.logon.model.product.rate.ProductRate;
import com.lana.logon.repository.product.ProductImageRepo;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, ProductImageMapper.class})
public abstract class ProductMapper {

    private ProductImageRepo productImageRepo;

    @Autowired
    public void initialize(ProductImageRepo productImageRepo) {
        this.productImageRepo = productImageRepo;
    }

    @Mapping(target = "image", ignore = true)
    @Mapping(target = "rate", ignore = true)
    public abstract ProductDto productToProductDTO(Product product);

    @AfterMapping
    public void setProductMainImage(Product product, @MappingTarget ProductDto productDto) {
        // Set image here instead of use Mapper
        // The Mapper will call product.getImages() and
        // then pass it to the map function.
        //
        // Here we only need query the first image.
        // instead of query all images by calling product.getImages()
        productDto.setImage(
                this.productImageRepo
                        .findFirstByProductId(product.getId())
                        .map(ProductImage::getImage)
                        .orElse("")
        );

        productDto.setRate(
                product.getRates().stream()
                        .map(ProductRate::getRate)
                        .reduce(Integer::sum)
                        .map((sum) -> {
                            int size = product.getRates().size();
                            return size == 0 ? (float) sum / size : (float) 0;
                        })
                        .orElse((float) 0)
        );
    }

}
