import java.util.*;
import java.io.*;


//don't forget to write a toString()
//what does @ do
public class ArrayDS<T extends Comparable<? super T>> implements SequenceInterface<T>, ReorderInterface, Comparable<ArrayDS<T>> {

    // to Start:
    // private data
    private T[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    // constructor
    @SuppressWarnings("unchecked")
    public ArrayDS() {
        array = (T[]) new Comparable[DEFAULT_CAPACITY];
        size = 0;
    }

    // copy constructor
    public ArrayDS(ArrayDS<T> other) {
        this.size = other.size;
        this.array = Arrays.copyOf(other.array, other.size);
    }

    // toString()
    public String toString() {
        String answer = ""; 

        for (int i = 0; i < size; i++) {
            answer += array[i];
        }

        return answer;
    }


    // SequenceInterface:
    // size()
    public int size() {
        return size;
    }

    // isEmpty()
    public boolean isEmpty() {
        return size == 0;
    }

    // first()
    public T first() {
        if (isEmpty())
            throw new EmptySequenceException("Array is empty.");
        return array[0];
    }

    // last()
    public T last() {
        if (isEmpty())
            throw new EmptySequenceException("Array is empty.");
        return array[size - 1];
    }

    // clear()
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    // itemAt()
    public T itemAt(int position) {
        if (position < 0 || position >= size)
            throw new IndexOutOfBoundsException("Index: " + position + " is out of bounds.");

        return array[position];
    }

    // predecessor(), return T at indexOf(itemT - 1)
    public T predecessor(T item) {
        if (isEmpty())
            throw new EmptySequenceException("Array is empty.");

        for (int i = 0; i < size; i++) {
            if (array[i] != null && array[i].equals(item)) {
                if (i == 0) {
                    return array[size - 1]; 
                } else {
                    return array[i - 1];
                }
            }
        }

        // int index = indexOf(item);
        // if (index <= 0)
        //     return null;
        
        return null;
    }
    //                  ||
    //                  ||
    // possible helper indexOf() method
    private int indexOf(T item) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item))
                return i;
        }
        return -1;
    }

    
    // lastOccurrenceOf()
    public int lastOccurrenceOf(T item) {
        if (isEmpty())
            throw new EmptySequenceException("Array is empty.");
        
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(item))
                return i;
        }

        return -1;
    }

    // getFrequencyOf()
    public int getFrequencyOf(T item) {
        if (isEmpty())
            throw new EmptySequenceException("Array is empty.");
        int counter = 0;
        
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item))
                counter++;
        }
        return counter;
    }

    // insert()
    public void insert(T item, int position) {
        if (position < 0 || position > size)
            throw new IndexOutOfBoundsException("Index: " + position + " is out of bounds.");

        if (size == array.length)
            resizeArray();

        for (int i = size; i > position; i--) {
            array[i] = array[i - 1];
        }
        array[position] = item;
        size++;
    }
    //        ||
    //        ||
    // resizeArray()
    // helper method
    private void resizeArray() {
        array = Arrays.copyOf(array, array.length * 2);
    }
    // append()
    public void append(T item) {
        insert(item, size);
    }

    // prefix()
    public void prefix(T item) {
        insert(item, 0);
    }

    // deleteHead()
    public T deleteHead() {
        if (isEmpty())
            throw new EmptySequenceException("Array is empty.");

        T answer = array[0];
        for (int i = 1; i < size; i++) {
            array[i - 1] = array[i];
        }

        array[size - 1] = null;
        size--;
        return answer;

    }

    // deleteTail()
    public T deleteTail() {
        if (isEmpty())
            throw new EmptySequenceException("Array is empty.");

        T answer = array[size - 1];

        array[size - 1] = null;
        size--;
        return answer;
    }

    // trim()
    public boolean trim(int numItems) {
        if (numItems > size)
            return false;

        for (int i = 0; i < numItems; i++) {
            if (size > 0) {
                array[size - 1] = null;
                size--;
            }
        }
        return true;
    }

    // cut()
    public boolean cut(int start, int numItems) {
        if (start + numItems > size) 
            return false;

        for (int i = start; i < size - numItems; i++) {
            array[i] = array[i + numItems];
        }

        for (int i = size - numItems; i < size; i++) {
            array[i] = null;
        }

        size = size - numItems;
        return true;
    }

    // slice()
    // compareTo() helper method needed
    public int compareTo(ArrayDS<T> other) {
        return Integer.compare(this.size, other.size);
    }

    // actual slice()
    public SequenceInterface<T> slice(T item) {
        SequenceInterface<T> result = new ArrayDS<>();

        for (int i = 0; i < size; i++) {
            if (array[i].compareTo(item) <= 0)
                result.append(array[i]);
        }
        return result;
    }

    // slice(two param)
    public SequenceInterface<T> slice(int start, int numItems) {
        if (start < 0 || start + numItems > size)
            return null; 

        SequenceInterface<T> result = new ArrayDS<>();
        for (int i = start; i < start + numItems; i++) {
            result.append(array[i]);
        }

        return result;
    }

    // ReorderInterface:
    // reverse()
    public void reverse() {
        if (isEmpty()) 
            throw new EmptySequenceException("Array is empty.");

        T[] reversed = (T[]) new Comparable[size];
        for (int i = 0; i < size; i++) {
            reversed[i] = array[size - 1 - i];
        }

        for (int i = 0; i < size; i++) {
            array[i] = reversed[i];
        }
    }

    // rotateRight()
    // shift elements right (i > 0; i--)
    public void rotateRight() {
        if (isEmpty()) 
            throw new EmptySequenceException("Array is empty.");
        
        T last = array[size - 1];
        for (int i = size - 1; i > 0; i--) {
            array[i] = array[i - 1];
        }
        array[0] = last;
    }

    // rotateLeft()
    // shift elements left (i < size - 1; i++)
    public void rotateLeft() {
        if (isEmpty()) 
            throw new EmptySequenceException("Array is empty.");

        T first = array[0];
        for (int i = 0; i < size - 1; i++) {
            array[i] = array[i + 1];
        }          
        array[size - 1] = first;
    }

    // shuffle()
    public void shuffle(int[] oldPositions, int[] newPositions) {
        if (oldPositions.length != newPositions.length) {
            throw new IllegalArgumentException("Arrays must have same length.");
        }
    
        for (int i = 0; i < oldPositions.length; i++) {
            for (int j = i + 1; j < oldPositions.length; j++) {
                if (oldPositions[i] == oldPositions[j]) {
                    throw new IllegalArgumentException("Duplicates found.");
                }
            }
        }
    
        for (int i = 0; i < newPositions.length; i++) {
            for (int j = i + 1; j < newPositions.length; j++) {
                if (newPositions[i] == newPositions[j]) {
                    throw new IllegalArgumentException("Duplicates found.");
                }
            }
        }
    
        T[] tempArray = (T[]) new Comparable[size]; 
        for (int i = 0; i < oldPositions.length; i++) {
            if (oldPositions[i] < 0 || oldPositions[i] >= size || newPositions[i] < 0 || newPositions[i] >= size) {
                throw new IndexOutOfBoundsException("Position out of bounds.");
            }
            tempArray[newPositions[i]] = array[oldPositions[i]];
        }
    
        for (int i = 0; i < size; i++) {
            array[i] = tempArray[i];
        }
    }


}