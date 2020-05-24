package com.cai.sort;

import java.util.Arrays;

/**
 * 插入排序
 * @author Administrator
 *
 */
public class InsetSort {
	public static void main(String[] args) {
		int[] arr = {101, 23, 119, 1};
		insertSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static void insertSort(int[] arr) {
		
		for (int i = 1; i < arr.length; i++) {
			int insertValue = arr[i]; // 第0个数
			int insertIndex = i-1; //第1个索引
			while(insertIndex >= 0 && insertValue < arr[insertIndex]) {
				arr[insertIndex+1] = arr[insertIndex];//把后一个数赋值给前一个数
				insertIndex--;
			}
			arr[insertIndex + 1] = insertValue;
		}
	}
}