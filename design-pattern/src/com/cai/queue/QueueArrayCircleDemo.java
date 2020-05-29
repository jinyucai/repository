package com.cai.queue;

import java.util.Scanner;

/**
 * 环形数组
 * @author Administrator
 *
 */
public class QueueArrayCircleDemo {
	public static void main(String[] args) {
		QueueArrayCircle array = new QueueArrayCircle(4);
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
				try {
					array.showQueue();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
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

class QueueArrayCircle {
	
	private int maxSize; //最大容量
	private int rear; //尾部
	private int front; //头部
	private int[] arr; // 数组模拟队列
	
	public QueueArrayCircle(int maxSize) {
		this.maxSize = maxSize;
		this.front = 0;
		this.rear = 0;
		this.arr = new int[maxSize];
	}
	
	//判断是否为空
	public boolean isEmpty() {
		return this.front == this.rear;
	}
	
	//判断是否满
	public boolean isFull() {
		return (this.rear+1)%this.maxSize == this.front;
	}
	
	//取数据
	public int getData() {
		if(isEmpty()) {
			throw new RuntimeException("队列为空，没有数据");
		}
		int data = arr[this.front];
		this.front = (this.front + 1)%this.maxSize;
		return data;
	}
	
	//存数据
	public void addData(int number) {
		if(isFull()) {
			throw new RuntimeException("队列满了，不能存数据了");
		}
		arr[this.rear] = number;
		this.rear = (this.rear + 1)%this.maxSize;
	}
	
	//获取头数据
	public int getHead() {
		if(isEmpty()) {
			throw new RuntimeException("队列为空，没有数据");
		}
		return arr[this.front];
	}
	
	//展示数据
	public void showQueue() {
		if(isEmpty()) {
			throw new RuntimeException("队列为空，没有数据");
		}
		//this.rear + this.maxSize -this.front是当前元素个数
		for (int i = this.front; i < (this.rear + this.maxSize -this.front)%this.maxSize + this.front; i++) {
			System.out.printf("arr[%d]=%d\n", i%this.maxSize, arr[i%this.maxSize]);
		}
	}
	
}