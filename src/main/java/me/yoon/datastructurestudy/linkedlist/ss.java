package me.yoon.datastructurestudy.linkedlist;

import java.util.LinkedList;

public class ss {
    public static void main(String[] args) {
        LinkedList<Integer> ls = new LinkedList<>();
        System.out.println(ls.toString());

        for (int i = 0; i < 10; i++) {
            ls.add(i);
        }
        System.out.println(ls);
    }
}
