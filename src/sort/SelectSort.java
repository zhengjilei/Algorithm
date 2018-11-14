package sort;

/**
 * Created by lenovo on 2018/3/26.
 */
public class SelectSort {
    /**
     * 选择排序: 遍历选择最小的数 放在第1位、第二位
     */
    public static void selectSort(int[] a){
        for(int i=0;i<a.length-1;i++){
            int key = a[i];     // key 记录最小值
            int position = i;  // position 记录最小值的位置
            for(int j=i+1;j<a.length;j++){
                if(a[j]<key){
                    key = a[j];
                    position = j;
                }
            }
            a[position] = a[i];
            a[i] = key;
        }
    }
}
