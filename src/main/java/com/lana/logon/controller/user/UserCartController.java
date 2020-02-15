package com.lana.logon.controller.user;


import com.lana.logon.dto.product.CartProductDto;
import com.lana.logon.model.cart.CartProduct;
import com.lana.logon.model.cart.CartProductKey;
import com.lana.logon.model.user.User;
import com.lana.logon.repository.CartProductRepo;
import com.lana.logon.repository.user.UserRepo;
import com.lana.logon.util.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/users")
public class UserCartController {
    private final UserRepo userRepo;
    private final CartProductRepo cartProductRepo;
    private final ProductMapper productMapper;

    @Autowired
    public UserCartController(UserRepo userRepo,
                              ProductMapper productMapper,
                              CartProductRepo cartProductRepo) {
        this.userRepo = userRepo;
        this.cartProductRepo = cartProductRepo;
        this.productMapper = productMapper;
    }

    @GetMapping("/{id}/carts")
    public ResponseEntity<List<CartProductDto>> getUserCarts(@PathVariable Integer id,
                                                             @AuthenticationPrincipal UserDetails authenticatedUser) {
        Optional<User> requestUser = userRepo.findById(id);
        if (!requestUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        if (!authenticatedUser.getUsername().equals(requestUser.get().getUsername())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(
                cartProductRepo.findAllByUserId(id)
                        .stream()
                        .map(productMapper::cartProductToCartProductDto)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/{id}/carts")
    public ResponseEntity<CartProductDto> addToUserCart(@PathVariable Integer id,
                                                        @RequestBody CartProductDto product,
                                                        @AuthenticationPrincipal UserDetails authenticatedUser,
                                                        UriComponentsBuilder uriBuilder) {
        Optional<User> requestUser = userRepo.findById(id);
        if (!requestUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        if (!authenticatedUser.getUsername().equals(requestUser.get().getUsername()) || !product.getId().getUserId().equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (cartProductRepo.existsById(product.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        CartProduct saved = cartProductRepo.save(productMapper.cartProductDtoToCartProduct(product));
        URI createdUri = uriBuilder
                .pathSegment("api",
                             "users",
                             saved.getId().getUserId().toString(),
                             "carts",
                             saved.getId().getProductId().toString())
                .build()
                .toUri();
        return ResponseEntity.created(createdUri).body(
                productMapper.cartProductToCartProductDto(
                        cartProductRepo.findById(saved.getId()).orElse(null)
                )
        );
    }

    @PutMapping("/{id}/carts/{productId}")
    public ResponseEntity<CartProductDto> putToUserCart(@PathVariable Integer id,
                                                        @PathVariable Integer productId,
                                                        @RequestBody CartProductDto product,
                                                        @AuthenticationPrincipal UserDetails authenticatedUser) {
        Optional<User> requestUser = userRepo.findById(id);
        if (!requestUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        if (!authenticatedUser.getUsername().equals(requestUser.get().getUsername()) || !product.getId().getUserId().equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        product.setId(new CartProductKey(id, productId));
        if (!cartProductRepo.existsById(product.getId())) {
            return ResponseEntity.notFound().build();
        }
        CartProduct modified = cartProductRepo.save(productMapper.cartProductDtoToCartProduct(product));
        return ResponseEntity.ok(
                productMapper.cartProductToCartProductDto(
                        cartProductRepo.findById(modified.getId()).orElse(null)
                )
        );
    }


    @DeleteMapping("/{id}/carts/{productId}")
    public ResponseEntity<CartProductDto> removeFromUserCart(@PathVariable Integer id,
                                                             @PathVariable Integer productId,
                                                             @AuthenticationPrincipal UserDetails authenticatedUser) {
        Optional<User> requestUser = userRepo.findById(id);
        if (!requestUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        if (!authenticatedUser.getUsername().equals(requestUser.get().getUsername())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        cartProductRepo.deleteById(new CartProductKey(id, productId));
        return ResponseEntity.ok().build();
    }
}
