package com.cai.stack;

import java.util.Scanner;

/**
 * 数组实现一个栈
 * @author Administrator
 *
 */
public class ArrayStackDemo {
	public static void main(String[] args) {
		ArrayStack stack = new ArrayStack(4);
		String key = "";
		boolean loop = true;
		Scanner sc = new Scanner(System.in);
		while(loop) {
			System.out.println("e退出");
			System.out.println("s展示");
			System.out.println("a入栈");
			System.out.println("p出战");
			key = sc.next();
			switch (key) {
			case "e":
				loop = false;
				break;
			case "s":
				try {
					stack.show();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case "a":
				try {
					System.out.println("输入一个数");
					int nextInt = sc.nextInt();
					stack.push(nextInt);
					System.out.println("添加了一个");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case "p":
				try {
					int pop = stack.pop();
					System.out.printf("出栈%d\n", pop);
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


class ArrayStack {
	
	private int maxSize;//栈的大小
	
	private int[] stack;
	
	private int top = -1; //栈顶 默认-1

	public ArrayStack(int maxSize) {
		this.maxSize = maxSize;
		this.stack = new int[maxSize]; //初始化栈
	}
	
	public boolean isEmpty() {
		return this.top == -1;
	}
	
	public boolean isFull() {
		return this.top == this.maxSize-1;
	}
	//压栈
	public void push(int value) {
		if(isFull()) {
			throw new RuntimeException("栈满");
		}
		top++;
		this.stack[top] = value;
	}
	//出栈
	public int pop() {
		if(isEmpty()) {
			throw new RuntimeException("栈空");
		}
		return this.stack[this.top--];
	}
	
	public void show() {
		if(isEmpty()) {
			throw new RuntimeException("栈空");
		}
		for (int i = top; i > -1; i--) {
			System.out.printf("第%d个=%d\n", i, this.stack[i]);
		}
	}
	
	
	
}