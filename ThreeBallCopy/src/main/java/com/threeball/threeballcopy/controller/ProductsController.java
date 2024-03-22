package com.threeball.threeballcopy.controller;

import com.threeball.threeballcopy.entities.Products;
import com.threeball.threeballcopy.model.requestDTO.ProductRequestDto;
import com.threeball.threeballcopy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Product")
public class ProductsController {
    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequestDto productsDTO){
        productService.create(productsDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/getAll")
    public ResponseEntity<?>GetAll(){
        return ResponseEntity.ok(productService.ToSeeAllProducts());
    }
    @GetMapping("/FindByName")
    public ResponseEntity<?>GetByName(@RequestParam String name){
        List<Products> result = productService.FindByName(name);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?>DeleteProduct(@RequestParam int id){
        productService.delete(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/FindById")
    public ResponseEntity<?>GetById(@RequestParam int id){
        return ResponseEntity.ok(productService.getById(id));
    }

}
