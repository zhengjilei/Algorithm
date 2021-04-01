package a_review.other;

/**
 * created by Ethan-Walker on 2019/3/5
 */
public class Trie {

    class TrieNode {
        private int path;
        private int end;
        private TrieNode[] map;

        public TrieNode() {
            map = new TrieNode[26];
        }
    }

    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        int index;
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            if (node.map[index] == null) {
                node.map[index] = new TrieNode();
            }
            node = node.map[index];
            node.path++;
        }
        node.end++;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        if (word == null || word.length() == 0) return false;
        int index;
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            if (node.map[index] == null) {
                return false;
            }
            node = node.map[index];
        }
        return node.end > 0;
    }

    public void delete(String word) {
        if (!search(word)) return;
        int index;
        TrieNode node = root;
        for (int i = 0; i < word.charAt(i); i++) {
            index = word.charAt(i) - 'a';
            if (node.map[index].path == 1) {
                node.map[index] = null; // 直接和后面节点断开，因为该节点以后只有 word 这一个字符串，不用一个个删除
                return;
            } else {
                node.map[index].path--;
            }
            node = node.map[index];
        }
        node.end--;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0) return false;
        int index;
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            index = prefix.charAt(i) - 'a';
            if (node.map[index] == null) {
                return false;
            }
            node = node.map[index];
        }
        return true;
    }
}
