package alexph90.kata.checkout;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;

public class PercentageOffPricingRule implements PricingRule {

	private BigDecimal percentageOff;
	private Predicate<Item> condition;
	
	public PercentageOffPricingRule(BigDecimal percentageOff, Predicate<Item> condition) {
		this.percentageOff = percentageOff;
		this.condition = condition;
	}
	
	@Override
	public BigDecimal getDiscount(List<Item> items) {
		
		BigDecimal regularTotal = items.stream()
			.filter(condition)
			.map(Item::getPrice)
			.reduce((price1, price2) -> price1.add(price2))
			.get();
		
		return regularTotal.divide(BigDecimal.valueOf(100)).multiply(percentageOff);
	}

}
