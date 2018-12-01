package 剑指offer;

/**
 * 合并两个已经排好序的数组
 * C/C++ 实现
 * 在原有数组上实现
 * O(m+n)
 * created by Ethan-Walker on 2018/12/2
 */
public class Q0051_UnionTwoSortedArray {

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
