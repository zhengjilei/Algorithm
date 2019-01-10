package LRU;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 链表实现 LRU 算法思路
 * 最近最少使用策略
 * 核心：越靠近链表尾部的元素是越早之前访问的
 * 访问一个元素val，遍历链表查找该元素所在节点
 * 1. 该节点不存在，说明是第一次访问，需要将该节点插入
 * 若缓存已满，先将链尾(很早之前访问的元素)删除
 * 新元素插入链头
 * 2. 节点存在，该节点是最近访问的（很可能之后很长时间不访问了）
 * 删除该节点，然后在链尾插入同值节点
 * created by Ethan-Walker on 2019/1/9
 */
public class LRUByLIST {

    LinkedList<Integer> lruCache; // 链表实现缓存，靠近链表尾部元素作为越早之前访问的
    int maxSize; // 缓存的最大长度

    public LRUByLIST(int maxSize) {
        this.maxSize = maxSize;
        lruCache = new LinkedList<>();
    }

    public boolean access(int val) {
        Iterator<Integer> iterator = lruCache.iterator();
        boolean deleted = false;
        while (iterator.hasNext()) {
            if (iterator.next() == val) {
                iterator.remove();
                deleted = true;
                break;
            }
        }

        if (!deleted) {
            // 未找到
            if (lruCache.size() == maxSize) { // 缓存已满
                lruCache.removeLast(); // 删除链表尾
            }
            lruCache.addFirst(val);
            return false;
        } else {
            lruCache.add(val); // 已经删除原位置的节点了，重新添加到尾结点
            return true;
        }
    }

}
