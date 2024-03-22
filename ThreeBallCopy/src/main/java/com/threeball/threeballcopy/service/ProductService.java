package com.threeball.threeballcopy.service;

import com.threeball.threeballcopy.entities.Products;
import com.threeball.threeballcopy.model.requestDTO.ProductRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    void create(ProductRequestDto productRequestDto);
    List<?> ToSeeAllProducts();
    List<Products>FindByName(String name);
    void delete(int id);
    Products getById(int id);
}
