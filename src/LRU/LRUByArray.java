package LRU;

/**
 * created by Ethan-Walker on 2019/1/9
 */
public class LRUByArray {
    int[] lruCache;  // 越靠近数组尾部，越是最早之前访问的元素
    int count; // 缓存中当前元素个数

    public LRUByArray(int maxSize) {
        lruCache = new int[maxSize];
    }

    public boolean access(int val) {
        for (int i = 0; i < count; i++) {
            if (lruCache[i] == val) {
                // 已经存在，将其移到数组尾部
                for (int j = i + 1; j < count; j++) {
                    lruCache[j - 1] = lruCache[j];
                }
                lruCache[count] = val;
                return false;
            }
        }

        // 不存在
        if (count == lruCache.length) { // 缓存已满，删除尾部元素
            for (int i = count - 2; i >= 0; i--) {
                lruCache[i + 1] = lruCache[i];
            }
        } else {
            for (int i = count - 1; i >= 0; i--) {
                lruCache[i + 1] = lruCache[i];
            }
        }
        lruCache[0] = val; // 插入数组头部
        return true;
    }
}
