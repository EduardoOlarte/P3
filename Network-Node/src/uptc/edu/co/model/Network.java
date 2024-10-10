package uptc.edu.co.model;

import java.util.LinkedHashSet;

public class Network {
	private String name;
	private LinkedHashSet<Store> stores;

	public Network(String name) {
		this.name = name;
		this.stores = new LinkedHashSet<>();
	}

	public void addStore(Store store) {
		stores.add(store);
	}

	public double networkValueInventory() {
		double totalValue = 0;
		for (Store store : stores) {
			totalValue += store.storeValueInventory();
		}
		return totalValue;
	}

	public Store getStore(int index) {
		if (index >= 0 && index < stores.size()) {
			return stores.toArray(new Store[0])[index];
		}
		return null;
	}
	
    public int getStoreCount() {
        return stores.size();
    }
	
	
}
