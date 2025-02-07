package com.swms.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.swms.dto.ShopDTO;
import com.swms.entity.Address;
import com.swms.entity.Shops;
import com.swms.repository.ShopRepository;
import com.swms.service.ShopService;

@Service
public class ShopServiceImpl implements ShopService {
	
	private final ShopRepository shopRepository;
	
	private final ModelMapper modelMapper;
	
	public ShopServiceImpl(ShopRepository shopRepository, ModelMapper modelMapper) {
		super();
		this.shopRepository = shopRepository;
		this.modelMapper = modelMapper;
	}



	@Override
	public Shops CreateShopInformation(ShopDTO shopDTO) {
		Shops shopDetails = modelMapper.map(shopDTO, Shops.class);
		List<Address> shopAddress = shopDTO.getShop_address().stream()
				.map(shopsDTO ->{
			Address address = modelMapper.map(shopsDTO, Address.class);
			address.setShop(shopDetails);
			return address;
		}).toList();
		shopDetails.setShop_address(shopAddress);
		Shops saveShopes = shopRepository.save(shopDetails);	
		return saveShopes;
	}

}
