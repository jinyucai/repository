package com.cai.stack;
/**
 * 单链表栈
 * @author Administrator
 *
 */
public class LinkedStackDemo {
	
	public static void main(String[] args) {
		LinkedStack linkedStack = new LinkedStack(4);
		StackValue stackValue1 = new StackValue(1, 10);
		StackValue stackValue2 = new StackValue(2, 20);
		StackValue stackValue3 = new StackValue(3, 30);
		StackValue stackValue4 = new StackValue(4, 40);
		linkedStack.push(stackValue1);
		linkedStack.push(stackValue2);
		linkedStack.push(stackValue3);
		linkedStack.push(stackValue4);
		linkedStack.list();
		int pop1 = linkedStack.pop();
		int pop2 = linkedStack.pop();
		int pop3 = linkedStack.pop();
		int pop4 = linkedStack.pop();
		System.out.printf("\n%d\t%d\t%d\t%d\t", pop1, pop2, pop3, pop4);
		linkedStack.pop();
	}
}



class LinkedStack {
	private StackValue stackValue = new StackValue(-1, 0);
	
	private int maxSize;

	public LinkedStack(int maxSize) {
		this.maxSize = maxSize;
	}

	public boolean isFull() {
		//遍历stackValue
		StackValue temp = stackValue;
		while(true) {
			if(temp.getNext() == null) {
				break;
			}
			temp = temp.getNext();
		}
		return temp.getNumber() == maxSize;
	}
	
	public boolean isEmpty() {
		return stackValue.getNext() == null;
	}
	
	//入栈，这里做个简单的就行了 序号必须从1一次递增
	public void push(StackValue stack) {
		if(isFull()) {
			System.out.println("栈满");
			return;
		}
		StackValue temp = stackValue;
		while(true) {
			if(temp.getNext() == null) {
				temp.setNext(stack);
				break;
			}
			temp = temp.getNext();
		}
	}
	
	public int pop() {
		if(isEmpty()) {
			System.out.println("栈空");
			return 0;
		}
		StackValue temp = stackValue;
		StackValue tempFront = stackValue;
		int value = 0;
		while(true) {
			if(temp.getNext() == null) {
				value = temp.getValue();
				tempFront.setNext(null); //去除最后一个
				break;
			}
			tempFront = temp;
			temp = temp.getNext();
		}
		return value;
	}
	
	public void list() {
		if(isEmpty()) {
			System.out.println("栈空");
			return;
		}
		StackValue temp = stackValue;
		int count = 0;//栈个数
		while(true) {
			if(temp.getNext() == null) {
				break;
			}
			count++;
			temp = temp.getNext();
		}
		for (int i = count; i > -1; i--) {
			StackValue cur = stackValue;
			for (int j = 0; j < i; j++) {
				cur = cur.getNext();
			}
			System.out.printf("第%d个数是：%d\n", i, cur.getValue());
		}
	}
}


/**
 * 栈节点
 * @author Administrator
 *
 */
class StackValue {
	private int number; //栈节点的序号
	
	private int value;
	
	private StackValue next;

	public StackValue(int number, int value) {
		this.number = number;
		this.value = value;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public StackValue getNext() {
		return next;
	}

	public void setNext(StackValue next) {
		this.next = next;
	}
	
	
}