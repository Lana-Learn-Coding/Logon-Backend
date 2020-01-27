package com.lana.logon.repositories;

import com.lana.logon.models.cart.CartProduct;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CartProductRepo extends PagingAndSortingRepository<CartProduct, String> {
}
