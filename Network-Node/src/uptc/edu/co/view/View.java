package uptc.edu.co.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uptc.edu.co.model.Store;
import uptc.edu.co.presenter.Presenter;

public class View {

    private Presenter presenter;

    public View(Presenter presenter) {
        this.presenter = presenter;
    }

    public void showMenu() {
        String[] options = {"Agregar Tienda", "Ver Valor del Inventario", "Agregar Artículo a Tienda",
                "Registrar Venta en Tienda", "Eliminar Artículos en Rango de Tienda", "Mostrar Todos los Artículos en Tienda","Mostrar Valor del Inventario de Tienda", "Visualizar Artículo en Tienda", "Salir"};

        JComboBox<String> optionList = new JComboBox<>(options);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            int result = JOptionPane.showOptionDialog(
                    null,
                    optionList,
                    "Menú de Red",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    null);

            if (result == JOptionPane.CLOSED_OPTION) {
                System.exit(0);
            }

            String selectedOption = (String) optionList.getSelectedItem();

            switch (selectedOption) {
                case "Agregar Tienda":
                    presenter.addStore();
                    break;
                case "Ver Valor del Inventario":
                    presenter.displayNetworkValue();
                    break;
                case "Agregar Artículo a Tienda":
                    presenter.addItemToStore(getSelectedStore());
                    break;
                case "Registrar Venta en Tienda":
                    presenter.recordSaleInStore(getSelectedStore());
                    break;
                case "Eliminar Artículos en Rango de Tienda":
                    presenter.removeItemRangeInStore(getSelectedStore());
                    break;
                case "Mostrar Todos los Artículos en Tienda":
                    presenter.showAllItemsInStore(getSelectedStore());
                    break;
                case "Visualizar Artículo en Tienda":
                    presenter.viewItemInStore(getSelectedStore());
                    break;
                case "Mostrar Valor del Inventario de Tienda":
                    presenter.displayStoreInventoryValue(getSelectedStore());
                    break;
                case "Salir":
                    JOptionPane.showMessageDialog(null, "Saliendo del programa");
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

    private Store findStoreByName(String storeName) {
        for (int i = 0; i < presenter.getNetwork().getStoreCount(); i++) {
            Store store = presenter.getNetwork().getStore(i);
            if (store != null && store.getName().equalsIgnoreCase(storeName)) {
                return store;
            }
        }
        return null;
    }

    private Store getSelectedStore() {
        List<String> storeNames = new ArrayList<>();
        for (int i = 0; i < presenter.getNetwork().getStoreCount(); i++) {
            Store store = presenter.getNetwork().getStore(i);
            if (store != null) {
                storeNames.add(store.getName());
            }
        }

        if (storeNames.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay tiendas disponibles.");
            return null;
        }

        JComboBox<String> storeList = new JComboBox<>(storeNames.toArray(new String[0]));
        JOptionPane.showOptionDialog(null, storeList, "Seleccionar Tienda",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

        String selectedStoreName = (String) storeList.getSelectedItem();
        return findStoreByName(selectedStoreName);
    }
}
