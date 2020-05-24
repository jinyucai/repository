package com.cai.circleLinked;
/**
 * 单向环形链表
 * @author Administrator
 *
 */
public class CircleLinkedDemo {
	public static void main(String[] args) {
		CircleLinked circleLinked = new CircleLinked();
		circleLinked.add(500);
		circleLinked.show();
		circleLinked.out(5,11,500);
	}
}

class CircleLinked {
	//创建一个初始节点
	private Boy first = null;
	/**
	 * 添加小孩
	 * @param nums 小孩个数
	 */
	public void add(int nums) {
		Boy cur = null; //辅助指针
		if(nums < 1) {
			//至少有一个
			System.out.println("至少需要一个");
			return;
		}
		for (int i = 1; i <= nums; i++) {
			Boy boy = new Boy(i);
			if(i == 1) {
				first = boy;
				first.setNext(first);
				cur = first;
			}else {
				cur.setNext(boy);
				boy.setNext(first);
				cur = boy;
			}
		}
	}
	
	/**
	 * 展示环形链表
	 */
	public void show() {
		if(first == null) {
			System.out.println("没有小孩");
			return;
		}
		Boy cur = first;
		while(true) {
			if(cur.getNext() == first) {
				System.out.println(cur);
				break;
			}
			System.out.println(cur);
			cur = cur.getNext();
		}
	}
	
	/**
	 * 
	 * @param start 第几个开始数
	 * @param count 数几下
	 * @param nums 个数
	 */
	public void out(int start, int count, int nums) {
		if(start < 1 || count > nums || start > nums) {
			System.out.println("输入有误");
			return;
		}
		//创建两个辅助指针 同时移动
		Boy handler = first;
		while(true) {
			if(handler.getNext() == first) {
				break;
			}
			handler = handler.getNext();
		}
		//确认从第几个开始数
		for (int i = 1; i < start; i++) {
			first = first.getNext();
			handler = handler.getNext();
		}
		
		while(true) {
			if(handler == first) {
				//只有一个节点了
				break;
			}
			for (int i = 1; i < count; i++) { // 第一下也数自己
				first = first.getNext();
				handler = handler.getNext();
			}
			first = first.getNext();//first往后移动一位
			handler.setNext(first);
		}
		System.out.println("最后幸运留下的是" + first);
	}
}

class Boy {
	private int no;
	
	private Boy next;
	
	public Boy(int no) {
		this.no = no;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public Boy getNext() {
		return next;
	}
	public void setNext(Boy next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "Boy [no=" + no + "号小孩" + "]";
	}

	
	
	
}