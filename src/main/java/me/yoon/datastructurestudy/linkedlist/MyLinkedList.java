package me.yoon.datastructurestudy.linkedlist;

import java.util.NoSuchElementException;
import java.util.Stack;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    public void addFirst(int data) {
        Node oldHead = this.head;
        Node newNode = new Node(data);
        head = newNode;
        if (oldHead == null)
            // old tail이 없다는 것은 head도 없는 빈 List라는 것
            // 따라서 head에 new node를 지정해줌과 동시에 tail에도 new node를 지정해야함
            tail = newNode;
        else
            newNode.setNext(oldHead);
        size++;
    }

    public void addLast(int data) {
        Node oldTail = this.tail;
        Node newNode = new Node(data);
        tail = newNode;

        if (oldTail == null)
            addFirst(data);
        else {
            oldTail.setNext(newNode);
            size++;
        }
    }

    public void add(int data) {
        addLast(data);
    }

    public void addAt(int index, int data) {
        // 노드를 추가하려면 우선 인덱스가 유효한지 검증해야함
        // 유효범위의 인덱스는 0 이상 size 값 이하까지임
        // 왜냐하면 맨 마지막 노드의 next는 null 이지만
        // 이 자리에 add는 addLast와 동일함 그래서 < size가 아닌 <= size임 (set의 경우엔 < size)
        // 인덱스가 검증되었다면, head가 null인지 확인
        // null이면 예외를 던질것인가 아니면 addFirst할것인가 선택
        // index가 0이면 addFirst
        // 0보다 크면 자리 조정 진행
        checkAddableIndex(index);
        if (index == 0)
            addFirst(data);
        else {
            Node oldNode = getNode(index);
            Node beforeNode = getNode(index - 1);
            Node newNode = new Node(data);

            beforeNode.setNext(newNode);
            newNode.setNext(oldNode);
        }

        size++;
    }

    public int removeFirst() {
        // 기존 head가 null이면? 즉, size가 0이면 예외
        Node oldHead = head;
        if (oldHead == null)
            throw new NoSuchElementException();

        // old head의 next가 null이면? 즉, size가 1이어서 removeFirst 후 size가 0이 된다면?
        int element = oldHead.getData();
        Node nextNode = oldHead.getNext();
        oldHead = null; // gc 대상
        if (nextNode == null)
            tail = null; //존재하는 element가 없으므로 head와 마찬가지로 tail도 null
        size--;

        return element;
    }

    public int removeLast() {
        if (tail == null)
            throw new NoSuchElementException();

        int element = tail.getData();
        tail = null;
        if (size == 1)
            head = null;
        else
            removeAt(size - 1);
        size--;

        return element;
    }

    public int removeAt(int index) {
        checkRemovableElement(index);

        if (index == 0)
            removeFirst();

        Node target = getNode(index);
        Node targetNext = target.getNext();
        Node targetBefore = getNode(index - 1);
        if (targetNext == null)
            removeLast();

        targetBefore.setNext(targetNext);
        target = null;
        size--;
        return target.getData();
    }

    public static void main(String[] args) {
        MyLinkedList ls = new MyLinkedList();
        ls.add(10);
        ls.add(20);
        System.out.println("ls = " + ls.size);
        System.out.println(ls.removeLast());
        System.out.println(ls.size);
    }

    private Node getNode(int index) {
        Node target = head;
        for (int i = 0; i < index; i++) {
            target = target.getNext();
        }

        return target;
    }

    private void checkAddableIndex(int index) {
        if (! isAddableIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean isAddableIndex(int index) {
        return index >= 0 && index <= size;
    }

    private void checkRemovableElement(int index) {
        if (! isRemovableElement(index))
            throw new IndexOutOfBoundsException();

//        if (isEmpty())
//            throw new NoSuchElementException();
    }

    private boolean isRemovableElement(int index) {
        return index >= 0 && index < size && !isEmpty();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
