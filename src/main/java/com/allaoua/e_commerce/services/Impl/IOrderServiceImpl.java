package com.allaoua.e_commerce.services.Impl;

import com.allaoua.e_commerce.dtos.Order.OrderRequestDTO;
import com.allaoua.e_commerce.dtos.Order.OrderResponseDTO;
import com.allaoua.e_commerce.dtos.OrderProduct.OrderProductRequestDTO;
import com.allaoua.e_commerce.entities.Order;
import com.allaoua.e_commerce.entities.OrderProduct;
import com.allaoua.e_commerce.entities.OrderProductKey;
import com.allaoua.e_commerce.entities.Product;
import com.allaoua.e_commerce.enums.OrderStatus;
import com.allaoua.e_commerce.exceptions.CustomerNotFoundException;
import com.allaoua.e_commerce.exceptions.OrderNotFoundException;
import com.allaoua.e_commerce.exceptions.QuantityExceedsStockException;
import com.allaoua.e_commerce.mappers.OrderMapper;
import com.allaoua.e_commerce.mappers.OrderProductMapper;
import com.allaoua.e_commerce.repositories.CustomerRepository;
import com.allaoua.e_commerce.repositories.OrderProductRepository;
import com.allaoua.e_commerce.repositories.OrderRepository;
import com.allaoua.e_commerce.repositories.ProductRepository;
import com.allaoua.e_commerce.services.IOrderService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IOrderServiceImpl implements IOrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderProductMapper orderProductMapper;
    private final CustomerRepository customerRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;

    public IOrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, OrderProductMapper orderProductMapper, CustomerRepository customerRepository, OrderProductRepository orderProductRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.orderProductMapper = orderProductMapper;
        this.customerRepository = customerRepository;
        this.orderProductRepository = orderProductRepository;
        this.productRepository = productRepository;
    }
    @CacheEvict(value = "products", allEntries = true)
    @Override
    public OrderResponseDTO saveOrder(OrderRequestDTO orderRequestDTO) {
        Order order=orderMapper.toEntity(orderRequestDTO);
        Order savedOrder = orderRepository.save(order);
        List<OrderProductRequestDTO> orderProductRequestDTOS=orderRequestDTO.getProducts();
        List<OrderProduct> orderProducts=orderProductRequestDTOS.stream().map(orderProductMapper::toEntity).toList();
        orderProducts.forEach(orderProduct->{
            orderProduct.setId(OrderProductKey.builder().orderId(savedOrder.getId()).productId(orderProduct.getProduct().getId()).build());
            orderProduct.setOrder(savedOrder);
            Product product=productRepository.findById(orderProduct.getProduct().getId()).orElse(null);
            if(product!=null){
                if(product.getStock()<orderProduct.getQuantity()){
                    throw new QuantityExceedsStockException("product quantity exceeds stock");
                }
                product.setStock(product.getStock()-orderProduct.getQuantity());
            }
            orderProductRepository.save(orderProduct);
        });
        return orderMapper.toDTO(savedOrder);
    }

    @CacheEvict(value = {"orders","order"},key = "#orderId", allEntries = true)
    @Override
    public OrderResponseDTO updateOrder(String orderId, OrderRequestDTO orderRequestDTO) throws OrderNotFoundException {
        Order foundOrder=orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("order not found"));
        foundOrder.getProducts().forEach(orderProduct->{
            Product product=productRepository.findById(orderProduct.getProduct().getId()).orElse(null);
            if(product!=null){
                product.setStock(product.getStock()+orderProduct.getQuantity());
                productRepository.save(product);
            }
        });
        orderProductRepository.deleteAll(foundOrder.getProducts());
        Order order=orderMapper.toEntity(orderRequestDTO);
        order.setId(orderId);
        Order savedOrder = orderRepository.save(order);
        List<OrderProductRequestDTO> orderProductRequestDTOS=orderRequestDTO.getProducts();
        List<OrderProduct> orderProducts=orderProductRequestDTOS.stream().map(orderProductMapper::toEntity).toList();
        orderProducts.forEach(orderProduct->{
            orderProduct.setId(OrderProductKey.builder().orderId(savedOrder.getId()).productId(orderProduct.getProduct().getId()).build());
            orderProduct.setOrder(savedOrder);
            Product product=productRepository.findById(orderProduct.getProduct().getId()).orElse(null);
            if(product!=null){
                product.setStock(product.getStock()-orderProduct.getQuantity());
                if(product.getStock()<orderProduct.getQuantity()){
                    throw new QuantityExceedsStockException("product quantity exceeds stock");
                }
                product.setStock(product.getStock()-orderProduct.getQuantity());
            }
            orderProductRepository.save(orderProduct);
        });
        return orderMapper.toDTO(savedOrder);
    }

    @CacheEvict(value = "orders", allEntries = true)
    @Override
    public String deleteOrder(String orderId) throws OrderNotFoundException {
        orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        orderRepository.deleteById(orderId);
        return "Order deleted successfully";
    }

    @Override
    public OrderResponseDTO getOrderById(String orderId) throws OrderNotFoundException {
        Optional<Order> foundOrder = orderRepository.findById(orderId);
        if(foundOrder.isEmpty())
            throw new OrderNotFoundException("order not found");
        return orderMapper.toDTO(foundOrder.get());
    }


    @Cacheable(value = "orders")
    @Override
    public List<OrderResponseDTO> getAllOrders() {
        return orderMapper.toListOfDTOS(orderRepository.findAll());
    }

    @Override
    public List<OrderResponseDTO> getOrdersByCustomerId(String customerId) throws CustomerNotFoundException {
        customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("customer not found"));
        return orderMapper.toListOfDTOS(orderRepository.findByCustomerId(customerId));
    }

    @Override
    public List<OrderResponseDTO> getOrdersByStatus(OrderStatus status) {
        return orderMapper.toListOfDTOS(orderRepository.findByStatus(status));
    }

    @Override
    public OrderResponseDTO updateOrderStatus(String orderId, OrderStatus status) throws OrderNotFoundException {
        Order order=orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        order.setStatus(status);
        Order savedOrder=orderRepository.save(order);
        return orderMapper.toDTO(savedOrder);
    }
}
