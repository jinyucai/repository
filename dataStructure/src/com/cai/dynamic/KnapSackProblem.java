package com.cai.dynamic;

/**
 * 动态匹配
 */
public class KnapSackProblem {

    public static void main(String[] args) {
        int[] w = {1, 4, 3}; //物品的重量
        int[] val = {1500, 3000, 2000}; //物品的价值
        int m = 4;//背包的容量
        int n = val.length; //物品个数

        /**
         * 这个二维数组做一下说吗，以下是我个人理解
         * n+1 = 4, 表示0，1，2，4
         * m+1 = 5， 表示0，1,2,3,4
         * 如下图 四行五列， 后三行表示商品种类，后四列表示放入背包的容量，共有12这么多种可能
         * 0    0   0   0   0
         *
         * 0    0   0   0   0
         *
         * 0    0   0   0   0
         *
         * 0    0   0   0   0
         */
        int[][] v = new int[n+1][m+1];
        int[][] path = new int[n+1][m+1];
        for (int i = 1; i < v.length; i++) { //行，不处理第一行
            for (int j = 1; j < v[0].length; j++) {//列，不处理第一列
                if(w[i-1] > j){//物品重量大于背包重量 ，这里背包重量是4，就是v[0].length条件
                    v[i][j] = v[i-1][j];  //放不下，只能放上一步的
                }else{
                    if(v[i-1][j] < val[i-1] + v[i-1][j-w[i-1]]){
                        v[i][j] = val[i-1] + v[i-1][j-w[i-1]];
                        path[i][j] = 1;
                    }else{
                        v[i][j] = v[i-1][j];
                    }
                }
            }
        }


        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + "\t");
            }
            System.out.println();
        }
        
        int i = path.length - 1;
        int j = path[0].length - 1;
        //最有的方法在最后的，因为数量最大
        while (i > 0 && j > 0){
            if(path[i][j] == 1){
                System.out.printf("第%d个商品被加入\n", i);
                j = j-w[i-1];//容量减小
            }
            i--;
        }
    }

}
