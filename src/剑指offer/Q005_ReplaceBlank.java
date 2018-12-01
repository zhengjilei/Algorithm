package 剑指offer;


import org.junit.Test;

/**
 * 将字符串中的空格替换成 %20
 * 用 C/C++ 实现，Java实现无法体现出算法的本质
 * 要求在原有的字符串内存中实现
 * created by Ethan-Walker on 2018/12/2
 */
public class Q005_ReplaceBlank {

    public String replace(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                sb.append("%20");
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    @Test
    public void testReplace() {
        System.out.println(replace("abc dsa"));
        System.out.println(replace(" a re  ew123"));
        System.out.println(replace("  "));
        System.out.println(replace(""));
        System.out.println(replace(" "));
        System.out.println(replace("\tdsa 21"));
    }


}
/*
#include <stdio.h>
#include <stdlib.h>

//保证扩展之后的长度要小于 maxLength
void replace(char s[], int maxLength)
{
	int count = 0,originalLen=0;  // originalLen 不包括 '\0'的长度
	char *t = s;
	while (*t != '\0') {
		if (*t == ' ') count++;
		t++;
		originalLen++;
	}

	int addLength = count * 2;
	if (originalLen + addLength >= maxLength) return;
	int p = originalLen; // p 指向'\0'
	int q = p + addLength;

	while (p >=0) {
		if (s[p] == ' ') {
			s[q--] = '0';
			s[q--] = '2';
			s[q--] = '%';
		}
		else {
			s[q--] = s[p];
		}
		p--;
	}
}
int main()
{
	char str[100]= "123 43fer  r4";
	replace(str, 100);
	printf("%s\n", str);

	char str1[100] = " de r4";
	replace(str1, 100);
	printf("%s\n", str1);

	char str2[100] = "av e3 21 ";
	replace(str2, 100);
	printf("%s\n", str2);

	char str3[100] = " ";
	replace(str3, 100);
	printf("%s\n", str3);

	system("pause");

}

 */
