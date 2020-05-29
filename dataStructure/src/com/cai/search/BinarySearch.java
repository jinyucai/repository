package com.cai.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找，数组必须有序
 * @author Administrator
 *
 */
public class BinarySearch {
	public static void main(String[] args) {
//		int[] arr = {1, 3, 5, 23, 23, 23, 456, 666, 768};
//		int index = binarySearch(arr, 0, arr.length-1, 23);
//		System.out.println(index);
//		ArrayList<Integer> list = binarySearch2(arr, 0, arr.length - 1, 23);
//		System.out.println(list);
		int[] arr = new int[100];
		for (int i = 0; i < 100; i++) {
			arr[i] = i+1;
		}
		int index = binarySearch(arr, 0, arr.length - 1, 100);
		System.out.println(index);
	}
	
	
	public static int binarySearch(int[] arr, int left, int right, int value) {
		System.out.println("调用了");
		if(left > right) {
			return -1;
		}
		int midIndex = (left + right)/2;
		int midValue = arr[midIndex];
		if(value > midValue) {
			return binarySearch(arr, midIndex+1, right, value);
		}else if(value < midValue) {
			return binarySearch(arr, left, midIndex-1, value);
		}else {
			return midIndex;
		}
	}
	
	//连续找相同的
	public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int value) {
		if(left > right) {
			return new ArrayList<Integer>();
		}
		int midIndex = (left + right)/2;
		int midValue = arr[midIndex];
		if(value > midValue) {
			return binarySearch2(arr, midIndex+1, right, value);
		}else if(value < midValue) {
			return binarySearch2(arr, left, midIndex-1, value);
		}else {
			int temp = midIndex - 1;
			ArrayList<Integer> resultIndex = new ArrayList<Integer>();
			//左边
			while(true) {
				if(temp < 0 || arr[temp] != midValue) {
					break;
				}
				resultIndex.add(temp);
				temp --;
			}
			//右边
			temp = midIndex + 1;
			while(true) {
				if(temp > arr.length - 1 || arr[temp] != midValue) {
					break;
				}
				resultIndex.add(temp);
				temp ++;
			}
			resultIndex.add(midIndex);
			return resultIndex;
		}
	}
}
