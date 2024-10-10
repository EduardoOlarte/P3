package uptc.edu.co.presenter;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import uptc.edu.co.model.Item;
import uptc.edu.co.model.Network;
import uptc.edu.co.model.Store;
import uptc.edu.co.view.View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Presenter {

	private Network network;
	private View view;

	public Presenter(Network network) {
		this.network = network;
		this.view = new View(this);
	}

	public void addStore() {
		String name = JOptionPane.showInputDialog("Ingrese el nombre de la tienda:");
		String address = JOptionPane.showInputDialog("Ingrese la dirección de la tienda:");

		Store store = new Store(name, address);
		network.addStore(store);

		JOptionPane.showMessageDialog(null,
				"Tienda agregada exitosamente:\nNombre: " + name + "\nDirección: " + address);
	}

	public void displayNetworkValue() {
		double networkValue = network.networkValueInventory();
		JOptionPane.showMessageDialog(null, "El valor total del inventario en la red es: " + networkValue);
	}
	
    public void displayStoreInventoryValue(Store store) {
        if (store != null) {
            double storeValue = store.storeValueInventory();
            JOptionPane.showMessageDialog(null, String.format("El valor del inventario en la tienda %s es: %.2f", store.getName(), storeValue));
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró la tienda con el nombre proporcionado.");
        }
    }

	public void addItemToStore(Store store) {
		int code = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el código del artículo:"));
		String name = JOptionPane.showInputDialog("Ingrese el nombre del artículo:");
		int supply = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad en stock:"));
		double unitValue = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor unitario:"));

		Item item = new Item(code, name, supply, unitValue);
		store.addItem(item);

		JOptionPane.showMessageDialog(null, "Artículo agregado exitosamente a la tienda " + store.getName()
				+ ":\nCódigo: " + code + "\nNombre: " + name);
	}

	public void recordSaleInStore(Store store) {
		int code = Integer
				.parseInt(JOptionPane.showInputDialog("Ingrese el código del artículo para registrar la venta:"));
		int sale = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad vendida:"));

		store.recordSale(code, sale);

		JOptionPane.showMessageDialog(null, "Venta registrada exitosamente para el artículo con código " + code
				+ " en la tienda " + store.getName());
	}

	public void removeItemRangeInStore(Store store) {
		int from = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el rango inferior de códigos a eliminar:"));
		int to = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el rango superior de códigos a eliminar:"));

		store.removeItemRange(from, to);

		JOptionPane.showMessageDialog(null, "Artículos eliminados exitosamente en la tienda " + store.getName()
				+ " en el rango de códigos " + from + " a " + to);
	}

	public Collection<Item> showAllItemsInStore(Store store) {
	    Collection<Item> items = store.showAllItems();

	    if (items.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "La tienda no tiene ningún artículo.");
	    } else {
	        displayItems(items);
	    }

	    return items;
	}
	public void viewItemInStore(Store store) {
        if (store != null) {
            List<String> itemCodes = new ArrayList<>();
            for (Item item : store.showAllItems()) {
                itemCodes.add(String.valueOf(item.getCode()));
            }

            if (itemCodes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "La tienda no tiene ningún artículo.");
            } else {
                JComboBox<String> itemCodeList = new JComboBox<>(itemCodes.toArray(new String[0]));
                JOptionPane.showOptionDialog(null, itemCodeList, "Seleccionar Artículo por Codigo",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

                String selectedCode = (String) itemCodeList.getSelectedItem();
                int code = Integer.parseInt(selectedCode);
                Item item = store.viewItem(code);

                if (item != null) {
                    String itemDetails = String.format("Detalles del artículo:\nCódigo: %d\nNombre: %s\nStock: %d\nValor Unitario: %.2f",
                            item.getCode(), item.getName(), item.getSupply(), item.getUnitValue());

                    JOptionPane.showMessageDialog(null, itemDetails);
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró el artículo con el código proporcionado en la tienda " + store.getName());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró la tienda con el nombre proporcionado.");
        }
    }
	
    private void displayItems(Collection<Item> items) {
        if (items.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La tienda no tiene ningún artículo.");
        } else {
            StringBuilder message = new StringBuilder("Artículos en la tienda:\n");

            for (Item item : items) {
                message.append("Código: ").append(item.getCode()).append("\n");
                message.append("Nombre: ").append(item.getName()).append("\n");
                message.append("Stock: ").append(item.getSupply()).append("\n");
                message.append("Valor Unitario: ").append(item.getUnitValue()).append("\n\n");
            }

            JOptionPane.showMessageDialog(null, message.toString());
        }
    }
    
	public Network getNetwork() {
		return network;
	}

	public void showMenu() {
		view.showMenu();
	}
	
	public void loadData() {
        Store store1 = new Store("Tienda 1", "Dirección 1");
        Store store2 = new Store("Tienda 2", "Dirección 2");

        network.addStore(store1);
        network.addStore(store2);

        Item item1 = new Item(1, "Artículo A", 50, 10.0);
        Item item2 = new Item(2, "Artículo B", 30, 15.0);
        Item item3 = new Item(3, "Artículo C", 20, 25.0);

        store1.addItem(item1);
        store1.addItem(item2);
        store2.addItem(item3);
	}

	public static void main(String[] args) {
		Network network = new Network("Mi Red");
		Presenter presenter = new Presenter(network);
		presenter.loadData();
		presenter.showMenu();
		
	}
}
