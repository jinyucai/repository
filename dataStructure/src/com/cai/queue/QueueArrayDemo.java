package com.cai.queue;

import java.util.Scanner;

/**
 * 使用数组来实现队列
 * @author Administrator
 *
 */
public class QueueArrayDemo {
	public static void main(String[] args) {
		QueueArray array = new QueueArray(3);
		Scanner sc = new Scanner(System.in);
		String nextLine = "";
		boolean loop = true;
		while(loop) {
			System.out.println("e:  退出程序");
			System.out.println("s:  展示数据");
			System.out.println("a:  添加一个到队列");
			System.out.println("g:  获取数据");
			System.out.println("h:  展示头数据");
			nextLine = sc.next();
			switch (nextLine) {
			case "e":
				loop = false;
				break;
			case "s":
				array.showQueue();
				break;
			case "a":
				try {
					System.out.println("输入一个数");
					int nextInt = sc.nextInt();
					array.addData(nextInt);
					System.out.println("添加了一个数\n");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case "g":
				try {
					int data = array.getData();
					System.out.printf("取出的数据为%d\n", data);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case "h":
				try {
					int data = array.getHead();
					System.out.printf("头数据为%d\n", data);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			default:
				break;
			}
			
		}
		
	}
}

class QueueArray {
	
	private int maxSize; //最大容量
	private int rear; //尾部
	private int front; //头部
	private int[] arr; // 数组模拟队列
	
	public QueueArray(int maxSize) {
		this.maxSize = maxSize;
		//队列一开始头和尾都是-1
		this.front = -1;
		this.rear = -1;
		this.arr = new int[maxSize];
	}
	
	//判断是否为空
	public boolean isEmpty() {
		return this.front == this.rear;
	}
	
	//判断是否满
	public boolean isFull() {
		return this.rear == this.maxSize-1;
	}
	
	//取数据
	public int getData() {
		if(isEmpty()) {
			throw new RuntimeException("队列为空，没有数据");
		}
		this.front++;
		return arr[this.front];
	}
	
	//存数据
	public void addData(int number) {
		if(isFull()) {
			throw new RuntimeException("队列满了，不能存数据了");
		}
		this.rear++;
		arr[this.rear] = number;
	}
	
	//获取头数据
	public int getHead() {
		if(isEmpty()) {
			throw new RuntimeException("队列为空，没有数据");
		}
		return arr[this.front+1];
	}
	
	//展示数据
	public void showQueue() {
		for (int i = 0; i < arr.length; i++) {
			System.out.printf("arr[%d]=%d\n", i, arr[i]);
		}
	}
	
}