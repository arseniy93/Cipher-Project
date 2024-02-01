package com.javarush.cryptoanalyzer.analyzer;

import com.javarush.cryptoanalyzer.exaption.AllExeptions;

public class CaesarCipher {
    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е','ё', 'ж', 'з',
            'и','й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э','ю', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' ','(',')','1','2','3','4','5','6',
    '7','8','9','0','/',',','—','-','\n','\r'};
    private static final String[] METHODS = {"Cezar", "BruteForce", "Static"};

    public StringBuilder toCipher(String message, int key) {
        char[] charsFromMessage = message.toLowerCase().toCharArray();
        StringBuilder messageCipher = new StringBuilder();
        for (int i = 0; i < charsFromMessage.length; i++) {

            if (!checkLetter(charsFromMessage[i])) {

                try {
                    throw new AllExeptions("The letter/symbol: " + "'" + charsFromMessage[i] + "'" + ", number of this letter/symbol = "
                            + (i + 1) + " is not involved in ALPHABET array");
                } catch (AllExeptions e) {
                    throw new RuntimeException(e);
                }

            }
            if (charsFromMessage[i]=='\n' ){
                messageCipher.append('\n');
            }
            else if(charsFromMessage[i]=='\r'){
                messageCipher.append('\r');
            }
            else{
                messageCipher.append(ALPHABET[(getIndex(charsFromMessage[i]) + key) % ALPHABET.length]);
            }

        }
        return messageCipher;
    }

    //Cesar unChipher
    public StringBuilder toUnCipher(String messageCipher, int key) {
        char[] charsFromMessage = messageCipher.toLowerCase().toCharArray();
        StringBuilder messageUnCipher = new StringBuilder();
        for (int i = 0; i < charsFromMessage.length; i++) {
            if (!checkLetter(charsFromMessage[i])) {
                try {
                    throw new AllExeptions("The letter/symbol: " + "'" + charsFromMessage[i] + "'" + ", number of this letter/symbol = "
                            + (i + 1) + " is not involved in ALPHABET array");
                } catch (AllExeptions e) {
                    throw new RuntimeException(e);
                }
            }
            if ((key > getIndex(charsFromMessage[i]))) {
                messageUnCipher.append(ALPHABET[(ALPHABET.length - key + getIndex(charsFromMessage[i]))]);
            } else {
                messageUnCipher.append((ALPHABET[Math.abs(getIndex(charsFromMessage[i]) - key) % ALPHABET.length]));
            }
        }
        return messageUnCipher;
    }

    //TODO  statistic
    //*Brute Force - Wuzuuup
    public int getKeyFromCipherText(String text, String fileWithUnChiperText) {
        if (text.length() != fileWithUnChiperText.length()) {
            int quantityOfLetters = text.length() > fileWithUnChiperText.length() ? text.length() : fileWithUnChiperText.length();
            String number = text.length() > fileWithUnChiperText.length() ? "first text" : "second text";
            try {
                throw new AllExeptions("The quantity of letters aren't coincidenced: " + number + " = "
                        + quantityOfLetters + " more than " + (Math.abs(text.length() - fileWithUnChiperText.length())));
            } catch (AllExeptions e) {
                throw new RuntimeException(e);
            }
        }
        int key = 0;
        StringBuilder textToUnCipher;
        for (int i = 1; i < ALPHABET.length; i++) {
            textToUnCipher = toUnCipher(fileWithUnChiperText, i);
            if (textToUnCipher.toString().equalsIgnoreCase(text)) {
                key = i;
                break;
            }
        }
        return key;
    }

    //get index of
    private int getIndex(char letter) {
        int indexLetter = 0;
        for (int i = 0; i < ALPHABET.length; i++) {
            if (letter == ALPHABET[i]) {
                indexLetter = i;
            }
        }
        return indexLetter;
    }

    // check: if letter involve in ALPHABET array
    private boolean checkLetter(char letter) {
        boolean law = false;
        for (int i = 0; i < ALPHABET.length; i++) {
            if (letter == ALPHABET[i]) {
                law = true;
            }
        }
        return law;
    }
}
