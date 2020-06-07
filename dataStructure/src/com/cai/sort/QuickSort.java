package com.cai.sort;

import java.util.Arrays;

/**
 * 快速排序
 * @author Administrator
 *
 */
public class QuickSort {
	public static void main(String[] args) {
//		int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//		quickSort(arr, 0, arr.length-1);
//		System.out.println(Arrays.toString(arr));
		
		int[] arr = new int[8000000]; //8万个测试时间复杂度
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 8000000);
		}
		long time1 = System.currentTimeMillis(); //纳秒
		quickSort(arr, 0, arr.length-1);
		long time2 = System.currentTimeMillis();
		System.out.println("排序耗时" + ((time2-time1)) + "毫秒");
	}
	
	/**
	 * 
	 * @param arr
	 * @param left 最左边索引
	 * @param right 最右边索引
	 */
	public static void quickSort(int[] arr, int left, int right) {
		int l = left;
		int r = right;
		int pivot = arr[(right + left)/2];//中值
		int temp = 0;
		while(l < r) {
			//排左边
			while(arr[l] < pivot) {
				l++;
			}
			//排右边
			while(arr[r] > pivot) {
				r--;
			}
			if(l >= r) {
				break;
			}
			//当左右都退出while说明两边各找到了一个比中值大一个比中值小的数，交换
			temp = arr[l];
			arr[l] = arr[r];
			arr[r] = temp;
			//这两部不做，循环会原地踏步一直退不出,而且省掉了多余的比较
			if(arr[l]==pivot) {
				r--;
			}
			if(arr[r] == pivot) {
				l++;
			}
		}
		if(l == r) {
			l ++;
			r --;
		}
		//继续左边
		if(left < r) {
			quickSort(arr, left, r);
		}
		if(right > l) {
			quickSort(arr, l, right);
		}
	}
}
