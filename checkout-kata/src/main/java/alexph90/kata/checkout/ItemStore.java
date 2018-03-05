package alexph90.kata.checkout;

public interface ItemStore {

	/**
	 * Add the given item to the item store.
	 * @param item
	 */
	void addItem(Item item);
	
	/**
	 * Delete the item with the given SKU (if it exists) from the item store.
	 * @param sku
	 */
	void deleteItemByStockKeepingUnit(StockKeepingUnit stockKeepingUnit);
	
	/**
	 * Get the item with the given SKU from the item store.
	 * @param sku
	 * @return
	 * @throws ItemNotFoundException if the item does not exist
	 */
	Item getItemByStockKeepingUnit(StockKeepingUnit stockKeepingUnit) throws ItemNotFoundException;
	
}
