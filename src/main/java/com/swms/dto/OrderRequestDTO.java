package com.swms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
   private Long quantity;
   private float price;
   private String paymentStatus;
   private String order_status;
   private ShopDTO shopDetails; 
   private String agentEmail; 
   private String agentContact; 
   private Long productId;
   private String gstId;

}
