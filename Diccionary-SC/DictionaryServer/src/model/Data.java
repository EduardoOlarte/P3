package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import structure.BinaryTree;
import structure.NodeTree;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Data {
	private List<Word> dataEs;
	private List<Word> dataEn;
	private List<Word> dataFr;
	private BinaryTree<Word> dictionaryEsEn;
	private BinaryTree<Word> dictionaryEnFr;

	public Data() {
		dataEs = new ArrayList<>();
		dataEn = new ArrayList<>();
		dataFr = new ArrayList<>();
		dictionaryEsEn = new BinaryTree<>((w1, w2) -> w1.getOriginal().compareTo(w2.getOriginal()));
		dictionaryEnFr = new BinaryTree<>((w1, w2) -> w1.getOriginal().compareTo(w2.getOriginal()));
	}

	public void loadDictionaries(String[] files) throws Exception {
		JSONParser parser = new JSONParser();

		for (String file : files) {
			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(file));

			JSONArray array = (JSONArray) jsonObject.get("animals");

			for (Object obj : array) {
				JSONObject wordObj = (JSONObject) obj;
				String name = (String) wordObj.get("name");
				Word word = new Word(name, null);

				if (file.contains("es")) {
					dataEs.add(word);
				} else if (file.contains("en")) {
					dataEn.add(word);
				} else if (file.contains("fr")) {
					dataFr.add(word);
				}
			}
		}

		for (int i = 0; i < dataEn.size(); i++) {
			Word enWord = dataEn.get(i);
			Word esWord = dataEs.get(i);
			Word frWord = dataFr.get(i);
			dictionaryEsEn.add(new Word(enWord.getOriginal(), esWord.getOriginal()));
			dictionaryEnFr.add(new Word(enWord.getOriginal(), frWord.getOriginal()));
		}
	}

	public void createDicionaries() {
	}

	public String findWord(String word, int type) {
		if (type == 0) {
			NodeTree<Word> resultNode = dictionaryEsEn.search(new Word(word, ""));
			if (resultNode == null) {
				return "Palabra no encontrada";
			}
			return resultNode.getData().getTranslation();
		} else if (type == 1) {
			NodeTree<Word> resultNode = dictionaryEnFr.search(new Word(word, ""));
			if (resultNode == null) {
				return "Palabra no encontrada";
			}
			return resultNode.getData().getTranslation();
		} else {
			return "Tipo de diccionario no válido";
		}
	}

	public boolean addWord(String original, String translation, int type) {
		if (type == 0) {
			if (dictionaryEsEn.search(new Word(original, "")) != null) {
				return false;
			}
			return dictionaryEsEn.add(new Word(original, translation));
		} else if (type == 1) {
			if (dictionaryEnFr.search(new Word(original, "")) != null) {
				return false;
			}
			return dictionaryEnFr.add(new Word(original, translation));
		} else {
			return false;
		}
	}

	public int size(int type) {
		if (type == 0) {
			return dictionaryEsEn.size();
		} else if (type == 1) {
			return dictionaryEnFr.size();
		} else {
			return 0;
		}
	}
}