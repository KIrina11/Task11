package ru.vsu.cs.kislova_i_v;

import org.junit.*;
import util.SwingUtils;

import java.io.FileNotFoundException;
import java.util.List;

public class Tests {
    @BeforeClass
    public static void reportAboutStartOfTests() {
        System.out.println("Testing of the program begins...");
        System.out.println();
    }

    @Before
    public void reportOnTestOperation() {
        System.out.print("Test is running...");
    }

    @Test
    public void testWithNamesOfTheFirstTypeOnly() throws FileNotFoundException {
        String str1 = SwingUtils.readStrFromFile("C:\\Users\\Ирина\\IdeaProjects\\Task11_num8\\inputForTests"
                + "\\input01TestWithNamesOfTheFirstTypeOnly.txt");
        String[] sortText1 = str1.split(" ");
        List<String> listForTest = Search.sortedText(sortText1);
        String str2 = SwingUtils.readStrFromFile("C:\\Users\\Ирина\\IdeaProjects\\Task11_num8"
                + "\\correctResultsFotTests\\correctResult01ForTestWithNamesOfTheFirstType.txt");
        String[] sortText2 = str2.split(" ");
        List<String> correctList = Search.sortedText(sortText2);

        Assert.assertEquals(correctList, listForTest);
    }

    @Test
    public void testWithNamesOfTheSecondTypeOnly() throws FileNotFoundException {
        String str1 = SwingUtils.readStrFromFile("C:\\Users\\Ирина\\IdeaProjects\\Task11_num8\\inputForTests"
                + "\\input02TestWithNamesOfTheSecondTypeOnly.txt");
        String[] sortText1 = str1.split(" ");
        List<String> listForTest = Search.sortedText(sortText1);
        String str2 = SwingUtils.readStrFromFile("C:\\Users\\Ирина\\IdeaProjects\\Task11_num8"
                + "\\correctResultsFotTests\\correctResult02ForTestWithNamesOfTheSecondTypeOnly.txt");
        String[] sortText2 = str2.split(" ");
        List<String> correctList = Search.sortedText(sortText2);

        Assert.assertEquals(correctList, listForTest);
    }

    @Test
    public void testConsistingOfNamesThatGoInRow() throws FileNotFoundException {
        String str1 = SwingUtils.readStrFromFile("C:\\Users\\Ирина\\IdeaProjects\\Task11_num8\\inputForTests"
                + "\\input03TestConsistingOfNamesThatGoInRow.txt");
        String[] sortText1 = str1.split(" ");
        List<String> listForTest = Search.sortedText(sortText1);
        String str2 = SwingUtils.readStrFromFile("C:\\Users\\Ирина\\IdeaProjects\\Task11_num8"
                + "\\correctResultsFotTests\\correctResult03ForTestConsistingOfNamesThatGoInRow.txt");
        String[] sortText2 = str2.split(" ");
        List<String> correctList = Search.sortedText(sortText2);

        Assert.assertEquals(correctList, listForTest);
    }

    @Test
    public void testStandardSituation() throws FileNotFoundException {
        String str1 = SwingUtils.readStrFromFile("C:\\Users\\Ирина\\IdeaProjects\\Task11_num8\\inputForTests"
                + "\\input04TestStandardSituation.txt");
        String[] sortText1 = str1.split(" ");
        List<String> listForTest = Search.sortedText(sortText1);
        String str2 = SwingUtils.readStrFromFile("C:\\Users\\Ирина\\IdeaProjects\\Task11_num8"
                + "\\correctResultsFotTests\\correctResult04ForTestStandardSituation.txt");
        String[] sortText2 = str2.split(" ");
        List<String> correctList = Search.sortedText(sortText2);

        Assert.assertEquals(correctList, listForTest);
    }

    @Test
    public void testWithNamesInDifferentCases() throws FileNotFoundException {
        String str1 = SwingUtils.readStrFromFile("C:\\Users\\Ирина\\IdeaProjects\\Task11_num8\\inputForTests"
                + "\\input05TestWithNamesInDifferentCases.txt");
        String[] sortText1 = str1.split(" ");
        List<String> listForTest = Search.sortedText(sortText1);
        String str2 = SwingUtils.readStrFromFile("C:\\Users\\Ирина\\IdeaProjects\\Task11_num8"
                + "\\correctResultsFotTests\\correctResult05ForTestWithNamesInDifferentCases.txt");
        String[] sortText2 = str2.split(" ");
        List<String> correctList = Search.sortedText(sortText2);

        Assert.assertEquals(correctList, listForTest);
    }

    @After
    public void reportCompletionOfTest() {
        System.out.println("Test execution completed");
    }

    @AfterClass
    public static void reportAboutEndOfTests() {
        System.out.println();
        System.out.print("Testing of the program is completed");
    }
}
