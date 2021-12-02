package ru.vsu.amm.thirdCourse.moevm.lukin.java.thirdLab;

import java.util.*;

/**
 * @author Lukin Artem
 */
public class Main {
    /**
     * Class of test results
     */
    public static class Results {

        long timeArr, timeLink;
        String method;

        /**
         * @param timeArr  time of test ArrayList
         * @param timeLink time of test LinkedList
         * @param method   Description of tested method
         */
        public Results(long timeArr, long timeLink, String method) {
            this.timeArr = timeArr;
            this.timeLink = timeLink;
            this.method = method;
        }
    }

    public static void main(String[] args) {

        int NumberOfTests = getNumberOfTestsFromConsole();
        while (NumberOfTests != 0) {
            ListsTester testArrayList = new ListsTester(new ArrayList<>(), NumberOfTests, 0);
            ListsTester testLinkedList = new ListsTester(new LinkedList<>(), NumberOfTests, 0);

            List<Results> ResultsOfTests = new ArrayList<>();
            ResultsOfTests.add(new Results(testArrayList.testMethodEnd(Methods::Add), testLinkedList.testMethodEnd(Methods::Add), "Add to the end"));
            ResultsOfTests.add(new Results(testArrayList.testMethodBegin(Methods::Add), testLinkedList.testMethodBegin(Methods::Add), "Add to the begin"));
            ResultsOfTests.add(new Results(testArrayList.testMethodMiddle(Methods::Add), testLinkedList.testMethodMiddle(Methods::Add), "Add to the middle"));

            ResultsOfTests.add(new Results(testArrayList.testMethodEnd(Methods::Get), testLinkedList.testMethodEnd(Methods::Get), "Get from the end"));
            ResultsOfTests.add(new Results(testArrayList.testMethodBegin(Methods::Get), testLinkedList.testMethodBegin(Methods::Get), "Get from the begin"));
            ResultsOfTests.add(new Results(testArrayList.testMethodMiddle(Methods::Get), testLinkedList.testMethodMiddle(Methods::Get), "Get from the middle"));

            ResultsOfTests.add(new Results(testArrayList.testMethodEnd(Methods::Remove), testLinkedList.testMethodEnd(Methods::Remove), "Remove from the end"));
            ResultsOfTests.add(new Results(testArrayList.testMethodBegin(Methods::Remove), testLinkedList.testMethodBegin(Methods::Remove), "Remove from the begin"));
            ResultsOfTests.add(new Results(testArrayList.testMethodMiddle(Methods::Remove), testLinkedList.testMethodMiddle(Methods::Remove), "Remove from the middle"));

            System.out.format("%22s%20s%20s\n", "Method", "ArrayList time", "LinkedList time");
            char comparison;
            for (int i = 0; i < ResultsOfTests.size(); i++) {
                if (ResultsOfTests.get(i).timeArr > ResultsOfTests.get(i).timeLink)
                    comparison = '>';
                else if (ResultsOfTests.get(i).timeArr < ResultsOfTests.get(i).timeLink)
                    comparison = '<';
                else comparison = '=';
                System.out.format("%22s%20d%3c", ResultsOfTests.get(i).method, ResultsOfTests.get(i).timeArr, comparison);
                System.out.println("  " + ResultsOfTests.get(i).timeLink);
            }
            System.out.println();
            NumberOfTests = getNumberOfTestsFromConsole();
        }
        System.out.println("Goodbye! It was nice to obey you! ;)");
    }

    /**
     * Read the number of tests from console
     *
     * @return read number
     */
    private static int getNumberOfTestsFromConsole() {
        System.out.println("Enter number of tests (for exit print 0)");
        String _NumberOfTests = new Scanner(System.in).nextLine();
        while (!checkNumberOfTests(_NumberOfTests)) {
            System.out.println("Syntax error!\nPlease enter number of tests again (for exit print 0)");
            _NumberOfTests = new Scanner(System.in).nextLine();
        }
        return Integer.parseInt(_NumberOfTests);
    }

    /**
     * Check if the number is correctly inputted by user
     *
     * @param _NumberOfTests checked string
     * @return if the number is inputted correctly
     */
    private static Boolean checkNumberOfTests(String _NumberOfTests) {
        if (_NumberOfTests.charAt(0) == '0' && _NumberOfTests.length() > 1) return false;
        for (int i = 0; i < _NumberOfTests.length(); i++) {
            if (!(_NumberOfTests.charAt(i) >= '0' && _NumberOfTests.charAt(i) <= '9')) return false;
        }
        return true;
    }
}
