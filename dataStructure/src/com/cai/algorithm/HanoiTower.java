package com.cai.algoratnm;

/**
 * 汉诺塔
 */
public class HanoiTower {

    public static void main(String[] args) {

        hanoiTower(3, 'A', 'B', 'C');
    }

    /**
     * 汉诺塔走法
     * @param num  共几个盘子
     * @param a  柱子A
     * @param b  柱子B
     * @param c  柱子C
     */
    public static void hanoiTower(int num, char a, char b, char c){
        if(num == 1){
            System.out.println("第1个盘：" + a + " ->" + c);
        }else{
            //分为两步，上面所有盘， 底下一个盘
            //1，现将上面所有盘A移动到B
            hanoiTower(num - 1, a, c, b);
            //2，把A盘的一个移到C盘
            System.out.println("第" + num + "个盘：" + a + " ->" + c);
            //3，将B盘移到C盘
            hanoiTower(num - 1, b, a, c);
        }
    }

}
