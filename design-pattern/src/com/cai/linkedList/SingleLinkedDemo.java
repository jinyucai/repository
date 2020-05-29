package com.cai.linkedList;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SingleLinkedDemo {
	public static void main(String[] args) {
		SingleLinked linked = new SingleLinked();
		SingleLinked linked2 = new SingleLinked();
		HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
		HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
		HeroNode hero3 = new HeroNode(3, "林冲", "豹子头");
		HeroNode hero4 = new HeroNode(4, "鲁智深", "花和尚");
		
		HeroNode hero5 = new HeroNode(6, "宋江2", "及时雨2");
		HeroNode hero6 = new HeroNode(7, "卢俊义2", "玉麒麟2");
		HeroNode hero7 = new HeroNode(5, "林冲2", "豹子头2");
		HeroNode hero8 = new HeroNode(8, "鲁智深2", "花和尚2");
		
		linked.addNode(hero2);
		linked.addNode(hero1);
		linked.addNode(hero3);
		linked.addNode(hero4);
		
		linked2.addNode(hero7);
		linked2.addNode(hero5);
		linked2.addNode(hero6);
		linked2.addNode(hero8);
		SingleLinked mergeTwoLink = mergeTwoLink(linked.getHeadNode(), linked2.getHeadNode());
		mergeTwoLink.list();
//		linked.addByOrder(hero1);
//		linked.addByOrder(hero4);
//		linked.addByOrder(hero2);
//		linked.addByOrder(hero3);
//		linked.list();
//		System.out.println("========");
//		linked.update(new HeroNode(2, "小卢子", "卢卢"));
//		linked.list();
//		System.out.println("========");
//		linked.delete(2);
//		linked.list();
//		int length = getLinkedLength(linked.getHeadNode());
//		System.out.println(length);
//		HeroNode node = getLastIndex(linked.getHeadNode(), 3);
//		System.out.println(node);
//		reverseLinked(linked.getHeadNode());
//		System.out.println("========反转后=======");
//		linked.list();
//		System.out.println("===========反向打印链表============");
//		reversePrint(linked.getHeadNode());
		
	}
	
	/**
	 * 获取链表个数
	 * @param head 头
	 * @return
	 */
	public static int getLinkedLength(HeroNode head) {
		if(head.getNext() == null) {
			return 0;
		}
		int count = 0;
		HeroNode temp = head;
		while(true) {
			if(temp.getNext() == null) {
				break;
			}else {
				count++;
			}
			temp = temp.getNext();
		}
		return count;
	}
	
	/**
	 * 获取倒数第index个
	 * @param head 头
	 * @param index
	 * @return
	 */
	public static HeroNode getLastIndex(HeroNode head, int index) {
		if(head.getNext() == null) {
			return null;
		}
		int length = getLinkedLength(head);
		if(index <= 0 || index > length) {
			return null;
		}
		HeroNode temp = head;
		for (int i = 0; i <= length - index; i++) {
			temp = temp.getNext();
		}
		return temp;
	}
	
	/**
	 * 链表翻转
	 * @param head  要翻转的链表的头
	 */
	public static void reverseLinked(HeroNode head) {
		if(head.getNext() == null || head.getNext().getNext() == null) {
			return;
		}
		//定义一个辅助指针
		HeroNode temp = head.getNext();
		//定义辅助指针的下一个节点，不然不知道下个节点是什么
		HeroNode next = null;
		HeroNode reverseHead = new HeroNode(0, "", "");
		while(temp != null) {
			next = temp.getNext();
			temp.setNext(reverseHead.getNext());//temp的下个节点指向revese的下个节点
			reverseHead.setNext(temp);//reverse的下个节点指向temp的下个节点
			temp = next;
		}
		head.setNext(reverseHead.getNext());
	}
	
	//反向打印链表
	public static void reversePrint(HeroNode head) {
		if(head.getNext() == null) {
			System.out.println("链表为空");
			return;
		}
		Stack<HeroNode> heroNode = new Stack<HeroNode>();//栈
		HeroNode temp = head;
		while(temp.getNext() != null) {
			//进栈
			heroNode.push(temp.getNext());
			temp = temp.getNext();
		}
		//打印栈
		while(heroNode.size() > 0) {
			HeroNode pop = heroNode.pop();
			System.out.println(pop);
		}
	}
	
	/**
	 * 合并两个单链表
	 * @param head1
	 * @param head2
	 */
	public static SingleLinked mergeTwoLink(HeroNode head1, HeroNode head2) {
		//定义一个空的头链表
		SingleLinked singleLinked = new SingleLinked();
		HeroNode temp1 = head1;
		while(true) {
			//先把原来两个随便结合
			if(temp1.getNext() == null) {
				if(head2.getNext() != null) {
					temp1.setNext(head2.getNext());
					break;
				}
				break;
			}
			temp1 = temp1.getNext();
		}
		HeroNode temp2 = head1;
		List<HeroNode> list = new ArrayList<HeroNode>();
		while(temp2.getNext() != null) {
			list.add(temp2.getNext());
			temp2 = temp2.getNext();
		}
		for (HeroNode heroNode : list) {
			singleLinked.addByOrder(heroNode);
		}
		return singleLinked;
	}
}

class SingleLinked {
	
	//初始化一个初始节点
	private HeroNode headNode = new HeroNode(0, "", "");
	
	public HeroNode getHeadNode() {
		return headNode;
	}

	public void setHeadNode(HeroNode headNode) {
		this.headNode = headNode;
	}

	public void addNode(HeroNode heroNode) {
		//找到最后一个节点
		HeroNode temp = headNode;
		while(true) {
			if(temp.getNext() == null) {
				//最后一个节点
				temp.setNext(heroNode);
				break;
			}
			temp = temp.getNext();
		}
	}
	
	/**
	 * 按照排名添加
	 * @param heroNode
	 */
	public void addByOrder(HeroNode heroNode) {
		HeroNode temp = headNode;
		boolean flag = false;
		while(true) {
			if(temp.getNext() == null) {
				break;
			}else if(temp.getNext().getNo() > heroNode.getNo()) {
				break;
			}else if(temp.getNext().getNo() == heroNode.getNo()) {
				flag = true;
				break;
			}else if(temp.getNo() == heroNode.getNo()) {
				
				flag = true;
				break;
			}
			//下个节点继续查找
			temp = temp.getNext();
		}
		if(flag) {
			System.out.printf("添加的节点编号%d 已经存在", heroNode.getNo());
		}else {
			heroNode.setNext(temp.getNext());//把heroNode的下个节点变成temp的下个节点
			temp.setNext(heroNode); //temp的下个节点变成heroNode
		}
	}
	
	public void update(HeroNode heroNode) {
		if(headNode.getNext() == null) {
			System.out.println("链表为空");
		}else {
			HeroNode temp = this.headNode.getNext();
			boolean flag = false;
			while(true) {
				if(temp == null) {
					break; //到头了
				}
				if(temp.getNo() == heroNode.getNo()) {
					flag = true;
					break;
				}
				temp = temp.getNext();
			}
			if(flag) {
				temp.setName(heroNode.getName());
				temp.setNickName(heroNode.getNickName());
			}else {
				System.out.println("没有找到编号为"+heroNode.getNo()+"的英雄");
			}
		}
	}
	
	public void delete(int no) {
		if(headNode.getNext() == null) {
			System.out.println("链表为空");
			return;
		}
		HeroNode temp = this.headNode;
		boolean flag = false;
		while(true) {
			if(temp.getNext() == null) {
				break;
			}
			if(temp.getNext().getNo() == no) {
				flag = true;
				break;
			}
			temp = temp.getNext();
		}
		if(flag) {
			temp.setNext(temp.getNext().getNext());
		}else {
			System.out.println("没有找到要删除的节点");
		}
	}
	
	public void list() {
		if(headNode.getNext() == null) {
			System.out.println("链表为空");
		}else {
			HeroNode next = headNode;
			while(true) {
				next = next.getNext();
				System.out.println(next);
				if(next.getNext() == null) {
					break;
				}
			}
		}
	}
	
}

class HeroNode {
	
	private int no;
	
	private String name;
	
	private String nickName;
	
	private HeroNode next; //下个节点

	public HeroNode(int no, String name, String nickName) {
		this.no = no;
		this.name = name;
		this.nickName = nickName;
	}
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public HeroNode getNext() {
		return next;
	}

	public void setNext(HeroNode next) {
		this.next = next;
	}



	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + ", nickName=" + nickName + "]";
	}
	
	
	
	
	
}