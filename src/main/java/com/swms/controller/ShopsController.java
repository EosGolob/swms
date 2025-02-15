package com.swms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swms.dto.ShopDTO;
import com.swms.entity.Shops;
import com.swms.serviceImpl.ShopServiceImpl;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/shop")
public class ShopsController {

	private ShopServiceImpl shopServiceImpl;

	public ShopsController(ShopServiceImpl shopServiceImpl) {
		super();
		this.shopServiceImpl = shopServiceImpl;
	}

	@PostMapping("/createShopInformation")
	public ResponseEntity<?> createShopeInformation(@Valid @RequestBody ShopDTO shopDTO, BindingResult bindingResult) {
		log.info("Received request to create shope information : {} ", shopDTO);
		if (bindingResult.hasErrors()) {
			List<String> errorMessages = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage)
					.collect(Collectors.toList());
			log.error("validation failed for shop information : {} ", errorMessages);
		}
		log.info("validation passed proceeding with saving shope details for : {} ", shopDTO);
		Shops saveShope = shopServiceImpl.createShopInformation(shopDTO);
		log.info("shop Created SuccessFully : {} ", saveShope);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveShope);

	}

	@GetMapping("/checkShopId/{gstId}")
	public ResponseEntity<?> checkShopDetailsPresentDatabases(@PathVariable String gstId) {
		Shops shops = shopServiceImpl.checkShopDetailsPresentInDb(gstId);
		Map<String, Object> response = new HashMap<>();
		if (shops != null) {
			response.put("exists", true);
			response.put("shopId", shops.getId());
			response.put("shopGstId",shops.getShopGstId());
			response.put("shopName", shops.getShop_name());
			response.put("contactInfo",shops.getContact_info());
			response.put("type",shops.getType());
			response.put("shop_address", shops.getShop_address());
			return ResponseEntity.ok(response);
		} else {
			response.put("exists", false);
			response.put("message", "Shop not found");
			return ResponseEntity.ok(response);
		}

	}

}
