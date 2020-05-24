package com.cai.recursion;
/**
 * 迷宫
 * @author Administrator
 *
 */
public class MiGong {
	
	
	static int[][] map = new int[8][7];

	public static void main(String[] args) {
		map[3][1] = 1;//墙
		map[3][2] = 1;//墙
		for (int i = 0; i < 7; i++) {
			map[0][i] = 1;//墙
			map[7][i] = 1;
		}
		for (int i = 0; i < 8; i++) {
			map[i][0] = 1;
			map[i][6] = 1;
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println("迷宫走完");
		setWay(map, 1, 1);
//		for (int i = 0; i < 8; i++) {
//			for (int j = 0; j < 7; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		
	}
	
	/**
	 * 迷宫寻找
	 * @param map
	 * @param i  开始行
	 * @param j  开始列
	 * @return
	 * @throws InterruptedException 
	 */
	public static boolean setWay(int[][] map, int i, int j) {
		if(map[6][5] == 2) {
			//找到了目的地
			return true;
		}else {
			if(map[i][j] == 0) {
				//还没走过
				map[i][j] = 2; //开始从哪里开始
				for (int k = 0; k < 8; k++) {
					for (int l = 0; l < 7; l++) {
						System.out.print(map[k][l] + " ");
					}
					System.out.println();
				}
				System.out.println("===============");
				if(setWay(map, i, j+1)) { //向右走
					return true;
				}else if(setWay(map, i-1, j)) {//向上走
					return true;
				}else if(setWay(map, i, j-1)) {//向左走
					return true;
				}else if(setWay(map, i+1, j)) { //想下走
					return true;
				}else {
					map[i][j] = 3;
					for (int k = 0; k < 8; k++) {
						for (int l = 0; l < 7; l++) {
							System.out.print(map[k][l] + " ");
						}
						System.out.println();
					}
					System.out.println("===============");
					return false;
				}
			}else {
				return false;
			}
		}
	}
	
	
}
