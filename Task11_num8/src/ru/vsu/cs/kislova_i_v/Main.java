package ru.vsu.cs.kislova_i_v;

import util.SwingUtils;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String str = SwingUtils.readStrFromFile("C:\\Users\\Ирина\\IdeaProjects\\Task11_num8\\inputTextForExample.txt");
        String[] sortText = str.split(" ");
        SwingUtils.printListInConsole(Search.sortedText(sortText));
    }
}
