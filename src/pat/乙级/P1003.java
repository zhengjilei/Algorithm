package pat.乙级;

import java.util.Scanner;

/**
 * Created by Ethan-Walker on 2018/4/23.
 */
public class P1003 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = null;
        for(int i=0;i<n;i++){
             s = sc.nextLine();
            boolean matches = s.matches("A*P[A]{1,2}TA*");
            if(matches){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
    }
}
