package com.cai.sort;

import java.util.Arrays;

/**
 * 希尔排序
 * @author Administrator
 *
 */
public class ShellSort {
	public static void main(String[] args) {
//		int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//		shellSort(arr);
//		System.out.println("希尔排序" + Arrays.toString(arr));
		int[] arr = new int[80000]; //8万个测试时间复杂度
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 8000000);
		}
		long time1 = System.currentTimeMillis(); //纳秒
		shellSort(arr);
		long time2 = System.currentTimeMillis();
		System.out.println("排序耗时" + ((time2-time1)) + "毫秒");
	}
	
	/**
	 * 希尔排序交换法
	 * @param arr
	 */
//	public static void shellSort(int[] arr) {
//		//第一次分成5组
//		int temp = 0;
//		for (int gap = arr.length/2; gap > 0; gap/=2) {
//			for (int i = gap; i < arr.length; i++) {
//				for (int j = i-gap; j >= 0; j-=gap) {
//					if(arr[j] > arr[j+gap]) {
//						temp = arr[j];
//						arr[j] = arr[j+gap];
//						arr[j+gap] = temp;
//					}
//				}
//			}
//		}
//	}
	
	//交换式的希尔排序优化插入
	public static void shellSort(int[] arr) {
		for (int gap = arr.length/2; gap > 0; gap/=2) {
			for (int i = gap; i < arr.length; i++) {
				int j = i;
				int temp = arr[i];
				if(arr[i] < arr[i - gap]) {
					while(j - gap >= 0 && temp < arr[j - gap]) {
						arr[j] = arr[j - gap];
						j -= gap;
					}
					arr[j] = temp;
				}
			}
		}
	}
}
