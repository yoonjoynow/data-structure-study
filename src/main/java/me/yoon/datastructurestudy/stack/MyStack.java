package me.yoon.datastructurestudy.stack;

import java.util.EmptyStackException;

public class MyStack<E> implements Stack<E>{

    private Node<E> top;
    private int size = 0;

    class Node<E> {
        E item;
        Node<E> next;

        public Node(E data) {
            this.item = data;
        }

        public Node(E data, Node<E> next) {
            this.item = data;
            this.next = next;
        }
    }
    @Override
    public E pop() {
        checkEmpty();

        return null;
    }

    @Override
    public E push(E item) {
        return null;
    }

    @Override
    public E peek() {
        checkEmpty();

        return top.item;
    }

    private E elementAt(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkEmpty() {
        if (isEmpty())
            throw new EmptyStackException();
    }
}
