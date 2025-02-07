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

import com.swms.dto.ShopDTO;
import com.swms.entity.Shops;
import com.swms.serviceImpl.ShopServiceImpl;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/shop")
public class ShopsController {
	
	private ShopServiceImpl shopServiceImpl;

	public ShopsController(ShopServiceImpl shopServiceImpl) {
		super();
		this.shopServiceImpl = shopServiceImpl;
	}
	
	@PostMapping("/createShopInformation")
	public ResponseEntity<?> createShopeInformation(@Valid @RequestBody ShopDTO shopDTO, 
			BindingResult bindingResult){
		log.info("Received request to create shope information : {} ", shopDTO);
		if(bindingResult.hasErrors()) {
			List<String> errorMessages = bindingResult.getAllErrors().stream()
					.map(ObjectError :: getDefaultMessage)
					.collect(Collectors.toList());
			log.error("validation failed for shop information : {} ",errorMessages);
		}
		log.info("validation passed proceeding with saving shope details for : {} ", shopDTO);
		Shops saveShope = shopServiceImpl.CreateShopInformation(shopDTO);
		log.info("shop Created SuccessFully : {} " , saveShope);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveShope);
		
		
	}

}
