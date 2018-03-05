package alexph90.kata.checkout;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple implementation to store items in an in-memory map.
 * @author Alex
 *
 */
public class InMemoryItemStore implements ItemStore {

	Map<StockKeepingUnit, Item> itemMap = new HashMap<>();
	
	@Override
	public void addItem(Item item) {
		itemMap.put(item.getStockKeepingUnit(), item);
	}

	@Override
	public void deleteItemByStockKeepingUnit(StockKeepingUnit stockKeepingUnit) {
		itemMap.remove(stockKeepingUnit);
	}

	@Override
	public Item getItemByStockKeepingUnit(StockKeepingUnit stockKeepingUnit) throws ItemNotFoundException {
		Item item = itemMap.get(stockKeepingUnit);
		
		if (item == null) throw new ItemNotFoundException("Item with SKU " + stockKeepingUnit + " not found!");
		
		return item;
	}

}
