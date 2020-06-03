package com.cai.tree;

import java.util.Arrays;

/**
 * 堆排序
 * @author Administrator
 *
 */
public class HeapSort {
	public static void main(String[] args) {
//		int[] arr = {2, 3, 1, 5, 12, 4, 7, 6};
//		heapSort(arr);
//		System.out.println(Arrays.toString(arr));
		int[] arr = new int[8000000]; //8万个测试时间复杂度
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 8000000);
		}
		long time1 = System.currentTimeMillis(); //纳秒
		heapSort(arr);
		long time2 = System.currentTimeMillis();
		System.out.println("排序耗时" + ((time2-time1)) + "毫秒");
	}
	
	
	
	public static void heapSort(int[] arr) {
		int temp = 0;
		//arr.length/2-1 第一个非叶子结点的索引，从左到右，从上到下
		for (int i = arr.length/2-1; i >= 0; i--) {
			adjustHeap(arr, i, arr.length);
		}
		
		for (int i = arr.length-1; i > 0; i--) {
			//堆顶元素和堆末元素交换位置
			temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			adjustHeap(arr, 0, i);
		}
	}
	
	/**
	 * 
	 * @param arr 要转成大顶堆的数组
	 * @param i 非叶子节点的对于数组中的索引
	 * @param length 要转成大顶堆的数组长度，逐渐变小的
	 */
	public static void adjustHeap(int[] arr, int i, int length) {
		int temp = arr[i];
		//i*2-1 对于左节点的索引
		for (int j = i*2+1; j < length; j=j*2+1) {
			if(j+1 < length && arr[j] < arr[j+1]) { //如果左节点小于右节点
				j++;
			}
			if(temp < arr[j]) {
				arr[i] = arr[j];
				i = j; //冲左右节点中大的那个做为父节点又开始排
			}else {
				break;
			}
		}
		arr[i] = temp;//此时的i是最末端的
	}
	
}
