package alexph90.kata.checkout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Checkout {
	
	private final ItemStore itemStore;
	private final List<PricingRule> pricingRules;
	private final List<Item> basket = new ArrayList<>();
	
	/**
	 * Create a new {@code Checkout} with the given {@code ItemStore} and no pricing rules.
	 * 
	 * @param itemStore
	 */
	public Checkout(ItemStore itemStore) {
		this.itemStore = itemStore;
		pricingRules = Collections.emptyList();
	}
	
	/**
	 * Create a new {@code Checkout} with the given {@code ItemStore} and pricing rules.
	 * 
	 * @param itemStore
	 * @param pricingRules
	 */
	public Checkout(ItemStore itemStore, List<PricingRule> pricingRules) {
		this.itemStore = itemStore;
		this.pricingRules = pricingRules;
	}
	
	/**
	 * Scan an item, passing its SKU (Stock Keeping Unit) code. The checkout will
	 * fetch the {@code Item} from the {@code ItemStore} and add it to the basket.
	 * 
	 * @param sku The SKU (Stock Keeping Unit) code to uniquely identify the item
	 * @throws ItemNotFoundException if the {@code Item} with the given SKU is not 
	 * present in the {@code ItemStore}
	 */
	public void scan(String sku) throws ItemNotFoundException {
		Item item = itemStore.getItemBySku(sku);
		basket.add(item);
	}
	
	/**
	 * Calculate the total cost of the current basket
	 * 
	 * @return the current total
	 */
	public BigDecimal calculateTotal() {
		BigDecimal total = BigDecimal.ZERO;
		
		for (Item i : basket) {
			total = total.add(i.getPrice());
		}
		
		BigDecimal discount = BigDecimal.ZERO;
		
		for (PricingRule rule : pricingRules) {
			discount = discount.add(rule.getDiscount(basket));
		}
		
		return total.subtract(discount);
	}

}
