package org.irri.iric.ds.utils;

public class TextSearchOptions {

	// text string is a regular expression
	private boolean isRegex = false;

	// text string should not be prefixed/suffixed with an alphanumeric character
	private boolean isWholeWord = true;

	// word is exact, case insensitive
	private boolean isExact = false;

	private String text = "";

	public TextSearchOptions() {
		super();

	}

	public TextSearchOptions(String text) {
		super();

		this.text = text.trim();

	}

	public TextSearchOptions(String text, boolean isRegex, boolean isWholeWord, boolean isExact) {
		super();
		this.isRegex = isRegex;
		this.isWholeWord = isWholeWord;
		this.text = text.trim();
		this.isExact = isExact;
	}

	public boolean isRegex() {
		return isRegex;
	}

	public boolean isWholeWord() {
		return isWholeWord;
	}

	public String getText() {
		if (isRegex())
			return text; // .replace("\\", "\\\\"); //.replace(".", "\\.").replace("\\-", "\\-");
		else if (isWholeWord()) {
			return "\\y" + text + "\\y";
		}
		return text;

	}

	public boolean isExact() {
		return isExact;
	}

}
