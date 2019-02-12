package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/2/12
 */
public class Q056_Merge {
    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return " (" + this.start + "," + this.end + ") ";
        }
    }

    // 删除被合并的 interval 对象
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) return intervals;
        // 按照 start 进行排序
        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });

        Iterator<Interval> iterator = intervals.iterator();
        Interval prev = iterator.next(), cur = null;

        // 必须用迭代器才能 边遍历边删除
        while (iterator.hasNext()) {
            cur = iterator.next();
            if (prev.end >= cur.start) {
                // prev 和 cur 可以合并
                if (prev.end < cur.end) {
                    prev.end = cur.end;
                }
                iterator.remove();
            } else {
                prev = cur;
            }
        }
        return intervals;
    }

    // 不改变原有的列表
    public List<Interval> merge2(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) return intervals;
        // 按照 start 进行排序
        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });

        List<Interval> res = new ArrayList<>();
        Interval prev = new Interval(intervals.get(0).start, intervals.get(0).end);
        res.add(prev);
        Interval cur = null;
        for (int i = 1; i < intervals.size() ; i++) {
            cur = intervals.get(i);
            if (prev.end >= cur.start) {
                // 可合并
                if (prev.end < cur.end) {
                    prev.end = cur.end;
                }
            } else {
                // 不可合并
                Interval interval = new Interval(cur.start, cur.end);
                res.add(interval);
                prev = interval;
            }
        }
        return res;
    }

    @Test
    public void test() {
        Interval a = new Interval(1, 4);
        Interval b = new Interval(0, 2);
        Interval c = new Interval(3, 5);

        List<Interval> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);

        List<Interval> res = merge2(list);
        System.out.println(res);
    }
}
