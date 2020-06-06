package com.cai.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 图结构
 */
public class GraphDemo {
    public static void main(String[] args) {
        List<String> vertexes = Arrays.asList("A", "B", "C", "D", "E");
        Graph graph = new Graph(vertexes.size());
        for (String vertex : vertexes) {
            graph.addVertex(vertex);
        }
        graph.addEdges(0, 1, 1);
        graph.addEdges(0, 2, 1);
        graph.addEdges(1, 2, 1);
        graph.addEdges(1, 3, 1);
        graph.addEdges(1, 4, 1);
        graph.showGraph();
        System.out.println("深度优先");
        graph.dfs();
        System.out.println("\n广度优先");
        graph.bfs();
    }
}

class Graph {

    private List<String> vertex; //定点值

    private int[][] edges; //边

    private int numOfEdges; //边的数目

    boolean[] isVisited; //是否被访问过

    public List<String> getVertex() {
        return vertex;
    }

    public int[][] getEdges() {
        return edges;
    }

    public int getNumOfEdges() {
        return numOfEdges;
    }

    public Graph(int n) {
        this.vertex = new ArrayList<>();
        this.edges = new int[n][n];
    }

    /**
     * 添加节点
     * @param vertex
     */
    public void addVertex(String vertex){
        this.vertex.add(vertex);
    }

    /**
     * 添加边
     * @param v1  指向的节点下标
     * @param v2  被指向的节点下标
     * @param weight  权值
     */
    public void addEdges(int v1, int v2, int weight){
        if(v1 == v2 || edges[v1][v2] == 1){
            //防止重复添加
            return;
        }
        edges[v1][v2] = 1;
        numOfEdges ++;
    }

    /**
     * 获取对应边的权值
     * @param v1
     * @param v2
     * @return
     */
    public int getWight(int v1, int v2){
        return this.edges[v1][v2];
    }

    /**
     * 根据下标获取值
     * @return
     */
    public String getValueByIndex(int index){
        return this.vertex.get(index);
    }


    /**
     * 展示
     */
    public void showGraph(){

        for (int i = 0; i < this.edges.length; i++) {
            System.err.println(Arrays.toString(this.edges[i]));
        }
    }

    /**
     * 获取第一个邻接节点的下标
     * @param v
     * @return
     */
    public int getFirstNeighbor(int index){
        for (int i = 0; i < this.vertex.size(); i++) {
            if(this.edges[index][i] == 1){
                return i;
            }
        }
        return -1;
    }

    /**
     * 返回下一个邻接节点
     * @param v1  相当于行号
     * @param v2  列号
     * @return
     */
    public int getNextNeighbor(int v1, int v2){
        for (int i = (v2+1); i < this.vertex.size(); i++) {
            if(this.edges[v1][v2] == 1){
                return i; //下一个邻接节点的下标
            }
        }
        return -1;
    }

    //重载深度遍历如果第0行没找到也要继续
    public void dfs(){
        this.isVisited = new boolean[this.vertex.size()];
        for (int i = 0; i < this.vertex.size(); i++) {
            if(!this.isVisited[i]){
                dfs(isVisited, i);
            }
        }
    }

    /**
     * 深度遍历
     * @param i
     */
    public void dfs(boolean[] isVisited, int i){
        System.out.print(getValueByIndex(i) + "->");
        //把当前的节点设置为已被访问
        isVisited[i] = true;
        int w = getFirstNeighbor(i);
        while (w != -1){
            if(isVisited[w] == false){
                dfs(isVisited, w);
            }
            w = getNextNeighbor(i, w);
        }

    }

    /**
     * 重载广度优先
     */
    public void bfs(){
        this.isVisited = new boolean[this.vertex.size()];
        for (int i = 0; i < this.vertex.size(); i++) {
            if(!this.isVisited[i]){
                bfs(isVisited, i);
            }
        }
    }


    /**
     * 图的广度优先便利
     * @param isVisited
     * @param i
     */
    public void bfs(boolean[] isVisited, int i){
        Integer u; //当前节点
        Integer w; //下一个邻接节点(u的下一个)
        System.out.print(getValueByIndex(i) + "->");
        isVisited[i] = true;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(i);
        while(!queue.isEmpty()){
           //取出一个数来往后查找
            u = queue.removeFirst(); //下一个节点
            w = getFirstNeighbor(u);
            while (w != -1){ //表示一直向后可以取到
                if(!isVisited[w]){
                    System.out.print(getValueByIndex(w) + "->");
                    isVisited[w] = true;
                    //将打印出来的那个加入队列
                    queue.addLast(w);
                }
                //否则，已经被访问过了
                w = getNextNeighbor(u, w);
            }
        }
    }

}