package com.cai.sort;

import java.util.Arrays;

/**
 * 基数排序（桶排序）
 * @author Administrator
 *
 */
public class RedixSort {
	public static void main(String[] args) {
//		int[] arr = {53, 3, 542, 748, 14, 214};
//		redixSort(arr);
//		System.out.println(Arrays.toString(arr));
		int[] arr = new int[8000000]; //8万个测试时间复杂度
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 10000000);
		}
		long time1 = System.currentTimeMillis();
		redixSort(arr);
		long time2 = System.currentTimeMillis();
		System.out.println("排序耗时" + ((time2-time1)) + "毫秒");
	}
	
	//基数排序
	public static void redixSort(int[] arr) {
		//找出arr中位数最大的数
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if(arr[i] > max) {
				max = arr[i];
			}
		}
		//获取最大数字的位数
		int maxLength = (max + "").length();
		//定义一个二维数组，相当于10个桶,考虑最大情况，每个桶的大小是arr.length
		int[][] buket = new int[10][arr.length];
		//定义一个数组记录每个桶中的元素个数
		int[] buketCount = new int[10];
		int index = 0;
		for (int i = 0, n = 1; i < maxLength; i++, n *= 10) { // 共有几轮
			for (int j = 0; j < arr.length; j++) {
				//获取位数
				int digit = arr[j]/n % 10; //每次循环的位数,相当于第几个桶
				//将位数对应的数字放入桶中
				buket[digit][buketCount[digit]] = arr[j];
				buketCount[digit] ++;
			}
			//将桶中元素重新放入元素组
			index = 0;
			for (int j = 0; j < buketCount.length; j++) { //共10个桶
				if(buketCount[j] > 0) {
					//如果桶中有元素
					for (int k = 0; k < buketCount[j]; k++) {
						arr[index] = buket[j][k];
						index++;
					}
					//把桶中元素置0
					buketCount[j] = 0;
				}
				
			}
		}
	}
}
