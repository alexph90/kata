package alexph90.kata.checkout;

import java.math.BigDecimal;
import java.util.List;

public class MultiBuyPricingRule implements PricingRule{

	private final Item item;
	private final int multiBuyQuantity;
	private final BigDecimal groupDiscount;
	
	/**
	 * Create a new {@code MultiBuyPricingRule} for the given item, quantity and special
	 * price for that quantity
	 * @param item
	 * @param multiBuyQuantity
	 * @param priceForQuanity
	 */
	public MultiBuyPricingRule(Item item, int multiBuyQuantity, BigDecimal priceForQuanity) {
		this.item = item;
		this.multiBuyQuantity = multiBuyQuantity;
		
		BigDecimal regularPriceForOne = item.getPrice();
		BigDecimal regularPriceForGroup = regularPriceForOne.multiply(BigDecimal.valueOf(multiBuyQuantity));
		this.groupDiscount = regularPriceForGroup.subtract(priceForQuanity);
	}
	
	@Override
	public BigDecimal getDiscount(List<Item> items) {
		
		// Casting long to int as very unlikely to be larger
		int applicableItemCount = (int) items
											.stream()
											.filter(i -> i.getSku().equals(item.getSku()))
											.count();
		
		int multibuyGroups = applicableItemCount / multiBuyQuantity;
		
		// Multiply the discount for one group by the number of groups to get the total discount
		
		return groupDiscount.multiply(BigDecimal.valueOf(multibuyGroups));
	}

}
