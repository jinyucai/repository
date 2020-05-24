package com.cai.stack;

public class Calculate {
	
	public static void main(String[] args) {
		ArrayStack2 number = new ArrayStack2(10); //数字栈
		ArrayStack2 ope = new ArrayStack2(10); //操作符栈
		int index = 0;
		int num1 = 0;
		int num2 = 0;
		int result = 0;
		String expression = "2+6*13-3*2-19+2+7";
		while(true) {
			if(index == expression.length()) {
				break;
			}
			char charAt = expression.substring(index, index+1).charAt(0);
			if(!isOpe(charAt)) {
				int num = getNumber(expression, index); //得到数字
				index += String.valueOf(num).length();
				number.push(num);//存入栈
			}else {
				//是操作符
				if(ope.isEmpty()) {
					ope.push(charAt);
				}else {
					char top = (char) ope.getTopValue();
					if(priority(charAt) < priority(top)) {
						char pop = (char) ope.pop();
						//老师算法改动一下，不然出错
						num1 = number.pop();
						num2 = number.pop();
						//计算
						result = operate(num1, num2, pop);
						//入栈
						number.push(result);
						ope.push(charAt);
					}else {
						ope.push(charAt);
					}
				}
				index++;
			}
		}
		
		//老师的逻辑有问题，我这里反过来计算
		ArrayStack2 number2 = new ArrayStack2(10); //数字栈
		ArrayStack2 ope2 = new ArrayStack2(10); //操作符栈
		
		while(true) {
			if(number.isEmpty()) {
				break;
			}
			number2.push(number.pop());
		}
		while(true) {
			if(ope.isEmpty()) {
				break;
			}
			ope2.push(ope.pop());
		}
		
		while(true) {
			//最后再做计算
			if(number2.getTop() == 0) {
				//最后的结果
				System.out.printf("最后的计算结果是%d", number2.pop());
				break;
			}else {
				char pop = (char) ope2.pop();
				num2 = number2.pop();
				num1 = number2.pop();
				result = operate(num1, num2, pop);
				number2.push(result);
			}
		}
	}
	
	/**
	 * 判断是不是操作符
	 * @return
	 */
	public static boolean isOpe(char ch) {
		if(ch == '+' || ch == '-' || ch == '*' || ch == '/') {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 判断优先级
	 * @param ch
	 * @return
	 */
	public static int priority(char ch) {
		if(ch == '*' || ch == '/') {
			return 1;
		}else if(ch == '+' || ch == '-') {
			return 0;
		}else {
			return -1;
		}
	}
	
	/**
	 * 操作
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static int operate(int num1, int num2, char ch) {
		int result = 0;
		switch (ch) {
		case '+':
			result = num2 + num1;
			break;
		case '-':
			result = num2 - num1;
			break;
		case '*':
			result = num2 * num1;
			break;
		case '/':
			result = num2/num1;
			break;
		default:
			break;
		}
		return result;
	}
	
	/**
	 * 循环得到一个数字，可能是多位的
	 * @param exp
	 * @param index
	 * @return
	 */
	public static int getNumber(String exp, int index) {
		String num = "";
		while(true) {
			char ch = exp.substring(index, index+1).charAt(0);
			if(isOpe(ch)) { 
				break;
			}
			if(index == exp.length()-1) {
				if(!isOpe(ch)) {
					num += ch;
				}
				break;
			}
			num += ch;
			index++;
		}
		int parseInt = Integer.parseInt(num);
		return parseInt;
	}
}


class ArrayStack2 {
	
	private int maxSize;//栈的大小
	
	private int[] stack;
	
	private int top = -1; //栈顶 默认-1

	public ArrayStack2(int maxSize) {
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
	
	//出栈
	public int getTopValue() {
		if(isEmpty()) {
			throw new RuntimeException("栈空");
		}
		return this.stack[this.top];
	}
	
	public void show() {
		if(isEmpty()) {
			throw new RuntimeException("栈空");
		}
		for (int i = top; i > -1; i--) {
			System.out.printf("第%d个=%d\n", i, this.stack[i]);
		}
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}
	
	
	
}