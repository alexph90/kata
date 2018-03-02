package alexph90.kata.checkout;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple implementation to store items in an in-memory map.
 * @author Alex
 *
 */
public class InMemoryItemStore implements ItemStore {

	Map<String, Item> itemMap = new HashMap<>();
	
	@Override
	public void addItem(Item item) {
		itemMap.put(item.getSku(), item);
	}

	@Override
	public void deleteItemBySku(String sku) {
		itemMap.remove(sku);
	}

	@Override
	public Item getItemBySku(String sku) throws ItemNotFoundException {
		Item item = itemMap.get(sku);
		
		if (item == null) throw new ItemNotFoundException("Item with SKU " + sku + " not found!");
		
		return item;
	}

}
