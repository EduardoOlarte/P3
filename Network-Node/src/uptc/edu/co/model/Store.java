package uptc.edu.co.model;

import java.util.Collection;
import java.util.TreeSet;

public class Store {
	private String name;
	private String address;
	private TreeSet<Item> items;

	public Store(String name, String address) {
		this.name = name;
		this.address = address;
		this.items = new TreeSet<>(new ItemComparator());
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public Item viewItem(int code) {
		for (Item item : items) {
			if (item.getCode() == code) {
				return item;
			}
		}
		return null;
	}

	public double storeValueInventory() {
		double totalValue = 0;
		for (Item item : items) {
			totalValue += item.getSupply() * item.getUnitValue();
		}
		return totalValue;
	}

	public void recordSale(int code, int sale) {
		Item item = viewItem(code);
		if (item != null) {
			int updatedSupply = item.getSupply() - sale;
			item.setSupply(updatedSupply);
		}
	}

	public void removeItemRange(int from, int to) {
		items.removeIf(item -> item.getCode() >= from && item.getCode() <= to);
	}

	public Collection<Item> showAllItems() {
		return items;
	}

	public String getName() {
		return name;
	}
	
}
