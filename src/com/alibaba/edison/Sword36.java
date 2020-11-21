package com.alibaba.edison;

/**
 * Created by 钧戈 on 2020/10/11.
 */
public class Sword36 {

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    /**
     * 中序遍历+双指针
     *
     * @param root
     * @return
     */
    Node pre, head;

    public Node treeToDoublyList(Node root) {
        if (root == null) { return null; }
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    void dfs(Node cur) {
        if (cur == null) { return; }
        dfs(cur.left);
        if (pre != null) {
            pre.right = cur;
        } else {
            head = cur;
        }
        cur.left = pre;
        pre = cur;
        dfs(cur.right);
    }

    /**
     * 二刷 2020-11-01 22:35:32
     * @param root
     * @return
     */
    Node pre2, head2;
    public Node ershua(Node root) {
        if (root == null) {
            return root;
        }
        dfs2(root);
        //递归结束后，中间的关系都已经建立完毕，最后补全头结点和尾节点的关系
        pre2.right = head2;
        head2.left = pre2;
        return head2;
    }

    private void dfs2(Node root) {
        if (root == null) {
            return;
        }
        //递归左子树
        dfs2(root.left);

        //中间处理节点
        //如果head2==null,表示探测到了第一个节点，那么它就应该是链表的头结点
        if (head2 == null) {
            head2 = root;
        }
        //如果pre2==null,也是表示探测到了第一个节点，第一个节点没有办法建立双向关系，那么应该把第一个节点的前驱置为空
        if (pre2 == null) {
            root.left = pre2;
        } else {
            //如果pre2!=null,表示是中间探测过程，那么前驱节点和当前节点的关系
            pre2.right = root;
            root.left = pre2;
        }
        //最后更新前驱
        pre2 = root;

        //递归右子树
        dfs2(root.right);
    }
}
