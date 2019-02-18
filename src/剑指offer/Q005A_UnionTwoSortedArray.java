package 剑指offer;

/**
 * 合并两个已经排好序的数组
 * C/C++ 实现
 * 在原有数组上实现
 * O(m+n)
 * created by Ethan-Walker on 2018/12/2
 */
public class Q005A_UnionTwoSortedArray {
    /**
     * 数组a1、a2是排好序的，且末尾有足够多的空余空间容纳 a2
     * 将a2所有的数字全部插入 a1
     * 思路：倒着插
     * 统计两个数组的有效数字总和，得到最终结果的长度
     * a1End /a2End 分别是a1 a2 数组有效数据的末尾位置，
     * 选择两个数组有效位置末尾较大的插入 a1 末尾
     *
     * @param a1
     * @param a2
     * @param a1Len 表示a1数组中有效数据的个数
     * @return
     */
    public int[] union(int[] a1, int[] a2, int a1Len) {
        int a1End = a1Len - 1, a2End = a2.length - 1;
        int resEnd = a1Len + a2.length - 1;

        while (a1End >= 0 && a2End >= 0) {
            if (a1[a1End] >= a2[a2End]) {
                a1[resEnd--] = a1[a1End--];
            } else {
                a1[resEnd--] = a2[a2End--];
            }

        }
        // 如果 a1End>=0 , 不需要动了

        while (a2End >= 0) {
            a1[resEnd--] = a2[a2End--];
        }
        return a1;
    }
}
/*
#include <stdio.h>
#include <stdlib.h>

void uni(int a[], int maxLength, int alen, int b[], int blen)
{
	int endA = alen - 1;
	int endB = blen - 1;
	int lastA = alen + blen - 1; // 指向a、b合并之后的最后一个元素

	while (endA >= 0 && endB >= 0) {
		if (a[endA] <= b[endB]) {
			a[lastA--] = b[endB--];
		}
		else {
			a[lastA--] = a[endA--];
		}
	}

	// 若 endB<0 ，b 全部插入a中，剩余的 endA 不用动了

	// 若 endA<0 , 将剩下的 endB 全部插入 a 的最前面
	while (endB >= 0) {
		a[lastA--] = a[endB--];
	}

}

int main()
{
	int a[100] = { 1,2,3,6,8,9,21 };
	int alen = 7;
	int b[10] = { 2,4,5,5,9,10,12,32,57 };
	int blen = 9;

	uni(a, 100, alen, b, blen);

	for (int i = 0; i < alen + blen; i++) {
		printf("%d ", a[i]);
	}
	printf("\n");

 	system("pause");
}
 */
