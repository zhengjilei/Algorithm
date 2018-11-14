package huawei.two;

import java.util.Scanner;

/**
 * Created by Ethan-Walker on 2018/4/18.
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long years = sc.nextInt();
        long weeks = sc.nextInt();

        if(years<0) {
            System.out.println("-1");
            return ;
        };
        long nowYear = 1900 + years - 1;

        long countDays = 0;
        long totalCount = 0;

        for (int i = 1900; i <= nowYear; i++) {

            for (int j = 1; j <= 12; j++) {
                // j 月份 13 日
                if ((countDays+13) % 7 == weeks) {
                    totalCount++;
                }
                countDays = countDays + getMonthDays(j, i);
            }

        }
        System.out.println(totalCount);

    }

    public static int getMonthDays(int month, int year) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
                    return 29;
                else return 28;
            default:
                return 30;

        }
    }
}
