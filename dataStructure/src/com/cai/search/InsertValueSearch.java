package com.cai.search;
/**
 * 插值查找
 * @author Administrator
 *
 */
public class InsertValueSearch {
	public static void main(String[] args) {
		int[] arr = new int[100];
		for (int i = 0; i < 100; i++) {
			arr[i] = i+1;
		}
		int index = insertValueSearch(arr, 0, arr.length - 1, 100);
		System.out.println(index);
	}
	
	
	public static int insertValueSearch(int[] arr, int left, int right, int value) {
		System.out.println("调用了");
		if(left > right || arr[0] > value || arr[arr.length - 1] < value) {
			return -1; // 没找到
		}
		int midIndex = (right - left) * ((value - arr[left])/(arr[right] - arr[left])) + left;
		int midValue = arr[midIndex];
		if(midValue > value) {
			midIndex--;
			return insertValueSearch(arr, left, midIndex, value);
		}else if(midValue < value) {
			midIndex++;
			return insertValueSearch(arr, midIndex, right, value);
		}else {
			return midIndex;
		}
	}
}
