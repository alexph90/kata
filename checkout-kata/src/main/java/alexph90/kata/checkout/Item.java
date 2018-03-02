package alexph90.kata.checkout;

import java.math.BigDecimal;

public final class Item {
	
	private final String sku;
	private final BigDecimal price;
	
	public Item(String sku, BigDecimal price) {
		this.sku = sku;
		this.price = price;
	}
	
	public String getSku() {
		return sku;
	}

	public BigDecimal getPrice() {
		return price;
	}	
	
}
