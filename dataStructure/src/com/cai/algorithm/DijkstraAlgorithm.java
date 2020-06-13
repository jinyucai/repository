package com.cai.algorithm;

import java.util.Arrays;
import java.util.Map;

/**
 * 迪杰斯特拉算法，求最短路径
 */
public class DijkstraAlgorithm {

    private static final int N = 65535;

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
        graph.dijkstra(6);
        graph.showResult();
    }
}

/**
 * 创建一个图
 */
class Graph {

    private char[] vertex; // 定点个数
    private int[][] matrix; //邻接矩阵
    private visitedVertex vv;//访问实时信息


    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public char[] getVertex() {
        return vertex;
    }

    public void setVertex(char[] vertex) {
        this.vertex = vertex;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    //展示
    public void showGraph(){
        for (int i = 0; i < this.matrix.length; i++) {
            System.out.println(Arrays.toString(this.matrix[i]));
        }
        System.out.println();
    }

    /**
     * 迪杰斯特拉算法实现
     * @param index 出发节点
     */
    public void dijkstra(int index){
        vv = new visitedVertex(vertex.length, index);
        update(index);
        for (int j = 1; j < vertex.length; j++) {
            int i = vv.updateArr();
            update(i);
        }
    }


    public void showResult(){
        vv.show();
    }

    /**
     * 实时更新
     * @param index 出发的节点
     */
    public void update(int index){
        int len = 0;
        for (int i = 0; i < vertex.length; i++) {
            //vv.getDis()[i] :出发节点到i节点的距离
            len = vv.getDis(index) + matrix[index][i];
            if(!vv.isVisited(i) && len < vv.getDis(i)){
                vv.updateDis(i, len);
                vv.updatePreVisited(i, index);
            }
        }
    }

}

/**
 * 已经访问过得节点
 */
class visitedVertex {
    private int[] already_arr;//存放访问状态
    private int[] dis;//出发节点到目标节点的距离
    private int[] pre_visited;//前驱节点下标

    /**
     *
     * @param length 定点个数
     * @param index 出发节点的下标
     */
    public visitedVertex(int length, int index) {
        already_arr = new int[length];
        dis = new int[length];
        pre_visited = new int[length];
        already_arr[index] = 1;
        //距离都设置为最长
        Arrays.fill(dis, 65535);
        dis[index] = 0;//自己到自己设置为0
    }

    public int[] getAlready_arr() {
        return already_arr;
    }

    public void setAlready_arr(int[] already_arr) {
        this.already_arr = already_arr;
    }

    public int[] getDis() {
        return dis;
    }

    public void setDis(int[] dis) {
        this.dis = dis;
    }

    public int[] getPre_visited() {
        return pre_visited;
    }

    public void setPre_visited(int[] pre_visited) {
        this.pre_visited = pre_visited;
    }

    /**
     * 判断节点是否访问过
     * @param index
     * @return
     */
    public boolean isVisited(int index){
        return already_arr[index] == 1;
    }

    /**
     * 更新距离
     */
    public void updateDis(int index, int length){
        dis[index] = length;
    }

    /**
     * 更新前驱结点
     * @param index 节点的索引
     * @param pre  前驱结点的索引
     */
    public void updatePreVisited(int index, int pre){
        pre_visited[index] = pre;
    }

    /**
     * 得到节点的距离
     * @param index
     * @return
     */
    public int getDis(int index){
        return dis[index];
    }

    /**
     * 依次访问剩下的没被访问过得节点
     * @return
     */
    public int updateArr() {
        int min = 65535;
        int index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if(already_arr[i] == 0 && dis[i] < min){
                min = dis[i];
                index = i;
            }
        }
        already_arr[index] = 1;
        return index;
    }


    public void show(){
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        for (int i = 0; i < dis.length; i++) {
            System.out.print("(" + vertex[i] + ")" + dis[i] + "\t");
        }
    }

}