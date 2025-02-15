package com.swms.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.swms.dto.AddressDTO;
import com.swms.dto.OrderRequestDTO;
import com.swms.dto.OrdersDTO;
import com.swms.dto.ProductOrderDTO;
import com.swms.dto.ShopDTO;
import com.swms.entity.Address;
import com.swms.entity.AgentDetails;
import com.swms.entity.Orders;
import com.swms.entity.Products;
import com.swms.entity.Shops;
import com.swms.repository.AddressRepository;
import com.swms.repository.AgentRepository;
import com.swms.repository.OrderRepository;
import com.swms.repository.ProductRepository;
import com.swms.repository.ShopRepository;
import com.swms.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	private final ShopRepository shopRepository;

	private final AgentRepository agentRepository;

	private AddressRepository addressRepository;

	private ProductRepository productRepository;

	private final ModelMapper modelMapper;

	private final AgentServiceImpl agentService;

	private final ShopServiceImpl shopService;

	public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, AgentRepository agentRepository,
			ShopRepository shopRepository, AddressRepository addressRepository, ProductRepository productRepository,
			AgentServiceImpl agentService, ShopServiceImpl shopService) {
		super();
		this.orderRepository = orderRepository;
		this.modelMapper = modelMapper;
		this.agentRepository = agentRepository;
		this.shopRepository = shopRepository;
		this.addressRepository = addressRepository;
		this.productRepository = productRepository;
		this.agentService = agentService;
		this.shopService = shopService;
	}

//	@Transactional
	@Override
	public Orders requestOrderDetails(OrdersDTO orderDto) {
		Orders orders = modelMapper.map(orderDto, Orders.class);
		Orders saveOrder = orderRepository.save(orders);
		return saveOrder;
	}

	@Override
	public List<Orders> createOrderRequest(OrderRequestDTO orderRequest) {
		Shops shop = null;
		// Check if shop details are provided
		if (orderRequest.getGstId() != null && !orderRequest.getGstId().isEmpty()) {
	        // Check if shop exists with the given gstId
	        Shops existingShop = shopService.checkShopDetailsPresentInDb(orderRequest.getGstId());
	        
	        if (existingShop != null) {
	            // If shop exists, set the shop from the database
	            shop = existingShop;
	        } else {
	            // If shop doesn't exist, proceed with shop creation (as before)
	            shop = shopService.createShopInformation(orderRequest.getShopDetails());
	        }
	    } else {
	        // If no gstId is provided, create a new shop (as before)
	        shop = shopService.createShopInformation(orderRequest.getShopDetails());
	    }
		// Find agent details if provided
		AgentDetails agent = null;
		if (orderRequest.getAgentEmail() != null && orderRequest.getAgentContact() != null) {
			agent = agentRepository.findByEmailAndContactNo(orderRequest.getAgentEmail(),
					orderRequest.getAgentContact());
		}

		
		List<Orders> ordersList = new ArrayList();
		// Find the product and reduce its quantity
//		Products product = productRepository.findById(productOrder.getProductId())
//				.orElseThrow(() -> new RuntimeException("Product not found"));
//
//		if (product.getQuantity() <  productOrder.getQuantity()) {
//			throw new RuntimeException("Insufficient product quantity");
//		}
		for (ProductOrderDTO productOrder : orderRequest.getProducts()) {
	        // Find the product and check its availability
	        Products product = productRepository.findById(productOrder.getProductId())
	                .orElseThrow(() -> new RuntimeException("Product not found"));

	        if (product.getQuantity() < productOrder.getQuantity()) {
	            throw new RuntimeException("Insufficient product quantity for product ID: " + productOrder.getProductId());
	        }

		product.setQuantity(product.getQuantity() -  productOrder.getQuantity());
		productRepository.save(product);

		// Create the order
		Orders order = new Orders();
		order.setQuantity(productOrder.getQuantity());
		order.setPrice(productOrder.getPrice());
		order.setPaymentStatus(orderRequest.getPaymentStatus());
		order.setOrder_status(orderRequest.getOrder_status());
		order.setOrderDate(LocalDateTime.now());
		order.setProduct(product);
		order.setShop(shop);
		order.setAgent(agent);

		 ordersList.add(order);
		}
		return orderRepository.saveAll(ordersList);
	}
}
