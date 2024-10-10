package uptc.edu.co.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
	private JTextField nameField;
	private JComboBox<String> priorityComboBox;
	private JButton addButton;
	private JButton takeButton;

	public View() {
		nameField = new JTextField(20);
		priorityComboBox = new JComboBox<>(new String[] { "Alto", "Medio", "Bajo" });
		addButton = new JButton("Agregar Turno");
		takeButton = new JButton("Atender");

		this.setTitle("Sala de espera");
		this.setSize(400, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(new JLabel("Nombre:"), gbc);

		gbc.gridx = 1;
		gbc.weightx = 1.0;
		panel.add(nameField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0;
		panel.add(new JLabel("Prioridad:"), gbc);

		gbc.gridx = 1;
		panel.add(priorityComboBox, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(takeButton, gbc);

		gbc.gridx = 1;
		panel.add(addButton, gbc);

		this.add(panel);
		this.setVisible(true);
	}

	public String getNombre() {
		return nameField.getText();
	}

	public int getPrioridad() {
		String prioridadTexto = (String) priorityComboBox.getSelectedItem();
		return priorityToInt(prioridadTexto);
	}

	private int priorityToInt(String priority) {
		switch (priority) {
		case "Alto":
			return 1;
		case "Medio":
			return 2;
		case "Bajo":
			return 3;
		default:
			throw new IllegalArgumentException("Prioridad desconocida: " + priority);
		}
	}

	public void addAddButtonListener(ActionListener listener) {
		addButton.addActionListener(listener);
	}

	public void addTakeButtonListener(ActionListener listener) {
		takeButton.addActionListener(listener);
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
}
