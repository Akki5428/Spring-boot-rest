package com.controller;

import org.springframework.web.bind.annotation.RestController;

import com.entity.ProductEntity;
import com.repository.ProductRepository;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class ProductController {

	
	@Autowired
	ProductRepository productRepository;
	
	// api
	// read product information and store into databse
	@PostMapping("/products")
	public ProductEntity addProduct(@RequestBody ProductEntity productEntity) {
		System.out.println(productEntity.getProductName());
		System.out.println(productEntity.getPrice());
		productRepository.save(productEntity);
		return productEntity;
	}
	
	@GetMapping("/products")
	public List<ProductEntity> getAllProducts(){
		List<ProductEntity> products = productRepository.findAll();
		return products;
	}
	
	@GetMapping("/products/{productId}")
	public ProductEntity getProductbyId(@PathVariable("productId") Integer productId)
	{
		Optional<ProductEntity> op = productRepository.findById(productId);
		if(op.isEmpty())
		{
			return null;
		}
		else
		{
			return op.get();
		}
	}
	
	@GetMapping("/productbyid")
	public ProductEntity getProductbyIdbyParam(@RequestParam("productId") Integer productId)
	{
		Optional<ProductEntity> op = productRepository.findById(productId);
		if(op.isEmpty())
		{
			return null;
		}
		else
		{
			return op.get();
		}
	}
		
	@DeleteMapping("/products/{productId}")
	public String deleteProductbyId(@PathVariable("productId") Integer productId)
	{
		Optional<ProductEntity> op = productRepository.findById(productId);
		if(op.isEmpty())
		{
			return "Product Not Found";
		}
		else
		{
			productRepository.deleteById(productId);
			return "Sucess";
		}
	}
	
	@PutMapping("/products")
	public ProductEntity updateProduct(@RequestBody ProductEntity productEntity) {
		productRepository.save(productEntity);
		return productEntity;
	}
	
}
