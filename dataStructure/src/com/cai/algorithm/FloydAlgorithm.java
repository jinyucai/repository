package com.cai.algorithm;

import java.util.Arrays;

/**
 * 弗洛伊德算法  ，求最短路径
 */
public class FloydAlgorithm {

    private static final int N = Integer.MAX_VALUE/2;

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};

        FloydGraph floydGraph = new FloydGraph(vertex, matrix);
        floydGraph.floyd();
        floydGraph.show();
    }
}

/**
 * 创建一个图
 */
class FloydGraph {
    private char[] vertex;//顶点
    private int[][] dis;//路径
    private int[][] pre;//前驱结点，也可以理解成中间节点

    public FloydGraph(char[] vertex, int[][] dis) {
        this.vertex = vertex;
        this.dis = dis;
        //初始化pre
        pre = new int[vertex.length][vertex.length];
        for (int i = 0; i < vertex.length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    public char[] getVertex() {
        return vertex;
    }

    public void setVertex(char[] vertex) {
        this.vertex = vertex;
    }

    public int[][] getDis() {
        return dis;
    }

    public void setDis(int[][] dis) {
        this.dis = dis;
    }

    public int[][] getPre() {
        return pre;
    }

    public void setPre(int[][] pre) {
        this.pre = pre;
    }

    /**
     * 展示
     */
    public void show(){
        for (int i = 0; i < vertex.length; i++) {
            //打印前驱结点(中间节点)
            for (int j = 0; j < vertex.length; j++) {
                System.out.print(vertex[pre[i][j]] + "  ");
            }
            System.out.println("");
            //打印路径
            for (int j = 0; j < vertex.length; j++) {
                System.out.print("(" + vertex[i] + "到" + vertex[j] + "的路径为" + dis[i][j] + ")    ");
            }
            System.out.println("\n");
        }
    }

    /**
     * 佛洛依德算法
     */
    public void floyd(){
        int len = 0;
        //遍历前驱节点（中间节点）
        for (int i = 0; i < vertex.length; i++) {
            //出发顶点
            for (int j = 0; j < vertex.length; j++) {
                //结束顶点
                for (int k = 0; k < vertex.length; k++) {
                    len = dis[j][i] + dis[i][k];
                    if(len < dis[j][k]){
                        dis[j][k] = len;
                        pre[j][k] = pre[i][k];
                    }
                }
            }
        }
    }


}
