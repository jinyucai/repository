package com.cai.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * @author Administrator
 *
 */
public class BubbleSort {
	public static void main(String[] args) {
//		int arr[] = {-2, -1, 10, 3, 3, };
//		bubbleSort(arr);
//		System.out.println("排序后");
//		System.out.println(Arrays.toString(arr));
		int[] arr = new int[80000]; //8万个测试时间复杂度
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 8000000);
		}
		long time1 = System.nanoTime(); //纳秒
		bubbleSort(arr);
		long time2 = System.nanoTime();
		System.out.println("排序耗时" + ((time2-time1)/1000000000) + "秒");
	}
	
	//冒泡排序
	public static void bubbleSort(int[] arr) {
		int temp = 0;
		boolean flag = false;
		for (int j = 0; j < arr.length-1; j++) {
			flag = false;
			for (int i = 0; i < arr.length-1-j; i++) {
				if(arr[i] > arr[i+1]) {
					flag = true;
					temp = arr[i+1];
					arr[i+1] = arr[i];
					arr[i] = temp;
				}
			}
			if(flag == false) {
				break;
			}
		}
	}
}
