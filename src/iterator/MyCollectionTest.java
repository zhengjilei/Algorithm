package iterator;

import java.util.Arrays;
import java.util.Iterator;

public class MyCollectionTest {
    public static void main(String[] args) {
        MyCollection<Integer> col = new MyCollection<>();
        for(int i=0;i<30;i++){
            col.add((int)(Math.random()*100));
        }
        Iterator<Integer> iterator = col.iterator();
        while(iterator.hasNext()){
            System.out.printf("%d ",iterator.next());
        }
        System.out.println("\n"+col.size());
    }
}
