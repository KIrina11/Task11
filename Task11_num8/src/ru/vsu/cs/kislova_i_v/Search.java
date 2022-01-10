package ru.vsu.cs.kislova_i_v;

import java.util.ArrayList;
import java.util.List;

public class Search {
    public static List<String> sortedText(String[] sortText) {
        List<String> namesFromText = new ArrayList<>();
        String word1 = sortText[0];
        String word2 = sortText[1];
        for (int i = 2; i < sortText.length - 2; i++) {
            String word3 = sortText[i];
            if (checkWord(word1) && checkWord(word2) && checkWord(word3)) {
                namesFromText.add(word1 + " ");
                namesFromText.add(word2 + " ");
                namesFromText.add(word3);
                i++;
                word1 = sortText[i];
                i++;
                word2 = sortText[i];
            } else {
                word1 = word2;
                word2 = word3;
            }
        }
        return namesFromText;

    }

    private static boolean checkWord(String word) {
        String[] RU = {"À", "Á", "Â", "Ã", "Ä", "Å", "¨", "Æ", "Ç", "È", "É",
                "Ê", "Ë", "Ì", "Í", "Î", "Ï", "Ð", "Ñ", "Ò", "Ó", "Ô", "Õ", "Ö", "×", "Ø", "Ù", "Ý", "Þ", "ß"};
        for (int i = 0; i < RU.length; i++) {
            if (word.startsWith(RU[i])) {
                return true;
            }
        }
        return false;
    }
}
