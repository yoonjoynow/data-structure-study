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
        } else if (index == (size - 1)){
            linkLast(data);
        } else {
            linkAt(index, data);
        }
    }

    public T removeFirst() {
        Node<T> headNode = head;
        if (headNode == null) {
            throw new EmptyLinkedListException();
        }

        return unlinkFirst(headNode);
    }

    public T removeLast() {
        Node<T> tailNode = tail;
        if (tailNode == null) {
            throw new EmptyLinkedListException();
        }

        return unlinkLast(tailNode);
    }

    public T removeAt(int index) {
        checkUsedIndex(index);
        T data = null;

        if (index == 0) {
            data = unlinkFirst(head);
        } else if (index == (size - 1)) {
            data = unlinkLast(tail);
        } else {
            data = unlinkAt(index);
        }

        return data;
    }

    public T setAt(int index, T data) {
        checkUsedIndex(index);
        Node<T> node = getNodeAt(index);
        T oldData = node.getData();
        node.setData(data);

        return oldData;
    }

    public int indexOf(T data) {
        int index = 0;
        for (Node<T> target = head; target != null; target = target.getNext()) {
            if (target.getData() == data) {
                return index;
            }
            index++;
        }

        return -1;
    }

    public boolean contains(T data) {
        return indexOf(data) >= 0;
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
        Node<T> existingNode = getNodeAt(index); //??????????????? ?????? ???????????? ???????????? ??????
        Node<T> previousNode = getNodeAt(index - 1); //???????????? ????????? ?????? ??????
        Node<T> newNode = new Node<>(data);

        previousNode.setNext(newNode);
        newNode.setNext(existingNode);
        size++;
    }

    private T unlinkFirst(Node<T> headNode) {
        T data = headNode.getData();
        Node<T> afterHeadNode = headNode.getNext();

        headNode = null; //gc ??????
        head = afterHeadNode;

        /**
         * size??? 0??? ?????? -> removeFirst?????? ?????????
         * size??? 1??? ?????? -> head??? next??? null?????????
         *                  head????????? size??? 0??? ????????? tail??? null
         * size??? 2??? ?????? -> head??? next??? ????????? head??? ??????,
         *                  2?????? 1?????? ??????????????? head?????? tail???
         * size??? 3??? ?????? -> head??? next??? ????????? head??? ???. ??? ?????? ?????? ??????
         */
        if (afterHeadNode == null) {
            // size??? 1??? ??????
            // headNode --> null
            tail = null;
        } else if(afterHeadNode.getNext() == null) {
            // size??? 2??? ??????
            // headNode --> headNodeNext --> null
            tail = afterHeadNode;
        }

        size--;
        return data;
    }

    private T unlinkLast(Node<T> tailNode) {
        /**
         *  size??? 1??? ??????, head??? tail ?????? null
         *  size??? 2??? ??????, previousTail??? head??? tail ??????
         *  size??? 3??? ??????, previousTail??? tail, head??? ?????????
         */
        T data = tail.getData();
        Node<T> beforeTailNode = getNodeAt(size - 2);
        tailNode = null;
        beforeTailNode.setNext(null);
        tail = beforeTailNode;

        if (size == 1) {
            head = null;
        } else if (size == 2){
            head = beforeTailNode;
        }

        size--;
        return data;
    }

    private T unlinkAt(int index) {
        // ?????? ????????? ????????????,
        // ????????? ?????? ????????? ????????? ?????? ????????? ??????????????? ?????????

        Node<T> existingNode = getNodeAt(index);
        Node<T> previousNode = getNodeAt(index - 1);
        Node<T> nextNode = existingNode.getNext();
        T data = existingNode.getData();

        previousNode.setNext(nextNode);
        size--;
        return data;
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
        for (int i = 0; i < index; i++) {
            target = target.getNext();
        }

        return target;
    }

    public T get(int index) {
        checkUsedIndex(index);
        return getNodeAt(index).getData();
    }

    public T getFirst() {
        if (head == null) {
            throw new EmptyLinkedListException();
        }
        return head.getData();
    }

    public T getLast() {
        if (tail == null) {
            throw new EmptyLinkedListException();
        }
        return tail.getData();
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        Node<T> headNode = head;
        return null;
    }

}
