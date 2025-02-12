package com.swms.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.swms.dto.AddressDTO;
import com.swms.dto.OrderRequestDTO;
import com.swms.dto.OrdersDTO;
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
			AgentServiceImpl agentService,ShopServiceImpl shopService) {
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
	public Orders createOrderRequest(OrderRequestDTO orderRequest) {

		  Shops shop = shopRepository.findByShopGstId(orderRequest.getShopGstId())
		            .orElseGet(() -> {
		                // If shop does not exist, prompt for shop details
		                ShopDTO shopDetails = orderRequest.getShopDetails();
		                if (shopDetails == null) {
		                    throw new RuntimeException("Shop details are required since the shop does not exist.");
		                }
		                return createShop(shopDetails);
		            });
        // Find agent details if provided
        AgentDetails agent = null;
        if (orderRequest.getAgentEmail() != null && orderRequest.getAgentContact() != null) {
            agent = agentRepository.findByEmailAndContactNo(orderRequest.getAgentEmail(), orderRequest.getAgentContact());
        }

        // Find the product and reduce its quantity
        Products product = productRepository.findById(orderRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        
        if (product.getQuantity() < orderRequest.getQuantity()) {
            throw new RuntimeException("Insufficient product quantity");
        }
        
        product.setQuantity(product.getQuantity() - orderRequest.getQuantity());
        productRepository.save(product);

        // Create the order
        Orders order = new Orders();
        order.setQuantity(orderRequest.getQuantity());
        order.setPrice(orderRequest.getPrice());
        order.setPaymentStatus(orderRequest.getPaymentStatus());
        order.setOrder_status("Pending");
        order.setOrderDate(LocalDateTime.now());
        order.setProduct(product);
        order.setShop(shop);
        order.setAgent(agent);

        return orderRepository.save(order);
    }

	private Shops createShop(ShopDTO shopDetails) {
	    Shops newShop = new Shops();
	    newShop.setShop_name(shopDetails.getShop_name());
	    newShop.setShopGstId(shopDetails.getShop_gst_id());
	    newShop.setType(shopDetails.getType());
	    newShop.setContact_info(shopDetails.getContact_info());

	    // Convert the list of AddressDTO to a list of Address entities
	    List<Address> addresses = shopDetails.getShop_address().stream()
	            .map(addressDTO -> modelMapper.map(addressDTO, Address.class))
	            .collect(Collectors.toList());

	    newShop.setShop_address(addresses); // Assuming shop_address is a List<Address>

	    return shopRepository.save(newShop);
	}
}
