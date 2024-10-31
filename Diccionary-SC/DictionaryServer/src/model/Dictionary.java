package model;

public class Dictionary {
	private Data dictionaries;

	public Dictionary() {
		this.dictionaries = new Data();
	}

	public void loadData(String[] files) throws Exception {
		dictionaries.loadDictionaries(files);
	}

	public String translate(String word, int type) {
		return dictionaries.findWord(word, type);
	}

	public boolean addWord(String original, String translation, int type) {
		return dictionaries.addWord(original, translation, type);
	}

	public int sizeDictionaries(int dictionary) {
		return dictionaries.size(dictionary);
	}
}