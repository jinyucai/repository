package com.cai.algoratnm;

import sun.misc.LRUCache;
import sun.security.provider.certpath.Vertex;

import javax.sound.midi.Soundbank;
import java.util.Arrays;

/**
 * 克鲁斯卡尔最小生成树算法
 * 字符最大的是终点
 */
public class KruskaAlgorithm {

    private static final int INF = Integer.MAX_VALUE;//给个很大的数表示边不连通

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrx = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}
        };
        Kruska kruska = createKruska(vertexs, matrx);
        printKruska(kruska);
        EdgesData[] edges = getEdges(kruska);
        sortEdge(edges);
        System.out.println(Arrays.toString(edges));
        EdgesData[] minEdges = kruskal(kruska);
        System.out.println(Arrays.toString(minEdges));
    }

    /**
     * 克鲁斯卡尔算法，生成最小生成图
     * @param kruska
     */
    public static EdgesData[] kruskal(Kruska kruska){
        //ends: 存放节点对应的末尾节点所在的索引，其本身的索引就是vertex的索引，所以长度和vertex一样
        int[] ends = new int[kruska.getEdgNum()];
        //存放得到的最小生成图的边
        EdgesData[] minEdges = new EdgesData[kruska.getEdgNum()];
        int index = 0;//最小生成图的下标从0开始
        //得到所有的边
        EdgesData[] edges = getEdges(kruska);
        sortEdge(edges);//排序
        //开始生成
        for (int i = 0; i < kruska.getEdgNum(); i++) {
            char start = edges[i].getStart();
            char end = edges[i].getEnd();
            int m = getPosition(start, kruska.getVertexs());//边的第一个节点的下标
            int n = getPosition(end, kruska.getVertexs());//边的第二个节点的下标
            //得到节点下标所对应的末尾节点的下标
            int im = getEnd(ends, m);
            int in = getEnd(ends, n);
            if(im != in){
                //说明两个节点的末尾下标不一样
                //此时im的末尾下标就变成了in了，加入ends中
                ends[im] = in;
                //将该边加入最小生成图
                minEdges[index++] = edges[i];
            }
        }
        return minEdges;
    }


    /**
     * 创建一个克鲁斯卡尔
     * @param vertexs
     * @param matrix
     * @return
     */
    public static Kruska createKruska(char[] vertexs, int[][] matrix){
        Kruska kruska = new Kruska(vertexs, matrix);
        int num = 0;
        //统计边
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i+1; j < vertexs.length; j++) {
                if(matrix[i][j] != INF){
                    kruska.setEdgNum(++num);
                }
            }
        }
        return kruska;
    }

    /**
     * 打印图
     * @param kruska
     */
    public static void printKruska(Kruska kruska){
        int[][] matrix = kruska.getMatrix();
        for (int i = 0; i < kruska.getVertexs().length; i++) {
            for (int j = 0; j < kruska.getVertexs().length; j++) {
                System.out.printf("%15d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 返回对应的定点下标
     * @param ch 定点的饿值
     * @return
     */
    public static int getPosition(char ch, char[] vertex){
        for (int i = 0; i < vertex.length; i++) {
            if(vertex[i] == ch){
                return i;
            }
        }
        return -1;
    }


    /**
     * 得到所有的边
     * @param kruska
     * @return
     */
    public static EdgesData[] getEdges(Kruska kruska){
        EdgesData[] edgesDatas = new EdgesData[kruska.getEdgNum()];
        int index = 0;
        for (int i = 0; i < kruska.getVertexs().length; i++) {
            for (int j = i+1; j < kruska.getVertexs().length; j++) {
                if(kruska.getMatrix()[i][j] != INF){
                    EdgesData edgesData = new EdgesData(kruska.getVertexs()[i], kruska.getVertexs()[j], kruska.getMatrix()[i][j]);
                    edgesDatas[index++] = edgesData;
                }
            }
        }
        return edgesDatas;
    }


    /**
     *用于判断是不是构成闭合回路
     * @param ends 已经加入的定点
     * @param i 传入定点的下标
     * @return 返回对应定点的末尾定点的下标
     */
    public static int getEnd(int[] ends, int i){
        while (ends[i] != 0){
            i = ends[i];
        }
        return i;
    }

    /**
     * 根据边的权值排序
     * @param edgesDatas
     */
    public static void sortEdge(EdgesData[] edgesDatas){
        for (int i = 0; i < edgesDatas.length - 1; i++) {
            for (int j = 0; j < edgesDatas.length - (i + 1); j++) {
                if(edgesDatas[j].getWeight() > edgesDatas[j + 1].getWeight()){
                    EdgesData temp = edgesDatas[j];
                    edgesDatas[j] = edgesDatas[j + 1];
                    edgesDatas[j + 1] = temp;
                }
            }
        }
    }

}

class Kruska {
    private int edgNum; //边的个数
    private char[] vertexs;//节点
    private int[][] matrix;//邻接矩阵

    public Kruska(char[] vertexs, int[][] matrix) {
        this.vertexs = vertexs;
        this.matrix = matrix;
    }

    public int getEdgNum() {
        return edgNum;
    }

    public void setEdgNum(int edgNum) {
        this.edgNum = edgNum;
    }

    public char[] getVertexs() {
        return vertexs;
    }

    public void setVertexs(char[] vertexs) {
        this.vertexs = vertexs;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
}

/**
 * 图的边
 */
class EdgesData {
    private char start;//边的开始
    private char end;//边的结束
    private int weight;//边的权值

    public EdgesData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public char getStart() {
        return start;
    }

    public void setStart(char start) {
        this.start = start;
    }

    public char getEnd() {
        return end;
    }

    public void setEnd(char end) {
        this.end = end;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EdgesData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}
