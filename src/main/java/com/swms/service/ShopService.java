package com.swms.service;

import com.swms.dto.ShopDTO;
import com.swms.entity.Shops;

public interface ShopService {
   
	Shops CreateShopInformation(ShopDTO shopDTO);
}
