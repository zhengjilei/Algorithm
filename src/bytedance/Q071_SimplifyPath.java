package bytedance;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * created by Ethan-Walker on 2019/2/2
 */
public class Q071_SimplifyPath {

    /**
     * 遍历原路径字符串，使用双端队列，队列中只存储一层层的目录
     * 1. 处理.., 将队列尾的目录弹出（如果有的话）
     * 2. 处理结束后，从队头依次弹出目录组成一个路径
     */

    //    /a/../../b/../c//.//
    //    /a//b////c/d//././/..
    public String simplifyPath(String path) {
        ArrayDeque<String> deque = new ArrayDeque<>();
        String[] strs = path.split("/");
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].equals("..")) {
                if (!deque.isEmpty()) { // 当前目录非根目录
                    deque.pollLast();
                }
            } else if (strs[i].length() > 0) {
                // 忽略 . ,其他都作为一层目录名压入队列中
                if (!strs[i].equals(".")) {
                    deque.offerLast(strs[i]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append("/");
            sb.append(deque.pollFirst());
        }
        if (sb.length() == 0) {
            sb.append("/");
        }
        return sb.toString();
    }


    @Test
    public void test() {
        System.out.println(simplifyPath("/home/"));
        System.out.println(simplifyPath("/../"));
        System.out.println(simplifyPath("/home//foo/"));
        System.out.println(simplifyPath("/a/./b/../../c/"));
        System.out.println(simplifyPath("/a/../../b/../c//.//"));
        System.out.println(simplifyPath("/a//b////c/d//././/.."));
        System.out.println(simplifyPath("/a/..."));
        System.out.println(simplifyPath("/..."));
    }

    public static void main(String[] args) {
        String s = "//";
        System.out.println(Arrays.toString(s.split("/")));

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        System.out.println(deque.pollLast());
    }
}
