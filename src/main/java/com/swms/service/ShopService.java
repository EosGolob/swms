package com.swms.service;

import com.swms.dto.ShopDTO;
import com.swms.entity.Shops;

public interface ShopService {
   
	Shops createShopInformation(ShopDTO shopDTO);

	Shops checkShopDetailsPresentInDb(String gstId);
}
