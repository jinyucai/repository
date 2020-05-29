package com.cai.search;

import java.util.Arrays;

/**
 * 斐波那契查找
 * @author Administrator
 *
 */
public class FibonacciSearch {
	
	private static final int maxSize = 20;
	
	public static void main(String[] args) {
		int[] arr = {1, 5, 8, 23,44, 46, 47, 55, 109};
		int index = fibSearch(arr, 46);
		System.out.println(index);
	}
	
	//生成一个菲波那切数列
	public static int[] fib() {
		int[] f = new int[maxSize];
		f[0] = 1;
		f[1] = 1;
		for (int i = 2; i < maxSize; i++) {
			f[i] = f[i-1] + f[i-2];
		}
		return f;
	}
	
	
	public static int fibSearch(int[] a, int value) {
		//得到菲波那切数列
		int[] f = fib();
		int k = 0;
		int low = 0;
		int high = a.length - 1;
		int mid = 0;
		//high表示a的长度，f(k)表示斐波那契的k索引所在的值必须要大于等于a的长度才退出
		while(high + 1 > f[k]) {
			k++;
		}
		int[] temp = Arrays.copyOf(a, f[k]); //新数组
		for (int i = high+1; i < temp.length; i++) {
			temp[i] = a[high];
		}
		
		while(low <= high) {
			mid = low + f[k-1]-1;
			if(value < temp[mid]) {
				high = mid - 1;
				k--;
			}else if(value > temp[mid]) {   //1  1  2  3  5  8  13  21  34
				low = mid + 1;
				k -= 2;
			}else {
				if(mid <= high) {
					return mid; 
				}else{
					return high;
				}
			}
		}
		return -1;
	}
}
