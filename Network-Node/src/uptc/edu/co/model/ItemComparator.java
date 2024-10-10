package uptc.edu.co.model;

import java.util.Comparator;

public class ItemComparator implements Comparator<Item> {
    @Override
    public int compare(Item item1, Item item2) {
        return Integer.compare(item1.getCode(), item2.getCode());
    }
}
