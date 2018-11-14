package huawei.one;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * Created by Ethan-Walker on 2018/4/18.
 *
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int length = sc.nextInt();

        try {
            byte[] bys  = s.getBytes("gbk");

            byte[] resultBys = new byte[bys.length];
            int j = 0;
            for (int i = 0; i < bys.length; i++) {
                if (j < length) {
                    if (bys[i] > 0x8E && bys[i] < 0xFF) {
                        //中文开头
                        if (j < length - 1 && i < bys.length - 1) {
                            resultBys[j++] = bys[i];
                            resultBys[j++] = bys[++i];
                        } else {
                            i++;
                        }
                    } else {
                        if (bys[i] >= '0' && bys[i] <= '9') {
                            continue;
                        } else {
                            resultBys[j++] = bys[i];
                        }
                    }

                }
            }
            String result = new String(resultBys, 0, j, "gbk");
            System.out.println(result);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}






 /* if(bys[i]>0x8E&&bys[i]<0xFF){

            }*/