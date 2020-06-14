package com.cai.algorithm;

import jdk.nashorn.internal.ir.IfNode;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 马踏棋盘，骑士周游问题
 */
public class HorseChessBoard {

    private static int X; //棋盘的列
    private static int Y;//棋盘的行
    private static boolean[] visited;//每个点是否访问过
    private static boolean finished = false;//完成

    public static void main(String[] args) {
        X = 8;
        Y = 8;
        int[][] chessBoard = new int[Y][X]; //棋盘
        visited = new boolean[X * Y];
        int row = 0; // 开始行的索引
        int column = 0; //开始列的索引
        long start = System.currentTimeMillis();
        traversalChessBoard(chessBoard, row, column, 0);
        long end = System.currentTimeMillis();
        System.out.println("共耗时" + (end - start) + "毫秒");
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[i].length; j++) {
                System.out.print(chessBoard[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     *
     * @param chessBoard 棋盘
     * @param row 所在的x位置
     * @param column 所在的y位置
     * @param step 步数
     */
    public static void traversalChessBoard(int[][] chessBoard, int row, int column, int step){
        chessBoard[row][column] = step;
        //把当前棋子设置为已访问
        visited[row * X + column] = true;
        List<Point> next = next(new Point(column, row));
        sort(next);
        while (!next.isEmpty()){
            Point p = next.remove(0);//取出一个
            if(!visited[p.y * X + p.x]){
                traversalChessBoard(chessBoard, p.y, p.x, step + 1);
            }
        }
        if(step + 1 < (X * Y) && !finished){
            //回溯,将当前的节点置为0
            chessBoard[row][column] = 0;
            //将当前的这个设置为未访问
            visited[row * X + column] = false;
        }else{
            finished = true;
        }
    }


    //得到下个棋子可能的位置
    public static List<Point> next(Point curPoint){
        List<Point> ps = new ArrayList<>();
        Point point = new Point();
        //马儿可能走得位置共有八种
        if((point.x = curPoint.x - 2) >=0 && (point.y = curPoint.y - 1) >= 0){
            ps.add(new Point(point));
        }
        if((point.x = curPoint.x - 1) >=0 && (point.y = curPoint.y - 2) >= 0){
            ps.add(new Point(point));
        }
        if((point.x = curPoint.x + 1) < X && (point.y = curPoint.y - 2) >= 0){
            ps.add(new Point(point));
        }
        if((point.x = curPoint.x + 2) < X && (point.y = curPoint.y - 1) >= 0){
            ps.add(new Point(point));
        }
        if((point.x = curPoint.x + 2) < X && (point.y = curPoint.y + 1) < Y){
            ps.add(new Point(point));
        }
        if((point.x = curPoint.x + 1) < X && (point.y = curPoint.y + 2) < Y){
            ps.add(new Point(point));
        }
        if((point.x = curPoint.x - 1) >=0 && (point.y = curPoint.y + 2) < Y){
            ps.add(new Point(point));
        }
        if((point.x = curPoint.x - 2) >=0 && (point.y = curPoint.y + 1) < Y){
            ps.add(new Point(point));
        }
        return ps;
    }

    /**
     * 非递减排序
     * @param ps
     */
    public static void sort(List<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                if(count1 < count2){
                    return -1;
                }else if(count1 == count2){
                    return 0;
                }else{
                    return 1;
                }
            }
        });
    }

}
