package com.example.multithreading_all.structure.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * module: 链表<br/>
 * <p>
 * description: 反向链表<br/>
 *
 * @author XiaoDong.Yang
 * @date: 2023/2/15 14:05
 */
public class ReverseLinkedList {

    ArrayList<Integer> tmp = new ArrayList<Integer>();

    public int[] reversePrint(ListNode head) {
        recur(head);
        int[] res = new int[tmp.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = tmp.get(i);
        }
        return res;
    }


    void recur(ListNode head) {
        if (head == null) {
            return;
        }
        recur(head.next);
        tmp.add(head.val);
    }

    public static class ListNode {
        int val;
        ListNode root;                              //定义头节点
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        public void add(ListNode node) {          //增加节点
            if (this.next == null) {  //如果下一个节点为空，则把新节点加入到next的位置上
                this.next = node;
            } else {                   //如果下一个节点不为空，则继续找next
                this.next.add(node);
            }
        }

        public void addNode(int val) {         //根据内容添加节点
            ListNode newNode = new ListNode(val);    //要插入的节点
            if (this.root == null) {                        //没有头节点，则要插入的节点为头节点
                this.root = newNode;
            } else { //如果有头节点，则调用节点类的方法自动增加
                this.root.add(newNode);
            }
        }
    }

    public static void main(String[] args) {
        ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
        ListNode listNode = new ListNode(0);
        listNode.addNode(3);
        listNode.addNode(4);
        listNode.addNode(5);
        int[] ints = reverseLinkedList.reversePrint(listNode);


        System.out.println(Arrays.toString(ints));
    }


}
