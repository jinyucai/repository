package com.cai.sparesArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 稀疏数组
 * @author Administrator
 *
 */
public class SparseArray {
	public static void main(String[] args) throws Exception {
		int[][] chessArray1 = new int[11][11];
		//赋值
		chessArray1[1][2] = 1; //1代表白字
		chessArray1[2][3] = 2; //2代表黑子
		chessArray1[5][7] = 1;
		//打印
		for (int i = 0; i < chessArray1.length; i++) {
			for (int j = 0; j < chessArray1[i].length; j++) {
				System.out.printf("%d\t", chessArray1[i][j]);
			}
			System.out.printf("\n");
		}
		//将二维数组转成稀疏数组
		int sum = 0; 
		for (int i = 0; i < chessArray1.length; i++) {
			for (int j = 0; j < chessArray1[i].length; j++) {
				if(chessArray1[i][j] != 0) {
					sum ++;
				}
			}
		}
		//定义稀疏数组
		int[][] sparseArray = new int[sum+1][3];
		//第一行
		sparseArray[0][0] = chessArray1.length;//原数组行
		sparseArray[0][1] = chessArray1[0].length;//原数组列
		sparseArray[0][2] = sum;
		int count = 1;
		for (int j = 0; j < chessArray1.length; j++) {
			for (int k = 0; k < chessArray1[j].length; k++) {
				if(chessArray1[j][k] != 0) {
					sparseArray[count][0] = j;
					sparseArray[count][1] = k;
					sparseArray[count][2] = chessArray1[j][k];
					count++;
				}
			}
		}
		System.out.println("===============转成稀疏数组==================");
		//打印稀疏数组
		String chess = "";
		for (int i = 0; i < sparseArray.length; i++) {
			System.out.printf("%d\t%d\t%d\t\n", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
			chess += sparseArray[i][0] + "\t" + sparseArray[i][1] + "\t" + sparseArray[i][2] + "\n";
		}
		//将稀疏数组存入磁盘
		File file = new File("E:\\apk\\chess.txt");
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(chess.getBytes(), 0, chess.length());
		fos.flush();
		fos.close();
		
		//从本地磁盘读取
		FileInputStream fis = new FileInputStream(new File("E:\\apk\\chess.txt"));
		String sparse = "";
		int len = 0;
		byte[] data = new byte[1024];
		while((len = fis.read(data)) != -1) {
			sparse += new String(data, 0, len);
		}
		fis.close();
		String[] sparse2 = sparse.split("\n");
		int[][] sparseArray2 = new int[sparse2.length][3];
		for (int i = 0; i < sparse2.length; i++) {
			sparseArray2[i][0] = Integer.parseInt(sparse2[i].split("\t")[0]);
			sparseArray2[i][1] = Integer.parseInt(sparse2[i].split("\t")[1]);
			sparseArray2[i][2] = Integer.parseInt(sparse2[i].split("\t")[2]);
		}
		//将稀疏数组转成二维数组
		int[][] chessArray2 = new int[sparseArray2[0][0]][sparseArray2[0][1]];
		for (int i = 1; i < sparseArray2.length; i++) {
			chessArray2[sparseArray2[i][0]][sparseArray2[i][1]] = sparseArray2[i][2];
		}
		System.out.println("=================系数还是做转化为二维数组====================");
		for (int i = 0; i < chessArray2.length; i++) {
			for (int j = 0; j < chessArray2[i].length; j++) {
				System.out.printf("%d\t", chessArray2[i][j]);
			}
			System.out.println();
		}
	}
}
