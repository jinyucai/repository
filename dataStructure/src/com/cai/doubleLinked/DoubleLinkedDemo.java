package com.cai.doubleLinked;

/**
 * 双向链表
 * @author Administrator
 *
 */
public class DoubleLinkedDemo {
	public static void main(String[] args) {
		HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
		HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
		HeroNode hero3 = new HeroNode(3, "林冲", "豹子头");
		HeroNode hero4 = new HeroNode(4, "鲁智深", "花和尚");
		DoubleLinked doubleLinked = new DoubleLinked();
		doubleLinked.addByOrder(hero2);
		doubleLinked.addByOrder(hero1);
		doubleLinked.addByOrder(hero4);
		doubleLinked.addByOrder(hero3);
		doubleLinked.list();
		doubleLinked.delete(4);
		System.out.println("=========================");
		doubleLinked.list();
	}
}

/**
 * 双向链表
 * @author Administrator
 *
 */
class DoubleLinked {
	//初始化一个初始节点
	private HeroNode headNode = new HeroNode(0, "", "");
	
	//添加节点
	public void addNode(HeroNode heroNode) {
		//找到最后一个节点
		HeroNode temp = headNode;
		while(true) {
			if(temp.getNext() == null) {
				//最后一个节点
				temp.setNext(heroNode);
				heroNode.setPre(temp);
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
			if(temp.getNext() == null) {
				//最后一个节点
				heroNode.setPre(temp);
				temp.setNext(heroNode);
			}else {
				heroNode.setNext(temp.getNext());//把heroNode的下个节点变成temp的下个节点
				temp.getNext().setPre(heroNode);
				temp.setNext(heroNode); //temp的下个节点变成heroNode
				heroNode.setPre(temp);
			}
		}
	}
	
	//更新节点内容
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
	
	//删除节点
	public void delete(int no) {
		if(headNode.getNext() == null) {
			System.out.println("链表为空");
			return;
		}
		HeroNode temp = this.headNode.getNext();
		boolean flag = false;
		while(true) {
			if(temp == null) {
				break;
			}
			if(temp.getNo() == no) {
				flag = true;
				break;
			}
			temp = temp.getNext();
		}
		if(flag) {
			if(temp.getNext() == null) {
				//最后一个节点
				temp.getPre().setNext(null);
			}else {
				temp.getPre().setNext(temp.getNext());
				temp.getNext().setPre(temp.getPre());
			}
		}else {
			System.out.println("没有找到要删除的节点");
		}
	}
	
	//展示节点
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
	
	private HeroNode pre; // 上一个节点
	
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

	public HeroNode getPre() {
		return pre;
	}

	public void setPre(HeroNode pre) {
		this.pre = pre;
	}

	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + ", nickName=" + nickName + "]";
	}
	
}
	
