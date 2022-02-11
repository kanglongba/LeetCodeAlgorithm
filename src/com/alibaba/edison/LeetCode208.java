package com.alibaba.edison;

/**
 * 实现 Trie (前缀树)，medium
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例
 * 如自动补完和拼写检查。
 * <p>
 * 完全没有思路，直接看答案吧：https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/shi-xian-trie-qian-zhui-shu-by-leetcode-ti500/
 * <p>
 * 未能独立解答。
 * <p>
 * author: qonyqian
 * created on: 2022/2/11 4:00 下午
 * version：1.0
 * description:
 */
public class LeetCode208 {

    private LeetCode208[] children;
    private boolean isEnd;

    /**
     * 初始化前缀树对象。
     */
    public LeetCode208() {
        children = new LeetCode208[26];
        isEnd = false;
    }

    /**
     * 向前缀树中插入字符串 word 。
     *
     * @param word
     */
    public void insert(String word) {
        LeetCode208 node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new LeetCode208();
            }
            node = node.children[index];
        }
        node.isEnd = true;

    }

    /**
     * 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
     *
     * @param word
     * @return
     */
    public boolean search(String word) {
        LeetCode208 node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    private LeetCode208 searchPrefix(String prefix) {
        LeetCode208 node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }

    /**
     * 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
     *
     * @param prefix
     * @return
     */
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }
}
