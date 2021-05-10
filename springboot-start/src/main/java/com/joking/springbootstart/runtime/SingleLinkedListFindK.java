package com.joking.springbootstart.runtime;

public class SingleLinkedListFindK {

    public static void main(String[] args) {
        Node head = new Node(0);
        Node curr = head;
        for (int i = 1; i < 100; i++) {
            curr.next = new Node(i);
            curr = curr.next;
        }
        Node lastN1 = getLastN2(head, 5);
        System.out.println(lastN1);
    }

    public static Node getLastN2(Node head, int n) {
        Node fastN = head, slowN = head;
        for(int i = 0; i < n; i ++) {
            if(fastN.next == null)  {
                throw new RuntimeException("单链表元素个数小于 " + n + "  ！");
            }
            fastN = fastN.next;
        }
        while(fastN.next != null) {
            fastN = fastN.next;
            slowN = slowN.next;
        }
        return slowN;
    }

    public static Node getLastN1(Node head, int n) {
        int count = 0;
        Node ahead = head;
        while(ahead.next != null) {
            count ++;
            ahead = ahead.next;
        }
        if(count < n) {
            throw new RuntimeException("单链表元素个数小于 " + n + " !");
        }
        ahead = head;
        for(int i = 0; i < count  - n ; i++) {
            ahead = ahead.next;
        }
        return ahead;
    }



    public static class Node{
        int val;

        public Node(int val) {
            this.val = val;
        }

        Node next;

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    '}';
        }
    }
}
