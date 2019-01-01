package lca;

import java.util.*;

/**
 * 超时
 * created by Ethan-Walker on 2018/12/31
 */
public class HDU4547E {

    public static HashMap<String, Integer> stringIndex = new HashMap<>();
    public static HashMap<Integer, String> indexString = new HashMap<>();

    static HashMap<Integer, List<Integer>> sonEdges = new HashMap<>();

    static HashMap<Integer, List<Integer>> queriesMap = new HashMap<>();
    static HashMap<Integer, List<Integer>> answerIndexMap = new HashMap<>();

    static HashMap<Integer, Integer> ancestorMap = new HashMap<>();


    static int[] parent;
    static int[] ufsets;

    static int[] answers;
    static Query[] queries;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int n, m;
        String str1, str2;
        int index1, index2;

        while (t > 0) {

            n = scanner.nextInt();
            m = scanner.nextInt();
            sonEdges.clear();
            queriesMap.clear();
            answerIndexMap.clear();
            ancestorMap.clear();
            stringIndex.clear();
            indexString.clear();

            parent = new int[n];
            ufsets = new int[n];
            answers = new int[m];
            queries = new Query[m];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                ufsets[i] = i;
            }

            for (int i = 0; i < n - 1; i++) {
                str1 = scanner.next();
                str2 = scanner.next();

                index1 = stringIndex.containsKey(str1) ? stringIndex.get(str1) : stringIndex.size();
                stringIndex.putIfAbsent(str1, index1);
                indexString.putIfAbsent(index1, str1);

                index2 = stringIndex.containsKey(str2) ? stringIndex.get(str2) : stringIndex.size();
                stringIndex.putIfAbsent(str2, index2);
                indexString.putIfAbsent(index2, str2);

                parent[index1] = index2;

                sonEdges.putIfAbsent(index2, new LinkedList<>());
                sonEdges.get(index2).add(index1);
            }

            for (int i = 0; i < m; i++) {
                str1 = scanner.next();
                str2 = scanner.next();

                index1 = stringIndex.get(str1);
                index2 = stringIndex.get(str2);

                queries[i] = new Query(index1, index2);
                queriesMap.putIfAbsent(index1, new LinkedList<>());
                queriesMap.putIfAbsent(index2, new LinkedList<>());

                answerIndexMap.putIfAbsent(index1, new LinkedList<>());
                answerIndexMap.putIfAbsent(index2, new LinkedList<>());

                queriesMap.get(index1).add(index2);
                queriesMap.get(index2).add(index1);

                answerIndexMap.get(index1).add(i);
                answerIndexMap.get(index2).add(i);


            }
            index1 = 0;
            while (index1 != parent[index1]) {
                index1 = parent[index1];
            }
            // 根节点是 index1
            dfs(index1);
            int[] result = calResult();

            for (int r : result) {
                System.out.println(r);
            }

            t--;
        }
    }

    static int[] calResult() {
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            // q.o1-->q.o2
            if (queries[i].o1 == answers[i]) {
                result[i] = 1;
            } else {
                int index = queries[i].o1;
                int count = 0;
                while (index != answers[i]) {
                    index = parent[index];
                    count++;
                }
                if (queries[i].o2 == answers[i]) {
                    result[i] = count;
                } else {
                    result[i] = count + 1;
                }
            }

        }
        return result;
    }

    static void dfs(int index) {
        List<Integer> children = sonEdges.get(index);

        if (children != null) {
            for (int son : children) {
                dfs(son);
                union(son, index);
                ancestorMap.put(findFather(index), index); // 集合的祖先是 当前节点 index
            }
        } else {
            ancestorMap.put(findFather(index), index);
        }

        int father = 0;
        int queryIndex, answerIndex;

        List<Integer> queriesIndexList = queriesMap.get(index);
        List<Integer> answersIndexList = answerIndexMap.get(index);
        if (queriesIndexList != null) {
            for (int i = 0; i < queriesIndexList.size(); i++) {
                queryIndex = queriesIndexList.get(i);
                answerIndex = answersIndexList.get(i);
                father = findFather(queryIndex); // 查找目标顶点所在集合
                if (ancestorMap.containsKey(father)) {
                    answers[answerIndex] = ancestorMap.get(father);
                }
            }
        }


    }

    static int findFather(int index) {
        int fa = index;
        while (ufsets[fa] != fa) {
            fa = ufsets[fa];
        }

        while (ufsets[index] != fa) {
            ufsets[index] = fa;
        }

        return fa;

    }

    static void union(int index1, int index2) {
        int fa1 = findFather(index1);
        int fa2 = findFather(index2);
        if (fa1 != fa2) {
            ufsets[fa1] = fa2;
        }
    }


    static class Query {
        int o1;
        int o2;

        public Query(int o1, int o2) {
            this.o1 = o1;
            this.o2 = o2;
        }

        @Override
        public String toString() {
            return "Query{" +
                    "o1=" + o1 +
                    ", o2=" + o2 +
                    '}';
        }
    }
}
