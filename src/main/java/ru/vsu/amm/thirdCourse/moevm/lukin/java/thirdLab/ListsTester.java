package ru.vsu.amm.thirdCourse.moevm.lukin.java.thirdLab;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Tester of speed of working of add, remove and get methods  for List
 */
class ListsTester {
    private List<Integer> list;
    private List<Integer> testList;
    private Instant start,end;
    private int NumberOfTests;
    private int value;

    /**
     * Constructor with parameters
     * @param _list tested List
     * @param _NumberOfElements Number of elements
     * @param _value used value for insertion
     */
    ListsTester(List<Integer> _list, int _NumberOfElements, int _value) {
        this.list = _list;
        this.testList = _list;
        this.NumberOfTests = _NumberOfElements;
        this.value = _value;
    }

    /**
     * Measures the time it takes to execute
     * the method 'NumberOfTests' times at the beginning
     * @return Time spent
     */
    public long testMethodBegin(Method method){
        ClearList();
        testList.add(0);
        if (method.doMethod(0,testList) != "Add") fill();

        start = Instant.now();
        for (int i = 0; i < NumberOfTests; i++)
            method.doMethod(0, list);
        end = Instant.now();

        return Duration.between(start,end).toNanos()/1000;
    }

    /**
     * Measures the time it takes to execute
     * the method 'NumberOfTests' times at the end
     * @return Time spent
     */
    public long testMethodEnd(Method method){
        ClearList();
        testList.add(0);
        if (method.doMethod(0,testList) != "Add") fill();

        start = Instant.now();
        for (int i = 0; i < NumberOfTests; i++)
            method.doMethod(list.size()-1, list);
        end = Instant.now();

        return Duration.between(start,end).toNanos()/1000;
    }

    /**
     * Measures the time it takes to execute
     * the method 'NumberOfTests' times at the middle
     * @return Time spent
     */
    public long testMethodMiddle(Method method){
        ClearList();
        testList.add(0);
        if (method.doMethod(0,testList) != "Add") fill();

        start = Instant.now();
        for (int i = 0; i < NumberOfTests; i++)
            method.doMethod((list.size()-1)/2, list);
        end = Instant.now();

        return Duration.between(start,end).toNanos()/1000;
    }

    /**
     * Fills list with the 'NumberOfTests' number of elements
     */
    public void fill(){
        while (list.size() < NumberOfTests)
            list.add(value);
    }

    /**
     * Clear values (and capacity if list is array)
     */
    public void ClearList()
    {
        list.clear();
        if (list instanceof ArrayList)
            ((ArrayList<Integer>) list).trimToSize();
    }
}

/**
 * a class that is used to pass methods to the testing
 * functions and is called using the interface Method
 */
class Methods{
    /**
     *
     * @param index the index where we add the element
     * @param list list, where the element is added
     * @return Name of the method
     */
    static String Add(int index, List list){
        list.add(index, 0);
        return "Add";
    }

    /**
     *
     * @param index index from where the element will be removed
     * @param list the list from which the item is being removed
     * @return Name of the method
     */
    static String Remove(int index, List list){
        list.remove(index);
        return "Remove";
    }

    /**
     *
     * @param index the index from where we get the element
     * @param list the list from which we get the element
     * @return Name of the method
     */
    static String Get(int index, List list){
        list.get(index);
        return "Get";
    }
}

/**
 * the interface used to call methods for testing
 */
interface Method {
    /**
     * executes a method from the Methods class
     * @param index the index of the element to which we will apply the method
     * @param list the list to which we will apply the method
     * @return Name of the method
     */
    String doMethod(int index, List list);
}