package org.example;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class CustomArrayList<E> {

    private final static int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elements;
    private final static Object[] EMPTY_ELEMENTS = {};


    public CustomArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            elements = new Object[initialCapacity];
            size = 0;
        } else if (initialCapacity == 0) {
            elements = EMPTY_ELEMENTS;
            size = 0;
        } else {
            throw new IllegalArgumentException("initialCapacity не может быть отрицательным");
        }
    }

    public CustomArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    void add(int index, E element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс " + index + " за пределами массива");
        } else {
            checkSizeBeforeAdd();
            rightShift(index);
            elements[index] = element;
        }
    }

    void add(E element) {
        checkSizeBeforeAdd();
        elements[size++] = element;
    }


    void addAll(Collection<E> collection) {
        for (E e : collection) {
            add(e);
        }
    }


    void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }


    E get(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс " + index + " за пределами массива");
        }
        return (E) elements[index];
    }

    boolean isEmpty() {
        return size == 0;
    }


    boolean remove(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс " + index + " за пределами массива");
        }
        if (elements[index] != null) {
            leftShift(index);
            return true;
        }
        return false;
    }

    boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == o) {
                leftShift(i);
                return true;
            }
        }
        return false;
    }

    void sort(Comparator<? super E> comparator) {
        int low = 0;
        int high = size - 1;
        quickSort(elements, low, high, comparator);
    }

    public int size() {
        return size;
    }


    private void checkSizeBeforeAdd() {
        if (size == elements.length) {
            int newCapacity = elements.length * 2;
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    private void rightShift(int index) {
        if (elements[index] != null) {
            for (int i = size; i > index; i--) {
                elements[i] = elements[i - 1];
            }
            size++;
        }
    }

    private void leftShift(int index) {
        for (int i = index; i < size; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        size--;
    }


    private void quickSort(Object[] arr, int low, int high, Comparator<? super E> comparator) {
        if (size == 0) {
            return;
        }
        if (low < high) {
            int part = partition(arr, low, high, comparator);
            quickSort(arr, low, part - 1, comparator);
            quickSort(arr, part + 1, high, comparator);
        }
    }

    private int partition(Object[] arr, int low, int high, Comparator<? super E> comparator) {
        Object prop = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparator.compare(((E) arr[j]), (E) prop) <= 0) {
                i++;
                Object temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Object temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}
