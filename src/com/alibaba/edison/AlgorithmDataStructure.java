package com.alibaba.edison;

/**
 * 算法中常用的数据结构
 * 1.Stack
 * 2.HashMap
 * 3.Deque -> LinkedList
 * 4.LinkedHashMap，本质是HashMap，但是使用双向链表维持了元素的插入顺序，使遍历的时候，可以按插入顺序遍历。它内部还有一个头指针和尾指针。
 * 5.PriorityQueue，优先队列，最小堆/最大堆，元素按顺序排列，所以遍历时，也是按顺序遍历。默认是排序是从小到大，可以自定义 Comparator。
 * offer/add添加元素；peek返回队列的头部元素，但是不出队；poll/remove返回并删除队列的头部元素。
 * [优先队列（PriorityQueue）常用方法及简单案例](https://blog.csdn.net/m0_37602827/article/details/100172976)
 * 6.PriorityBlockingQueue，线程安全版PriorityQueue，多线程环境，会阻塞其余线程
 * 3.Queue(队列)->Deque(双端队列)->ArrayDeque，内部是数组
 *
 *
 * 位运算
 * 与：全为1才为1，运算符 &
 * 或：有一个为1就为1，运算符 |
 * 非：0为1，1为0，运算符 ~
 * 异或：相异为1，相同为0，运算符 ^
 * 同或：相同为1，相异为0，Java中没有同或运算符
 *
 * 移位操作：https://zhuanlan.zhihu.com/p/30108890
 * 左移：<<，符号位也会被移掉，右边产生的空位补0
 * 右移：>>，左边空位补上符号位的值（符号位是1，就补1；符号位是0，就补0）
 * 无符号右移：>>>，左边空位固定补0
 * 符号位，1表示负数、0表示正数
 * 所有的移位运算，符号位都会跟着走，差别只是在补位的策略不同
 *
 *
 * 1.穷举：exhaustion
 * 2.递归：recursion
 *
 * author: qonyqian
 * created on: 2022/1/30 2:57 下午
 * version：1.0
 * description:
 */
public class AlgorithmDataStructure {
}
