package iterator;

import java.util.Iterator;

public class MyCollection<Item> implements Iterable<Item> {

    Item[] items;

    private final int DEFAULT_SIZE = 20;
    private int count;  // 当前集合中的元素数量
    private int size = DEFAULT_SIZE;

    public MyCollection() {
        items = (Item[]) new Object[size];
    }

    public MyCollection(int n) {
        size = n;
        items = (Item[]) new Object[size];
    }

    @Override
    public Iterator<Item> iterator() {
        return new MyIterator();
    }

    public void add(Item item) {
        if (count < size) {
            items[count++] = item;
        } else {
            int newSize = 2 * size;
            Item[] itemsTemp = (Item[]) new Object[newSize];
            for (int i = 0; i < size; i++) {
                itemsTemp[i] = items[i];
            }
            size = newSize;
            items = itemsTemp;
            items[count++] = item;
        }
    }

    public int size() {
        return size;
    }

    public Item removeLast() {
        return items[--count];
    }

    private class MyIterator  implements Iterator<Item> {
        private int i = count;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return items[--i];
        }
    }
}
