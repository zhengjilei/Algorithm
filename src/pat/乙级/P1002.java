package pat.乙级;

import java.util.Scanner;

/**
 * Created by lenovo on 2018/4/23.
 */
public class P1002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        char[] chars = s.toCharArray();
        long count = 0;
        for (char c : chars) {
            count += (c - '0');
        }
        String s1 = String.valueOf(count);
        int length = s1.length();
        String result = "";

        for(int i=0;i<length-1;i++){
            String mappingStr = mapping(s1.charAt(i));
            result+=mappingStr;
            result+=" ";
        }
        String lastStr = mapping(s1.charAt(length-1));
        result+=lastStr;
        System.out.println(result);
    }

    public static String mapping(char c) {
        switch (c) {
            case '0':
                return "ling";
            case '1':
                return "yi";
            case '2':
                return "er";
            case '3':
                return "san";
            case '4':
                return "si";
            case '5':
                return "wu";
            case '6':
                return "liu";
            case '7':
                return "qi";
            case '8':
                return "ba";
            case '9':
                return "jiu";
            default:
                return null;
        }
    }
}
