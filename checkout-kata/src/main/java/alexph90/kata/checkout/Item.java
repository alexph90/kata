package alexph90.kata.checkout;

import java.math.BigDecimal;

public final class Item {
	
	private final StockKeepingUnit stockKeepingUnit;
	private final BigDecimal price;
	
	public Item(StockKeepingUnit stockKeepingUnit, BigDecimal price) {
		this.stockKeepingUnit = stockKeepingUnit;
		this.price = price;
	}
	
	public StockKeepingUnit getStockKeepingUnit() {
		return stockKeepingUnit;
	}

	public BigDecimal getPrice() {
		return price;
	}	
	
}
