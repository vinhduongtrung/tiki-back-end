package com.tikifake.model.request.creator;

public class CheckItemRequest {
	
	private Long cartId;

	private Long productId;
	
	private String shopName;
	

	public Long getCartId() {
		return cartId;
	}

	public Long getProductId() {
		return productId;
	}

	public String getShopName() {
		return shopName;
	}
	
}
