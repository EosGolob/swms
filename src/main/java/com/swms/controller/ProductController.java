package com.swms.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swms.dto.ProductDTO;
import com.swms.entity.Products;
import com.swms.error.ErrorResponse;
import com.swms.serviceImpl.ProductServiceImpl;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/product")
public class ProductController {

	private ProductServiceImpl productServiceImpl;

	public ProductController(ProductServiceImpl productServiceImpl) {
		super();
		this.productServiceImpl = productServiceImpl;
	}

	@PostMapping("/creatProductInformation")
	public ResponseEntity<?> insertProductInformation(@Valid @RequestBody ProductDTO productDto,
			BindingResult bindingResult) {
		log.info("Received request to create product information : {} ", productDto);
		if (bindingResult.hasErrors()) {
			List<String> errorMessages = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage)
					.collect(Collectors.toList());
			log.error("Validation failed for product information : {} ", errorMessages);
			ErrorResponse errorResponse = new ErrorResponse(errorMessages);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
		log.info("Validation passed proceeding with product creation for : {} ", productDto);
		Products prod = productServiceImpl.insertProductInformation(productDto);
		log.info("product created successfully : {} ", prod);
		return ResponseEntity.status(HttpStatus.CREATED).body(prod);

	}
}
