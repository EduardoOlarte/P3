package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class View extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField wordField;
	private JTextField originalField;
	private JTextField translationField;
	private JButton translationButton;
	private JButton addButton;
	private JButton infoButton;
	private JComboBox<String> translationComboBox;
	private JComboBox<String> addComboBox;

	public View() {
		setBackground(new Color(192, 192, 192));
		setIconImage(Toolkit.getDefaultToolkit().getImage("Data/icono.JPEG"));
		setTitle("Dicc");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 225, 470);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(201, 193, 182));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel translationLabel = new JLabel("Traductor");
		translationLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		translationLabel.setBounds(64, 11, 86, 31);
		contentPane.add(translationLabel);

		wordField = new JTextField();
		wordField.setBounds(11, 58, 187, 31);
		contentPane.add(wordField);
		wordField.setColumns(10);

		JLabel englishLabel = new JLabel("Ingles");
		englishLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		englishLabel.setBounds(11, 35, 43, 17);
		contentPane.add(englishLabel);

		translationComboBox = new JComboBox<>();
		translationComboBox.addItem("Español");
		translationComboBox.addItem("Frances");
		translationComboBox.setSelectedIndex(0);
		translationComboBox.setMaximumRowCount(2);
		translationComboBox.setBounds(11, 100, 187, 31);
		contentPane.add(translationComboBox);

		translationButton = new JButton("Traducir");
		translationButton.setBounds(37, 142, 127, 35);
		contentPane.add(translationButton);

		JLabel addWordLabel = new JLabel("Agregar Palabra");
		addWordLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		addWordLabel.setBounds(11, 188, 111, 17);
		contentPane.add(addWordLabel);

		originalField = new JTextField();
		originalField.setBounds(11, 227, 187, 31);
		contentPane.add(originalField);
		originalField.setColumns(10);

		translationField = new JTextField();
		translationField.setBounds(11, 280, 187, 31);
		contentPane.add(translationField);
		translationField.setColumns(10);

		JLabel originalLabel = new JLabel("Palabra");
		originalLabel.setBounds(11, 205, 111, 22);
		contentPane.add(originalLabel);

		JLabel traductionLabel = new JLabel("Traduccion");
		traductionLabel.setBounds(11, 261, 117, 20);
		contentPane.add(traductionLabel);

		addButton = new JButton("Agregar");
		addButton.setBounds(49, 364, 115, 23);
		contentPane.add(addButton);

		infoButton = new JButton("Info Diccionarios");
		infoButton.setBounds(25, 398, 157, 22);
		contentPane.add(infoButton);

		addComboBox = new JComboBox<>();
		addComboBox.addItem("Ingles-Español");
		addComboBox.addItem("Ingles-Frances");
		addComboBox.setSelectedIndex(0);
		addComboBox.setMaximumRowCount(2);
		addComboBox.setBounds(10, 322, 187, 31);
		contentPane.add(addComboBox);
	}

	public JTextField getWordField() {
		return wordField;
	}

	public JTextField getOriginalField() {
		return originalField;
	}

	public JTextField getTranslationField() {
		return translationField;
	}

	public JButton getTranslationButton() {
		return translationButton;
	}

	public JButton getAddButton() {
		return addButton;
	}

	public JButton getInfoButton() {
		return infoButton;
	}

	public JComboBox<String> getTranslationComboBox() {
		return translationComboBox;
	}

	public JComboBox<String> getAddComboBox() {
		return addComboBox;
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	public void showTranslation(String translation) {
		showMessage("Traducción: " + translation);
	}
}
