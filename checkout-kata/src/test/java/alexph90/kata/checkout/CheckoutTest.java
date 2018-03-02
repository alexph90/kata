package alexph90.kata.checkout;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CheckoutTest {
	
	private final String A = "A";
	private final String B = "B";
	private final String C = "C";
	private final String D = "D";
	private final Item itemA = new Item(A, BigDecimal.valueOf(50));
	private final Item itemB = new Item(B, BigDecimal.valueOf(30));
	private final Item itemC = new Item(C, BigDecimal.valueOf(20));
	private final Item itemD = new Item(D, BigDecimal.valueOf(15));
	private final ItemStore itemStore = new InMemoryItemStore();
	private final Checkout checkoutNoDiscount = new Checkout(itemStore);
	private final List<PricingRule> pricingRules = new ArrayList<>();
	private final Checkout checkoutWithMultiBuy = new Checkout(itemStore, pricingRules);
	
	@Before
	public void setUp() {
		itemStore.addItem(itemA);
		itemStore.addItem(itemB);
		itemStore.addItem(itemC);
		itemStore.addItem(itemD);
		
		pricingRules.add(new MultiBuyPricingRule(itemA, 3, BigDecimal.valueOf(130)));
		pricingRules.add(new MultiBuyPricingRule(itemB, 2, BigDecimal.valueOf(45)));
	}
	
	@Test
	public void givenCheckoutWithNoDiscountWhenItemsAreScannedThenTotalShouldIncrease() throws ItemNotFoundException {	
		checkoutNoDiscount.scan(A);
		assertThat(checkoutNoDiscount.calculateTotal(), is(equalTo(BigDecimal.valueOf(50))));
		
		checkoutNoDiscount.scan(B);
		assertThat(checkoutNoDiscount.calculateTotal(), is(equalTo(BigDecimal.valueOf(80))));
		
		checkoutNoDiscount.scan(C);
		assertThat(checkoutNoDiscount.calculateTotal(), is(equalTo(BigDecimal.valueOf(100))));
		
		checkoutNoDiscount.scan(D);
		assertThat(checkoutNoDiscount.calculateTotal(), is(equalTo(BigDecimal.valueOf(115))));
	}
	
	@Test
	public void givenCheckoutWithMultibuyWhenItemsAreScannedThenTotalShouldIncreaseWithDiscount() throws ItemNotFoundException {	
		checkoutWithMultiBuy.scan(A);
		assertThat(checkoutWithMultiBuy.calculateTotal(), is(equalTo(BigDecimal.valueOf(50))));
		
		checkoutWithMultiBuy.scan(A);
		assertThat(checkoutWithMultiBuy.calculateTotal(), is(equalTo(BigDecimal.valueOf(100))));
		
		checkoutWithMultiBuy.scan(A);
		assertThat(checkoutWithMultiBuy.calculateTotal(), is(equalTo(BigDecimal.valueOf(130))));
		
		checkoutWithMultiBuy.scan(A);
		assertThat(checkoutWithMultiBuy.calculateTotal(), is(equalTo(BigDecimal.valueOf(180))));
		
		checkoutWithMultiBuy.scan(A);
		assertThat(checkoutWithMultiBuy.calculateTotal(), is(equalTo(BigDecimal.valueOf(230))));
		
		checkoutWithMultiBuy.scan(A);
		assertThat(checkoutWithMultiBuy.calculateTotal(), is(equalTo(BigDecimal.valueOf(260))));
	}
	
	@Test
	public void givenCheckoutWithMultiBuyWhenMultipleDifferentItemsAreScannedThenTotalShouldIncreaseWithDiscount() throws ItemNotFoundException {
		scanMultiple(checkoutWithMultiBuy, A, B, C, D, A, C, B, A, B);
		assertThat(checkoutWithMultiBuy.calculateTotal(), is(equalTo(BigDecimal.valueOf(260))));
	}
	
	@Test(expected = ItemNotFoundException.class)
	public void whenScannedItemDoesNotExistThenThrowException() throws ItemNotFoundException {
		checkoutNoDiscount.scan("E");
	}
	
	// Helper method for brevity
	private void scanMultiple(Checkout checkout, String... skus) throws ItemNotFoundException {
		
		for (String sku : skus) {
			checkout.scan(sku);
		}
	}

}
