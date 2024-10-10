package control;

import model.Dictionary;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Control {
	private Dictionary dictionary;
	private View view;

	public Control(Dictionary dictionary, View view) throws Exception {
		this.dictionary = dictionary;
		this.view = view;
		initController();
		view.setVisible(true);
	}

	private void initController() throws Exception {
		String[] files = { "src/diccionarios/en.json", "src/diccionarios/es.json", "src/diccionarios/fr.json" };
		dictionary.loadData(files);

		view.getTranslationButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				translateWord();
			}
		});

		view.getAddButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addWord();
			}
		});

		view.getInfoButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showDictionariesInfo();
			}
		});
	}

	private void translateWord() {
		String word = view.getWordField().getText().trim();
		int type = view.getTranslationComboBox().getSelectedIndex();

		if (word.isEmpty()) {
			view.showMessage("Por favor, ingrese una palabra para traducir.");
			return;
		}

		String translation = dictionary.translate(word, type);

		if (translation != null) {
			view.showTranslation(translation);
		} else {
			view.showMessage("La palabra no se encontró en el diccionario.");
		}
	}

	private void addWord() {
		String original = view.getOriginalField().getText().trim();
		String translation = view.getTranslationField().getText().trim();
		int type = view.getAddComboBox().getSelectedIndex();

		if (original.isEmpty() || translation.isEmpty()) {
			view.showMessage("Por favor, complete ambos campos (Palabra y Traducción).");
			return;
		}

		boolean success = dictionary.addWord(original, translation, type);
		if (success) {
			view.showMessage("Palabra agregada correctamente.");
		} else {
			view.showMessage("Error al agregar la palabra.");
		}
	}

	private void showDictionariesInfo() {
		StringBuilder info = new StringBuilder();
		info.append("Tamaño del diccionario ").append("En-Es").append(": ").append(dictionary.sizeDictionaries(0))
				.append("\n");
		info.append("Tamaño del diccionario ").append("En-Fr").append(": ").append(dictionary.sizeDictionaries(1))
				.append("\n");

		view.showMessage(info.toString());
	}
}
