package com.tikifake.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tikifake.entity.CartItem;
import com.tikifake.entity.Order;
import com.tikifake.entity.OrderItem;
import com.tikifake.entity.User;
import com.tikifake.model.request.creator.OrderRequest;
import com.tikifake.model.response.list.IOrderItem;
import com.tikifake.repository.CartItemRepository;
import com.tikifake.repository.CartRepository;
import com.tikifake.repository.OrderItemRepository;
import com.tikifake.repository.OrderRepository;
import com.tikifake.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	
	@Override
	public List<IOrderItem> order(OrderRequest orderRequest) {
		Long cartId = orderRequest.getCartId();
		List<CartItem> items = cartItemRepository.findAllCheckedItemInCart(cartId);
		User user = cartRepository.findById(cartId).get().getUser();
		double totalCost = 0;
		
		List<OrderItem> orderItemList = new ArrayList<>();
		
		Order order = new Order();
		order.setUser(user);	
		order.setOrderItems(orderItemList);
		order.setStatus("wait for payment");
		
		for(CartItem cartItem : items) {
			System.out.println(cartItem.getTotalPrice());
			OrderItem orderItem = new OrderItem();
			orderItem.setTotalPrice(cartItem.getTotalPrice());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setTotalWeight(cartItem.getTotalWeight());
			orderItem.setOrder(order);
			
			totalCost = totalCost + cartItem.getTotalPrice();
			order.setTotalCost(totalCost);
			order.getOrderItems().add(orderItem);
		}
		
		Order result = orderRepository.save(order);
		
		List<IOrderItem> response = orderItemRepository.getOrderItem(result.getId());
		return response;
	}

}
