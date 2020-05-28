package com.cai.sort;

import java.util.Arrays;

/**
 * 归并排序
 * @author Administrator
 *
 */
public class MergeSort {
	
	public static void main(String[] args) {
//		int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
//		int[] temp = new int[arr.length];
//		mergeSort(arr, 0, arr.length-1, temp);
//		System.out.println(Arrays.toString(arr));
		int[] arr = new int[100000000]; //8万个测试时间复杂度
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 100000000);
		}
		int[] temp = new int[arr.length];
		long time1 = System.currentTimeMillis();
		mergeSort(arr, 0, arr.length-1, temp);
		long time2 = System.currentTimeMillis();
		System.out.println("排序耗时" + ((time2-time1)) + "毫秒");
	}
	
	/**
	 * 拆分
	 * @param arr
	 * @param left
	 * @param right
	 * @param temp
	 */
	public static void mergeSort(int[] arr, int left, int right, int[] temp) {
		if(left < right) {
			int mid = (left + right)/2; //中间索引
			mergeSort(arr, left, mid, temp);//拆分左边
			mergeSort(arr, mid+1, right, temp);//右边
			merge(arr, left, mid, right, temp);
		}
	}
	
	/**
	 * 先写合并的代码
	 * @param arr 要排序的数组
	 * @param left 左边的索引
	 * @param mid 中间索引（左边最右边）
	 * @param right 最右边索引
	 * @param temp 临时数组
	 */
	public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
		int l = left;
		int r = mid + 1;//右边开始索引
		int t = 0;//临时数组的开始索引
		while(l <= mid && r <= right) {
			if(arr[l] <= arr[r]) {
				temp[t] = arr[l];
				l++;
				t++;
			}else {
				temp[t] = arr[r];
				r++;
				t++;
			}
		}
		//把剩下的数放入临时数组
		while(l <= mid) {
			temp[t] = arr[l];
			l++;
			t++;
		}
		while(r <= right) {
			temp[t] = arr[r];
			r++;
			t++;
		}
		//临时数组合并到原始数组
		t = 0; // 每一次的小合并都从0开始，所以这里置0
		int tempLeft = left;
		while(tempLeft <= right) {
			//合并左边
			arr[tempLeft] = temp[t];
			tempLeft++;
			t++;
		}
	}
	
}
