package com.swms.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.swms.dto.AddressDTO;
import com.swms.dto.OrderDetailsRequestDto;
import com.swms.dto.OrdersDTO;
import com.swms.dto.ProductDTO;
import com.swms.entity.Address;
import com.swms.entity.AgentDetails;
import com.swms.entity.Orders;
import com.swms.entity.Products;
import com.swms.entity.Shops;
import com.swms.exception.InsufficientStockException;
import com.swms.exception.ProductNotFoundException;
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

	public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, AgentRepository agentRepository,
			ShopRepository shopRepository, AddressRepository addressRepository, ProductRepository productRepository) {
		super();
		this.orderRepository = orderRepository;
		this.modelMapper = modelMapper;
		this.agentRepository = agentRepository;
		this.shopRepository = shopRepository;
		this.addressRepository = addressRepository;
		this.productRepository = productRepository;
	}

//	@Transactional
	@Override
	public Orders requestOrderDetails(OrdersDTO orderDto) {
		Orders orders = modelMapper.map(orderDto, Orders.class);
		Orders saveOrder = orderRepository.save(orders);
		return saveOrder;
	}

	/**
	 * @Override public Orders createOrder(OrderDetailsRequestDto orderDetails) {
	 *           Orders orders = modelMapper.map(orderDetails, Orders.class); // Log
	 *           method start log.info("Starting to create order for Shop GST ID:
	 *           {}", orderDetails.getShop_gst_id());
	 * 
	 *           // Check if the shop exists by GST ID Optional<Shops> shopOpt =
	 *           shopRepository.findByShopGstId(orderDetails.getShop_gst_id());
	 *           Shops shop;
	 * 
	 *           if (shopOpt.isPresent()) { shop = shopOpt.get(); log.info("Shop
	 *           found with GST ID: {}", orderDetails.getShop_gst_id()); } else { //
	 *           If shop doesn't exist, create a new shop log.info("Shop not found.
	 *           Creating a new shop with GST ID: {}",
	 *           orderDetails.getShop_gst_id()); shop = new Shops();
	 *           shop.setShopGstId(orderDetails.getShop_gst_id());
	 *           shop.setShop_name(orderDetails.getShop_name());
	 *           shop.setType(orderDetails.getType());
	 *           shop.setContact_info(orderDetails.getContact_info());
	 * 
	 *           // Save the shop shop = shopRepository.save(shop); log.info("Shop
	 *           created and saved with GST ID: {}", orderDetails.getShop_gst_id());
	 * 
	 *           // Save shop address for (AddressDTO addressDTO :
	 *           orderDetails.getShop_address()) { Address shopAddress =
	 *           modelMapper.map(addressDTO, Address.class);
	 *           shopAddress.setStreet(addressDTO.getStreet());
	 *           shopAddress.setCity(addressDTO.getCity());
	 *           shopAddress.setState(addressDTO.getState());
	 *           shopAddress.setZipCode(addressDTO.getZipCode());
	 *           shopAddress.setCountry(addressDTO.getCountry());
	 *           shopAddress.setShop(shop); addressRepository.save(shopAddress);
	 *           log.info("Shop address saved for GST ID: {}",
	 *           orderDetails.getShop_gst_id()); } }
	 * 
	 *           // Check if the agent exists by email or ID Optional<AgentDetails>
	 *           agentOpt = agentRepository.findByEmail(orderDetails.getEmail());
	 *           AgentDetails agent;
	 * 
	 *           if (agentOpt.isPresent()) { agent = agentOpt.get(); log.info("Agent
	 *           found with email: {}", orderDetails.getEmail()); } else { // If
	 *           agent doesn't exist, create a new agent log.info("Agent not found.
	 *           Creating a new agent with email: {}", orderDetails.getEmail());
	 *           agent = new AgentDetails(); agent.setName(orderDetails.getName());
	 *           agent.setEmail(orderDetails.getEmail());
	 *           agent.setContactNo(orderDetails.getContactNo());
	 * 
	 *           // Create and add the agent's addresses to the agent object
	 *           List<Address> agentAddresses = new ArrayList<>(); for (AddressDTO
	 *           addressDTO : orderDetails.getAgentAddress()) { Address agentAddress
	 *           = modelMapper.map(addressDTO, Address.class);
	 *           agentAddress.setStreet(addressDTO.getStreet());
	 *           agentAddress.setCity(addressDTO.getCity());
	 *           agentAddress.setState(addressDTO.getState());
	 *           agentAddress.setZipCode(addressDTO.getZipCode());
	 *           agentAddress.setCountry(addressDTO.getCountry());
	 *           agentAddress.setAgent(agent); // Associate the address with the
	 *           agent agentAddresses.add(agentAddress); }
	 * 
	 *           // Set the agent's addresses to the agent object
	 *           agent.setAgentAddress(agentAddresses);
	 * 
	 *           // Save the agent (which will also save the associated addresses
	 *           due to cascade) agent = agentRepository.save(agent);
	 *           log.info("Agent created and saved with email: {}",
	 *           orderDetails.getEmail());
	 * 
	 *           // No need to separately save agent addresses because they are
	 *           saved due to the cascade for (Address agentAddress :
	 *           agentAddresses) { log.info("Agent address saved for agent email:
	 *           {}", orderDetails.getEmail()); } }
	 * 
	 *           // Create the order Orders order = new Orders();
	 *           order.setShop_gst_id(orderDetails.getShop_gst_id());
	 *           order.setQuantity(orderDetails.getQuantity());
	 *           order.setPrice(orderDetails.getPrice());
	 *           order.setPaymentStatus(orderDetails.getPaymentStatus());
	 *           order.setOrderDate(orderDetails.getOrderDate());
	 *           order.setShop(shop); order.setAgent(agent);
	 * 
	 *           // Log the order creation log.info("Creating order for Shop GST ID:
	 *           {} with Agent Email: {}", orderDetails.getShop_gst_id(),
	 *           orderDetails.getEmail());
	 * 
	 *           // Save the order Orders savedOrder = orderRepository.save(order);
	 *           log.info("Order created and saved with ID: {}",
	 *           savedOrder.getOrder_id());
	 * 
	 *           // Return the saved order // OrdersDTO ordersDTO =
	 *           modelMapper.map(savedOrder, OrdersDTO.class);
	 * 
	 *           return savedOrder; }
	 */

	@Override
	public List<Orders> createOrder(OrderDetailsRequestDto orderDetails) {
	    // Log method start
	    log.info("Starting to create order for Shop GST ID: {}", orderDetails.getShop_gst_id());

	    // Step 1: Handle shop logic (Check if shop exists, otherwise create it)
	    Shops shop = getOrCreateShop(orderDetails);

	    // Step 2: Handle agent logic (Check if agent exists, otherwise create it)
	    AgentDetails agent = getOrCreateAgent(orderDetails);
/**
	    // Step 3: Handle product validation and stock check
	    Products product = validateAndGetProduct(orderDetails);

	    // Step 4: Create the order and associate the product
	    Orders order = createAndSaveOrder(orderDetails, shop, agent, product);
*/
	    List<Orders> orders = new ArrayList<>();
	    // Step 3: Process each product in the order
	    for (ProductDTO productDTO : orderDetails.getProducts()) {
	        // Step 3.1: Validate and get product
	        Products product = validateAndGetProduct(productDTO);

	        // Step 3.2: Create and save the order for this product
	        Orders order = createAndSaveOrder(orderDetails, shop, agent, productDTO, product);

	        // Step 3.3: Add the created order to the orders list
	        orders.add(order);
	    }
	    return orders;
	}

	private Shops getOrCreateShop(OrderDetailsRequestDto orderDetails) {
	    Optional<Shops> shopOpt = shopRepository.findByShopGstId(orderDetails.getShop_gst_id());
	    Shops shop;

	    if (shopOpt.isPresent()) {
	        shop = shopOpt.get();
	        log.info("Shop found with GST ID: {}", orderDetails.getShop_gst_id());
	    } else {
	        log.info("Shop not found. Creating a new shop with GST ID: {}", orderDetails.getShop_gst_id());
	        shop = new Shops();
	        shop.setShopGstId(orderDetails.getShop_gst_id());
	        shop.setShop_name(orderDetails.getShop_name());
	        shop.setType(orderDetails.getType());
	        shop.setContact_info(orderDetails.getContact_info());

	        shop = shopRepository.save(shop);
	        log.info("Shop created and saved with GST ID: {}", orderDetails.getShop_gst_id());

	        // Save shop address
	        saveShopAddresses(orderDetails, shop);
	    }

	    return shop;
	}

	private void saveShopAddresses(OrderDetailsRequestDto orderDetails, Shops shop) {
	    for (AddressDTO addressDTO : orderDetails.getShop_address()) {
	        Address shopAddress = modelMapper.map(addressDTO, Address.class);
	        shopAddress.setShop(shop);
	        addressRepository.save(shopAddress);
	        log.info("Shop address saved for GST ID: {}", orderDetails.getShop_gst_id());
	    }
	}

	private AgentDetails getOrCreateAgent(OrderDetailsRequestDto orderDetails) {
	    Optional<AgentDetails> agentOpt = agentRepository.findByEmail(orderDetails.getEmail());
	    AgentDetails agent;

	    if (agentOpt.isPresent()) {
	        agent = agentOpt.get();
	        log.info("Agent found with email: {}", orderDetails.getEmail());
	    } else {
	        log.info("Agent not found. Creating a new agent with email: {}", orderDetails.getEmail());
	        agent = new AgentDetails();
	        agent.setName(orderDetails.getName());
	        agent.setEmail(orderDetails.getEmail());
	        agent.setContactNo(orderDetails.getContactNo());

	        // Create and add agent's addresses
	        saveAgentAddresses(orderDetails, agent);

	        agent = agentRepository.save(agent);
	        log.info("Agent created and saved with email: {}", orderDetails.getEmail());

	        logAgentAddresses(orderDetails);
	    }

	    return agent;
	}

	private void saveAgentAddresses(OrderDetailsRequestDto orderDetails, AgentDetails agent) {
	    List<Address> agentAddresses = new ArrayList<>();
	    for (AddressDTO addressDTO : orderDetails.getAgentAddress()) {
	        Address agentAddress = modelMapper.map(addressDTO, Address.class);
	        agentAddress.setAgent(agent);
	        agentAddresses.add(agentAddress);
	    }
	    agent.setAgentAddress(agentAddresses);
	}

	private void logAgentAddresses(OrderDetailsRequestDto orderDetails) {
	    // Iterate over the list of AddressDTO (since getAgentAddress() returns a list of AddressDTO)
	    for (AddressDTO agentAddressDTO : orderDetails.getAgentAddress()) {
	        // Log the agent address details using AddressDTO fields (you can customize the log as needed)
	        log.info("Agent address saved for agent email: {} - Address: {}, {}, {}", 
	                 orderDetails.getEmail(), 
	                 agentAddressDTO.getStreet(), 
	                 agentAddressDTO.getCity(), 
	                 agentAddressDTO.getState()); 
//	                 agentAddressDTO.getPostalCode());
	    }
	}

/**
	private Products validateAndGetProduct(OrderDetailsRequestDto orderDetails) {
	    Optional<Products> productOpt = productRepository.findByProductId(orderDetails.getProduct_id());
	    Products product;

	    if (productOpt.isPresent()) {
	        product = productOpt.get();
	        log.info("Product found with ID: {}", orderDetails.getProduct_id());

	        // Check for stock availability
	        if (product.getQuantity() < orderDetails.getQuantity()) {
	            log.error("Insufficient stock for product with ID: {}", orderDetails.getProduct_id());
	            throw new InsufficientStockException("Not enough stock for product ID: " + orderDetails.getProduct_id());
	        }

	        // Reduce the stock
	        product.setQuantity(product.getQuantity() - orderDetails.getQuantity());
	        productRepository.save(product);
	        log.info("Product quantity updated for product ID: {}", orderDetails.getProduct_id());
	    } else {
	        log.error("Product with ID {} not found", orderDetails.getProduct_id());
	        throw new ProductNotFoundException("Product not found with ID: " + orderDetails.getProduct_id());
	    }

	    return product;
	}
*/
	
	private Products validateAndGetProduct(ProductDTO productDTO) {
	    Optional<Products> productOpt = productRepository.findByProductId(productDTO.getProduct_id());
	    Products product;

	    if (productOpt.isPresent()) {
	        product = productOpt.get();
	        log.info("Product found with ID: {}", productDTO.getProduct_id());

	        // Check for stock availability
	        if (product.getQuantity() < productDTO.getQuantity()) {
	            log.error("Insufficient stock for product with ID: {}", productDTO.getProduct_id());
	            throw new InsufficientStockException("Not enough stock for product ID: " + productDTO.getProduct_id());
	        }

	        // Reduce the stock
	        product.setQuantity(product.getQuantity() - productDTO.getQuantity());
	        productRepository.save(product);
	        log.info("Product quantity updated for product ID: {}", productDTO.getProduct_id());
	    } else {
	        log.error("Product with ID {} not found", productDTO.getProduct_id());
	        throw new ProductNotFoundException("Product not found with ID: " + productDTO.getProduct_id());
	    }

	    return product;
	}
/**
	private Orders createAndSaveOrder(OrderDetailsRequestDto orderDetails, Shops shop, AgentDetails agent, Products product) {
	    Orders order = new Orders();
	    order.setShop_gst_id(orderDetails.getShop_gst_id());
	    order.setQuantity(orderDetails.getQuantity());
	    order.setPrice(orderDetails.getPrice());
	    order.setPaymentStatus(orderDetails.getPaymentStatus());
	    order.setOrderDate(orderDetails.getOrderDate());
	    order.setShop(shop);
	    order.setAgent(agent);
	    order.setProduct(product);

	    log.info("Creating order for Shop GST ID: {} with Agent Email: {}", orderDetails.getShop_gst_id(), orderDetails.getEmail());

	    Orders savedOrder = orderRepository.save(order);
	    log.info("Order created and saved with ID: {}", savedOrder.getOrder_id());

	    return savedOrder;
	}
*/
	private Orders createAndSaveOrder(OrderDetailsRequestDto orderDetails, Shops shop, AgentDetails agent, ProductDTO productDTO, Products product) {
	    Orders order = new Orders();
	    order.setShop_gst_id(orderDetails.getShop_gst_id());
	    order.setQuantity(productDTO.getQuantity());
	    order.setPrice(productDTO.getPrice());
	    order.setPaymentStatus(orderDetails.getPaymentStatus()); // Assuming paymentStatus is the same for all products in the order
	    order.setOrderDate(orderDetails.getOrderDate()); // Assuming orderDate is the same for all products in the order
	    order.setShop(shop);
	    order.setAgent(agent);
	    order.setProduct(product); // Associate the product with the order

	    log.info("Creating order for Shop GST ID: {} with Agent Email: {} for Product ID: {}", 
	             orderDetails.getShop_gst_id(), orderDetails.getEmail(), productDTO.getProduct_id());

	    Orders savedOrder = orderRepository.save(order);
	    log.info("Order created and saved with ID: {}", savedOrder.getOrder_id());

	    return savedOrder;
	}


}
