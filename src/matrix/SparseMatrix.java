package matrix;

import java.util.Arrays;
import java.util.Comparator;

public class SparseMatrix {

    Trituple[] trituples;
    int size;

    public SparseMatrix(int size) {
        this.size = size;
        trituples = new Trituple[size];
    }

    public void init(int[][] a) {
        int row = a.length;
        int col = a[0].length;
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (a[i][j] != 0) {
                    trituples[count++] = new Trituple(i, j, a[i][j]);
                }
            }
        }
    }

    /**
     * 稀疏矩阵转置
     */
    public void transpose() {
        for (int i = 0; i < size; i++) {
            int t = trituples[i].col;
            trituples[i].col = trituples[i].row;
            trituples[i].row = t;
        }
        Arrays.sort(trituples, 0, size, new Comparator<Trituple>() {
            @Override
            public int compare(Trituple t1, Trituple t2) {
                int a = t1.row - t2.row;
                if (a != 0) {
                    return a;
                } else {
                    return t1.col - t2.col;
                }
            }
        });
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(trituples[i].row + "," + trituples[i].col + ":" + trituples[i].val);
        }
        System.out.println("----------");
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{
                {1, 2, 0, 0, 0, 0},
                {3, 4, 5, 0, 0, 0},
                {0, 6, 0, 8, 0, 0},
                {0, 0, 9, 10, 0, 0},
                {0, 0, 0, 12, 0, 0}
        };
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] != 0) {
                    count++;
                }
            }
        }
        SparseMatrix matrix = new SparseMatrix(count);
        matrix.init(a);
        matrix.print();
        matrix.transpose();
        matrix.print();
    }

    class Trituple {
        int row;
        int col;
        int val;

        public Trituple(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }
}
