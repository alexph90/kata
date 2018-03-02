package alexph90.kata.checkout;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

public class MultiBuyPricingRuleTest {

	private Item itemA = new Item("A", new BigDecimal("50"));
	private Item itemB = new Item("B", new BigDecimal("30"));
	private Item itemC = new Item("C", new BigDecimal("20"));
	private List<Item> items = new ArrayList<>();
	private MultiBuyPricingRule ruleA;
	private MultiBuyPricingRule ruleB;
	
	@Before
	public void setUp() {
		
		IntStream.range(1, 5)
					.forEach(i -> items.add(itemA));
		
		IntStream.range(1, 3)
					.forEach(i -> items.add(itemB));
		
		IntStream.range(1, 2)
					.forEach(i -> items.add(itemC));
		
		ruleA = new MultiBuyPricingRule(itemA, 3, new BigDecimal("130"));
		ruleB = new MultiBuyPricingRule(itemB, 2, new BigDecimal("45"));
	}
	
	@Test
	public void whenRuleAAppliedThenDiscountShouldBeCorrect() {
		assertThat(ruleA.getDiscount(items), is(equalTo(new BigDecimal("20"))));
	}
	
	@Test
	public void whenRuleBAppliedThenDiscountShouldBeCorrect() {
		assertThat(ruleB.getDiscount(items), is(equalTo(new BigDecimal("15"))));
	}

}
