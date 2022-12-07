package edu.uoc.pac2;

import static java.lang.String.*;

public class TextAnalysis {

    //These symbols are considered as a sentence-end symbol.
    private static final String[] SENTENCE_END_SYMBOLS = {"?", "!", ";", ":"};

    private String text;

    public TextAnalysis(String text) {
        this.text = text;
    }


    public String getText(){
        return text;
    }

    /**
     * Method that replaces all signs of the SENTENCE_ENDSYMBOLS attribute with the symbol "."..
     * @return text as a text string with end-of-sentence characters normalized.
     */
    public String normalizeSentenceEndSymbols() {
        //TODO

        for (String symbol : SENTENCE_END_SYMBOLS) {
            text = text.replace(symbol, ".");
        }
        return text;
    }

    /**
     * Getter method that returns a String array with the words contained in text in lowercase,
     * without punctuation marks and without blank spaces at the beginning and end.
     * @return bagOfWords as a text string with end-of-sentence characters normalized.
     */
    public String[] getBagOfWords() {
        //TODO

        String textWithoutPunctuation = text.toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "").trim().replaceAll("( )+", " ");

        String[] words = textWithoutPunctuation.split(" ");

        return words;

    }

    /**
     * Getter method that calculates the average number of words per sentence after normalizing the signs.
     * @return averageSentenceLength as a double value.
     */
    public double getAverageSentenceLength() {
        //TODO

        normalizeSentenceEndSymbols();
        String[] words = getBagOfWords();

        double wordsCount = words.length;

        String sentences[] = text.split("\\.");
        double counterSentences = sentences.length;

        return wordsCount / counterSentences;

    }

    /**
     * Method that checks if the word is included in the text.
     * @param pattern as a String value.
     * @return true if the word is included in the text, false otherwise.
     */
    public boolean isIncluded(String pattern){
        //TODO

        String[] letters = pattern.split("");
        StringBuilder textBuilder = new StringBuilder(text);

        for (String letter : letters) {

            if (!textBuilder.toString().contains(letter)) {
                return false;
            }
            textBuilder.deleteCharAt(textBuilder.indexOf(letter));
        }
        return true;

    }

}
