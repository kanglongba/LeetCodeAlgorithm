package com.alibaba.edison;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 请你设计并实现一个满足 LRU (最近最少使用) 缓存 约束的数据结构。medium
 * <p>
 * 未能写出。https://leetcode-cn.com/problems/lru-cache/solution/lruhuan-cun-ji-zhi-by-leetcode-solution/
 * <p>
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value)如果关键字key 已经存在，则变更其数据值value ；如果不存在，则向缓存中插入该组key-value 。如果插
 * 入操作导致关键字数量超过capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * <p>
 * author: qonyqian
 * created on: 2022/2/3 12:56 下午
 * version：1.0
 * description:
 */
public class LeetCode146 {

    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;

        public DLinkedNode() {
        }

        public DLinkedNode(int _key, int _value) {
            key = _key;
            value = _value;
        }
    }

    private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    public LeetCode146(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 使用伪头部和伪尾部节点
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            // 如果 key 不存在，创建一个新的节点
            DLinkedNode newNode = new DLinkedNode(key, value);
            // 添加进哈希表
            cache.put(key, newNode);
            // 添加至双向链表的头部
            addToHead(newNode);
            ++size;
            if (size > capacity) {
                // 如果超出容量，删除双向链表的尾部节点
                DLinkedNode tail = removeTail();
                // 删除哈希表中对应的项
                cache.remove(tail.key);
                --size;
            }
        } else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }


    /**
     * 会使用LinkedHashMap实现就可以了
     * LinkedHashMap：[图解LinkedHashMap原理](https://www.jianshu.com/p/8f4f58b4b8ab)
     * 不构建数据结构，直接使用LinkedHashMap来解答
     */
    public class LruCache {

        /**
         * LinkedHashMap，本质是一个HashMap，但是内部有一个双向链表，可以维持元素的插入顺序。在遍历的时候，会按照插入顺序遍历元素
         */
        LinkedHashMap<Integer, Integer> cache;
        int capacity;

        public LruCache(int capacity) {
            this.capacity = capacity;
            cache = new LinkedHashMap<>(capacity);
        }

        public int get(int key) {
            int value = cache.getOrDefault(key, -1);
            if (value == -1) {
                return -1;
            }
            //把元素移到最近的位置
            cache.remove(key);
            cache.put(key, value);
            return value;
        }

        public void put(int key, int value) {
            if (cache.containsKey(key)) {
                cache.remove(key);
                cache.put(key, value);
            } else if (cache.size() >= capacity) {
                //超出容量了
                Iterator<Integer> iterator = cache.keySet().iterator();
                if (iterator.hasNext()) {
                    //因为LinkedHashMap是有序的，所以第一个元素就是最老的元素
                    cache.remove(iterator.next());
                }
                cache.put(key, value);
            } else {
                cache.put(key, value);
            }
        }
    }

}
