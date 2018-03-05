package alexph90.kata.checkout;

import static alexph90.kata.checkout.TestConstants.A;
import static alexph90.kata.checkout.TestConstants.B;
import static alexph90.kata.checkout.TestConstants.C;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;

public class PercentageOffPricingRuleTest {

	private Item itemA = new Item(A, new BigDecimal("50"));
	private Item itemB = new Item(B, new BigDecimal("30"));
	private Item itemC = new Item(C, new BigDecimal("20"));
	private List<Item> items = new ArrayList<>();
	private PercentageOffPricingRule ruleA;
	private PercentageOffPricingRule ruleB;
	
	@Before
	public void setUp() {
		
		IntStream.range(1, 5)
					.forEach(i -> items.add(itemA));
		
		IntStream.range(1, 3)
					.forEach(i -> items.add(itemB));
		
		IntStream.range(1, 2)
					.forEach(i -> items.add(itemC));
		
		ruleA = new PercentageOffPricingRule(BigDecimal.valueOf(50), item -> item.getStockKeepingUnit().equals(A));
		ruleB = new PercentageOffPricingRule(new BigDecimal("0.5"), item -> item.getStockKeepingUnit().equals(A));
	}
	
	@Test
	public void whenRuleAAppliedThenDiscountShouldBeCorrect() {
		
		
		
	}

}
