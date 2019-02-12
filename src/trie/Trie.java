package trie;

/**
 * created by Ethan-Walker on 2019/2/11
 */
public class Trie {
    class TrieNode {
        public int path;// 以当前节点为路径中节点的数目
        public int end; // 以当前节点为路径终点的书怒

        public TrieNode[] map;

        public TrieNode() {
            this.path = 0;
            this.end = 0;
            this.map = new TrieNode[26];
        }
    }

    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        TrieNode t = root;
        int index = 0;
        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            if (t.map[index] == null) {
                t.map[index] = new TrieNode();
            }
            t = t.map[index];
            t.path++;
        }
        t.end++;
    }

    // 假设被删除的单词一定存在
    public void delete(String word) {
        if (word == null || word.length() == 0) return;
        TrieNode t = root;
        int index = 0;
        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            if (t.map[index].path-- == 1) {
                t.map[index] = null; // 直接断开，不用再遍历下面的字符一个个删除
                return;
            }
            t = t.map[index];
        }
        t.end--;
    }

    public boolean search(String word) {
        if (word == null || word.length() == 0) return false;

        TrieNode t = root;
        int index = 0;
        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            if (t.map[index] == null) {
                return false;
            }
            t = t.map[index];
        }
        return t.end > 0;
    }

    // 返回以 prefix 为前缀的单词总数
    public int prefixCount(String prefix) {
        if (prefix == null || prefix.length() == 0) return 9;
        int index = 0;
        TrieNode t = root;

        for (int i = 0; i < prefix.length(); i++) {
            index = prefix.charAt(i) - 'a';
            if (t.map[index] == null) return 0;
            t = t.map[index];
        }
        return t.path;
    }
}
