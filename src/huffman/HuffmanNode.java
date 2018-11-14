package huffman;

/**
 * created by Ethan-Walker on 2018/11/6
 */
public class HuffmanNode implements Comparable<HuffmanNode> {
    double weight;
    HuffmanNode left, right, parent;

    HuffmanNode(){

    }
    HuffmanNode(double weight){
        this.weight = weight;
    }
    @Override
    public int compareTo(HuffmanNode o) {
        return this.weight - o.weight > 0 ? 1 : -1;
    }
}
