package me.yoon.datastructurestudy.linkedlist;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SinglyLinkedListTest {

    SinglyLinkedList<Integer> list;

    @Test
    void add_success_test() {
        //arrange
        list = new SinglyLinkedList<>();
        list.add(1);
        list.add(2);

        //act
        int index1 = list.indexOf(1);
        int index2 = list.indexOf(2);

        //assert
        assertThat(index1).isEqualTo(0);
        assertThat(index2).isEqualTo(1);
    }

    @Test
    void addFirst_test() {
        //arrange
        list = new SinglyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i * 10);
        }

        //act
        Integer oldFirst = list.getFirst();
        int oldSize = list.getSize();
        list.addFirst(44);
        Integer newFirst = list.getFirst();
        int newSize = list.getSize();

        //assert
        assertThat(oldFirst).isEqualTo(0);
        assertThat(oldSize).isEqualTo(10);
        assertThat(newFirst).isEqualTo(44);
        assertThat(newSize).isEqualTo(11);
    }

    @Test
    public void addLast_test() {
        //arrange
        list = new SinglyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i * 10);
        }

        //act
        Integer oldLast = list.getLast();
        int oldSize = list.getSize();
        list.addLast(44);
        Integer newLast = list.getLast();
        int newSize = list.getSize();

        //assert
        assertThat(oldLast).isEqualTo(90);
        assertThat(oldSize).isEqualTo(10);
        assertThat(newLast).isEqualTo(44);
        assertThat(newSize).isEqualTo(11);
    }

    @Test
    public void addAt_test() {
        //arrange
        list = new SinglyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i * 10);
        }

        //act
        list.addAt(0, 777);
        list.addAt(9, 777);
        list.addAt(5, 777);

        //assert
        assertThat(list.getFirst()).isEqualTo(777);


        assertThat(list.getLast()).isEqualTo(777);
        assertThat(list.get(5)).isEqualTo(777);
    }
}