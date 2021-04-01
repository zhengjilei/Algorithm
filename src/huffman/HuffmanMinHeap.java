package huffman;

import java.util.Arrays;

/**
 * created by Ethan-Walker on 2018/11/6
 */
public class HuffmanMinHeap {

    HuffmanNode[] heap;
    int currentSize;
    int maxSize = 100;

    public HuffmanMinHeap(HuffmanNode[] heap, int currentSize) {
        maxSize = maxSize > currentSize ? maxSize : currentSize;
        this.currentSize = currentSize;
        this.heap = heap;
        buildMinHeap(0, currentSize);
    }

    public HuffmanMinHeap() {
        this.heap = new HuffmanNode[maxSize];
    }

    public void buildMinHeap(int startIndex, int length) {
        int position = length / 2 - 1;
        while (position >= 0) {
            siftDown(position, length - 1);
            position--;
        }
    }

    public void insert(HuffmanNode node) {
        if (currentSize == maxSize) {
            maxSize = maxSize * 2;
            heap = Arrays.copyOf(heap, maxSize);
        }
        heap[currentSize] = node;
        currentSize++;
        siftUp(currentSize - 1);
    }

    public void siftUp(int startIndex) {
        int j = startIndex;
        int i = (j - 1) / 2;
        while (j > 0) {
            if (heap[j].compareTo(heap[i]) < 0) {
                swap(heap, i, j);
                j = i;
                i = (j - 1) / 2;
            } else {
                break;
            }
        }
    }

    public HuffmanNode removeTop() {
        HuffmanNode node = null;

        if (currentSize > 0) {
            node = heap[0];
            heap[0] = heap[currentSize - 1];
            currentSize--;
            siftDown(0, currentSize - 1);
        }
        return node;
    }

    public void siftDown(int startIndex, int endIndex) {
        int i = startIndex;
        int j = 2 * i + 1;
        while (j <= endIndex) {
            if (j < endIndex && heap[j + 1].compareTo(heap[j]) < 0) j++;
            if (heap[i].compareTo(heap[j]) < 0) break;
            else {
                swap(heap, i, j);
                i = j;
                j = 2 * i + 1;
            }
        }
    }

    private void swap(HuffmanNode[] heap, int i, int j) {
        HuffmanNode t = heap[i];
        heap[i] = heap[j];
        heap[j] = t;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void printHeap() {
        System.out.print("最小堆: ");
        for (int i = 0; i < currentSize; i++) {
            System.out.printf("%6.1f", heap[i].weight);
        }
        System.out.println();
    }
}
