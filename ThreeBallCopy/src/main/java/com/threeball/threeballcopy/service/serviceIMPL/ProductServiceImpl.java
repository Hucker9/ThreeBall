package com.threeball.threeballcopy.service.serviceIMPL;

import com.threeball.threeballcopy.entities.Products;
import com.threeball.threeballcopy.exceptions.ProductNotFoundException;
import com.threeball.threeballcopy.model.requestDTO.ProductRequestDto;
import com.threeball.threeballcopy.model.responseDTO.ProductResponseDto;
import com.threeball.threeballcopy.repository.ProductRepository;
import com.threeball.threeballcopy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void create(ProductRequestDto productRequestDto) {
        Products products  = new Products();
        products.setIdproducts(0);
        products.setTitle(productRequestDto.getName());
        products.setDescription(productRequestDto.getDescription());
        products.setPrice(productRequestDto.getPrice());
        try {
            productRepository.save(products);
        }catch (Exception e){
            throw new ProductNotFoundException("Something went wrong during creating product");
        }
    }

    @Override
    public List<?> ToSeeAllProducts() {
        List <Products> products = productRepository.findAll();
        if(products == null){
           throw new ProductNotFoundException("there is no products");
        }
        List<ProductResponseDto>productResponseDTOS = new ArrayList<>();
        for (Products product :products) {
            ProductResponseDto productResponseDTO = new ProductResponseDto();
            productResponseDTO.setTitle(product.getTitle());
            productResponseDTO.setDescription(product.getDescription());
            productResponseDTO.setPrice(product.getPrice());
            productResponseDTOS.add(productResponseDTO);
        }
        return productResponseDTOS;
    }

    @Override
    public List<Products> FindByName(String name) {
        List<Products>products = productRepository.searchProduct(name);
        if(products == null){
            throw new ProductNotFoundException("Not found Product With that name");
        }
        return products;
    }

    @Override
    public void delete(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public Products getById(int id) {
        Optional<Products> byId = productRepository.findById(id);
        return byId.get();
    }

}