package com.swms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
//   private Long order_id;
   private Long quantity;
   private float price;
   private String paymentStatus;
   private String shopGstId; // Existing shop ID
   private ShopDTO shopDetails; // New shop details if shop doesn't exist
   private String agentEmail; // Optional
   private String agentContact; // Optional
   private Long productId;
}
