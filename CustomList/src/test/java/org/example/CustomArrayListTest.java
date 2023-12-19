package org.example;


import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayListTest  {

    CustomArrayList<String> list;

    @BeforeEach
    void createList(){
        list = new CustomArrayList<>();
    }

    @Test
    @DisplayName("Method add")
    public void testAdd() {
        list.add("add1");
        assertEquals("add1",list.get(0));
    }


    @Test
    @DisplayName("Method add for index")
    public void testAddForIndex() {
        list.add("add1");
        list.add("add2");
        list.add(1,"add3");
        assertEquals("add3",list.get(1));
    }
    @Test
    @DisplayName("Method addAll")
    public void testAddAll() {
        List<String> addAll = Arrays.asList("addAll1","addAll2");
        list.addAll(addAll);
        assertEquals(list.size(),addAll.size());
    }
    @Test
    @DisplayName("Method clear")
    public void testClear() {
        for(int i = 0; i < 12; i++){
            list.add(String.valueOf(i));
        }
        list.clear();
        assertEquals(0,list.size());
    }
    @Test
    public void testGet() {
        list.add("get1");
        assertEquals("get1",list.get(0));
    }
    @Test
    public void testIsEmpty() {
        assertEquals(true,list.isEmpty());
    }
    @Test
    @DisplayName("Method remove for index")
    public void testRemove() {
        list.add("remove1");
        list.add("remove2");
        list.add("remove3");
        list.remove(1);
        assertEquals("remove3",list.get(1));

    }
    @Test
    @DisplayName("Method remove for element")
    public void testRemoveElement() {
        list.add("remove1");
        list.add("remove2");
        list.add("remove3");
        list.remove("remove2");
        assertEquals("remove3",list.get(1));
    }
    @Test
    public void testSort() {
        list.add("remove2");
        list.add("remove11213");
        list.add("remove31233321");
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        };
        list.sort(comparator);

        assertEquals("remove2",list.get(0));
        assertEquals("remove11213",list.get(1));
        assertEquals("remove31233321",list.get(2));
    }


    @Test
    @DisplayName("Exception indexOutOfBound")
    public void testExceptionGet(){
        list.add("testException");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));

    }
}