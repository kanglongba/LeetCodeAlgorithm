package com.alibaba.edison;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by 钧戈 on 2020/10/5.
 */
public class Sword35 {
    class Node implements Cloneable {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            Node copy = new Node(val);
            copy.next = (Node)this.next.clone();
            copy.random = (Node)this.next.clone();
            return copy;
        }
    }

    public Node copyRandomList(Node head) {
        //这道题应该是考察对链表的理解，没有巧妙的数学解法
        return java(head);
    }

    private Node java(Node head) {
        Node copy = null;
        try {
            copy = (Node)head.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return copy;
    }

    /**
     * 广度优先通常配置队列或堆栈
     *
     * @param head
     * @return
     */
    private Node bfs(Node head) {
        if (head == null) {
            return head;
        }
        HashMap<Node, Node> hashMap = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        Node copy = new Node(head.val);
        hashMap.put(head, copy);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            //if (!hashMap.containsKey(node)) {
            //    Node nNode = new Node(node.val);
            //    hashMap.put(node, nNode);
            //}
            Node cNode = hashMap.get(node);
            if (node.next != null && !hashMap.containsKey(node.next)) {
                Node cNext = new Node(node.next.val);
                hashMap.put(node.next, cNext);
                queue.offer(node.next);
            }
            if (node.random != null && !hashMap.containsKey(node.random)) {
                Node cRandom = new Node(node.random.val);
                hashMap.put(node.random, cRandom);
                queue.offer(node.random);
            }
            cNode.next = hashMap.get(node.next);
            cNode.random = hashMap.get(node.random);
        }

        return copy;
    }

    /**
     * 深度优先配合递归。原来深度优先的就是先拷贝next，再拷贝random。而且广度优先是同时拷贝next和random。
     *
     * @param head
     * @return
     */
    private Node dfs(Node head) {
        if (head == null) {
            return head;
        }
        HashMap<Node, Node> hashMap = new HashMap<>();
        Node copy = new Node(head.val);
        hashMap.put(head, copy);
        copy.next = dfsDigui(head.next, hashMap);
        copy.random = dfsDigui(head.random, hashMap);
        return copy;
    }

    private Node dfsDigui(Node head, HashMap<Node, Node> hashMap) {
        if (head == null) {
            return head;
        }
        if (hashMap.containsKey(head)) {
            return hashMap.get(head);
        } else {
            Node copy = new Node(head.val);
            hashMap.put(head, copy);
            copy.next = dfsDigui(head.next, hashMap);
            copy.random = dfsDigui(head.random, hashMap);
            return copy;
        }
    }

    private Node diedai(Node head) {
        if (head == null) {
            return head;
        }
        HashMap<Node, Node> hashMap = new HashMap<>();
        Node cNode = new Node(head.val);
        hashMap.put(head, cNode);
        Node temp = head;
        while (temp != null) {
            Node node = hashMap.get(temp);
            if (temp.next != null && !hashMap.containsKey(temp.next)) {
                Node cNext = new Node(temp.next.val);
                hashMap.put(temp.next, cNext);
            }
            if (temp.random != null && !hashMap.containsKey(temp.random)) {
                Node cRandom = new Node(temp.random.val);
                hashMap.put(temp.random, cRandom);
            }
            if (temp.next != null) {
                node.next = hashMap.get(temp.next);
            }
            if (temp.random != null) {
                node.random = hashMap.get(temp.random);
            }
            temp = temp.next;
        }
        return cNode;
    }

    private Node dieDaiYouHua(Node head) {
        if (head == null) {
            return head;
        }
        Node temp = head;
        while (temp != null) {
            Node node = new Node(temp.val);
            node.next = temp.next;
            temp.next = node;
            temp = node.next;
        }
        temp = head;
        while (temp != null) {
            if (temp.random != null) {
                temp.next.random = temp.random.next;
            }
            temp = temp.next.next;
        }
        temp = head;
        Node cNode = head.next;
        Node temp2 = cNode;
        while (temp != null && temp2 != null) {
            temp.next = temp2.next;
            if (temp.next != null) {
                temp2.next = temp.next.next;
            }
            temp = temp.next;
            temp2 = temp2.next;
        }

        return cNode;
    }
}
