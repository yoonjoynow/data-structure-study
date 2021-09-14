package me.yoon.datastructurestudy.stack;

public interface Stack<E> {

    E pop();

    E push(E item);

    E peek();

    boolean isEmpty();
}
