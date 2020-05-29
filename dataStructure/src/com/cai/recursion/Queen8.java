package com.cai.recursion;
/**
 * 八皇后问题
 * @author Administrator
 *
 */
public class Queen8 {
	
	static int[] array = new int[8];
	static int count = 0;
	public static void main(String[] args) {
		putQueen(0);
		System.out.printf("共有%d总摆法", count);
	}
	
	/**
	 * 摆放八皇后
	 * @param n 行号也就是第几个八皇后
	 */
	public static void putQueen(int n) {
		if(n == 8) {
			++count;
			print();
			return;
		}
		//从第一行开始摆 i代表列
		for (int i = 0; i < 8; i++) {
			array[n] = i;
			if(judge(n)) {//如果不冲突就下一行
				putQueen(n + 1);
			}
		}
	}
	
	/**
	 * 判断是否和上一行产生冲突 
	 * @param n
	 * @return
	 */
	public static boolean judge(int n) {
		for (int i = 0; i < n; i++) {
			//是不是同一列或者是不是斜线
			if(array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 打印最后的八皇后
	 */
	public static void print() {
		for (int i = 0; i < array.length; i++) { // 行
			System.out.print(array[i] + 1 + " ");
		}
		System.out.println();
	}
	
}
