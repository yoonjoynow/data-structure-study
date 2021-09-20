package me.yoon.datastructurestudy.linkedlist;

import java.util.LinkedList;

public class Hello {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += list.get(i);
        }

        for (Integer integer : list) {

        }
    }
}
