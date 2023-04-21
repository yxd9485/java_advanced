package com.example.multithreading_all.structure.linkedlist;

import lombok.Data;

/**
 * module: 应用模块名称<br/>
 * <p>
 * description: 描述<br/>
 *
 * @author XiaoDong.Yang
 * @date: 2023/2/15 18:54
 */
public class ReverseLink {

    public static void main(String[] args) {

        ListNode listNode = new ListNode(3);
        System.out.println();

    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null) {
            ListNode next = curr.next;
            curr.next =prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    @Data
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

}
