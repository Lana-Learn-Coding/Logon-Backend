package com.lana.logon.repositorie;

import com.lana.logon.model.cart.CartProduct;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CartProductRepo extends PagingAndSortingRepository<CartProduct, Integer> {
}
