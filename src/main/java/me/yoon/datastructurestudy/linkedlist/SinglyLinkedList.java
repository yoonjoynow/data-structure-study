package me.yoon.datastructurestudy.linkedlist;

import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {

    private int size = 0;
    private Node<T> head;
    private Node<T> tail;

    private class Node<T> {

        private T data;
        private Node<T> next;

        Node(T data) {
            this.data = data;
        }

        Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        private void setNext(Node<T> node) {
            next = node;
        }

        private Node<T> getNext() {
            return next;
        }

        private void setData(T data) {
            this.data = data;
        }

        private T getData() {
            return data;
        }
    }

    public void addFirst(T data) {
        linkFirst(data);
    }

    public void addLast(T data) {
        linkLast(data);
    }

    public boolean add(T data) {
        linkLast(data);
        return true;
    }

    public void addAt(int index, T data) {
        checkAddableIndex(index);

        if (index == 0) {
            linkFirst(data);
        } else {
            linkAt(index, data);
        }
    }

    public T removeFirst() {
        Node<T> headNode = head;
        if (headNode == null) {
            throw new NoSuchElementException();
        }

        return unlinkFirst(headNode);
    }

    private T unlinkFirst(Node<T> headNode) {
        T data = headNode.getData();
        Node<T> headNodeNext = headNode.getNext();

        headNode = null; //gc 대상
        head = headNodeNext;

        /**
         * size가 0인 경우 -> removeFirst에서 검증됨
         * size가 1인 경우 -> head의 next가 null이므로
         *                  head삭제시 size가 0이 되므로 tail도 null
         * size가 2인 경우 -> head의 next가 새로운 head가 되고,
         *                  2에서 1개가 삭제되므로 head이가 tail임
         * size가 3인 경우 -> head의 next가 새로운 head가 됨. 그 이상 변화 없음
         */
        if (headNodeNext == null) {
            // size가 1인 경우
            // headNode --> null
            tail = null;
        } else if(headNodeNext.getNext() == null) {
            // size가 2인 경우
            // headNode --> headNodeNext --> null
            tail = headNodeNext;
        }

        size--;
        return data;
    }

    private void linkFirst(T data) {
        Node<T> oldHead = head;
        Node<T> newNode = new Node<>(data);
        head = newNode;

        if (oldHead == null) {
            tail = newNode;
        } else {
            newNode.setNext(oldHead);
        }

        size++;
    }

    private void linkLast(T data) {
        Node<T> oldTail = tail;
        Node<T> newNode = new Node<>(data);
        tail = newNode;

        if (oldTail == null) {
            head = newNode;
        } else {
            oldTail.setNext(newNode);
        }

        size++;
    }

    private void linkAt (int index, T data) {
        Node<T> oldNode = getNodeAt(index); //삽입하고자 하는 인덱스에 현존하는 노드
        Node<T> previousNode = getNodeAt(index - 1); //현존하는 노드의 이전 노드
        Node<T> newNode = new Node<>(data);

        previousNode.setNext(newNode);
        newNode.setNext(oldNode);
        size++;
    }

    private void checkAddableIndex(int index) {
        if (! isAddableIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean isAddableIndex(int index) {
        return index >= 0 && index <= size;
    }

    private void checkUsedIndex(int index) {
        if (! isUsedIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean isUsedIndex(int index) {
        return index >= 0 && index < size;
    }

    private Node<T> getNodeAt(int index) {
        Node<T> target = head;
        for (int i = 0; i < size; i++) {
            target = target.getNext();
        }

        return target;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        System.out.println("1234");
    }

}
