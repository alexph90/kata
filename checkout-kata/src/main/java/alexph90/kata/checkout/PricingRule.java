package alexph90.kata.checkout;

import java.math.BigDecimal;
import java.util.List;

public interface PricingRule {

	/**
	 * Get the discount from applying this pricing rule to a list of items.
	 * @param items
	 * @return
	 */
	BigDecimal getDiscount(List<Item> items);
	
}
