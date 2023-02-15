package com.example.multithreading_all.structure.linkedlist;

import java.util.ArrayList;

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
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
        ListNode listNode = new ListNode(3);

        System.out.println(reverseLinkedList.reversePrint(listNode));
    }


}
