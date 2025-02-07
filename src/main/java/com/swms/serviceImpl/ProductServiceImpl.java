package com.swms.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.swms.dto.ProductDTO;
import com.swms.entity.Products;
import com.swms.repository.ProductRepository;
import com.swms.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;
	
	private final ModelMapper modelMapper;
	
	public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
		super();
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
	}


	@Override
	public Products insertProductInformation(ProductDTO productDTO) {
		Products products = modelMapper.map(productDTO, Products.class);
		Products saveProduct = productRepository.save(products);
		return saveProduct;
	}

}
