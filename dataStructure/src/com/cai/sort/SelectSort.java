package com.cai.sort;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * 选择排序
 * @author Administrator
 *
 */
public class SelectSort {
	
	public static void main(String[] args) {
//		int[] arr = {2, 4, -2, 10, -9, 3};
//		selectSort(arr);
//		System.out.println(Arrays.toString(arr));
		
		int[] arr = new int[80000]; //8万个测试时间复杂度
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 8000000);
		}
		long time1 = System.currentTimeMillis(); //纳秒
		selectSort(arr);
		long time2 = System.currentTimeMillis();
		System.out.println("排序耗时" + ((time2-time1)) + "毫秒");
	}
	
	
	public static void selectSort(int[] arr) {
		for (int i = 0; i < arr.length-1; i++) {
			int min = arr[i];
			int minIndex = i;
			for (int j = i+1; j < arr.length; j++) {
				if(min > arr[j]) {
					minIndex = j;
					min = arr[j];
				}
			}
			arr[minIndex] = arr[i];
			arr[i] = min;
		}
	}
}