package com.cai.search;
/**
 * 线性查找
 * @author Administrator
 *
 */
public class SeqSearch {
	public static void main(String[] args) {
		int[] arr = {1, 3, -2, 4, 5, 9, -5};
		int index = seqSearch(arr, 10);
		if(index == -1) {
			System.out.println("没有找到");
		}else {
			System.out.printf("找到了索引为：%d", index);
		}
	}
	
	public static int seqSearch(int[] arr, int taget) {
		for (int i = 0; i < arr.length; i++) {
			if(taget == arr[i]) {
				return 1;
			}
		}
		return -1;
	}
}
