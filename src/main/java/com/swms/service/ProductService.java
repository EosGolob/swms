package com.swms.service;

import com.swms.dto.ProductDTO;
import com.swms.entity.Products;

public interface ProductService {
	
	 Products insertProductInformation(ProductDTO productDTO);

}
