package com.cai.hashtable;

import java.util.Scanner;

public class HashTableDemo {
	public static void main(String[] args) {
		HashTable hashTable = new HashTable(7);
		//simple test
		String key = "";
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("a  添加");
			System.out.println("l  显示");
			System.out.println("f  查找");
			System.out.println("d  删除");
			System.out.println("e  退出");
			key = sc.next();
			switch (key) {
			case "a":
				System.out.println("输入id");
				int id = sc.nextInt();
				System.out.println("输入名字");
				String name = sc.next();
				Emp emp = new Emp(id, name);
				hashTable.add(emp);
				break;
			case "l":
				hashTable.list();
				break;
			case "d":
				System.out.println("输入要删除的id");
				id = sc.nextInt();
				hashTable.delete(id);
				break;
			case "f":
				System.out.println("输入要查找的id");
				id = sc.nextInt();
				Emp emp2 = hashTable.find_by_id(id);
				if(emp2 == null) {
					System.out.println("没有找到");
				}
				break;
			case "e":
				sc.close();
				System.exit(0);
			default:
				break;
			}
		}
	}
}


//创建hahtable
class HashTable {
	private HashLinkedList[] empArray;  //存放链表有多少长度就有几个链表
	private int size;
	//size  数组长度
	public HashTable(int size) {
		this.size = size;
		empArray = new HashLinkedList[size];
		//分别初始化每个链表
		for (int i = 0; i < size; i++) {
			empArray[i] = new HashLinkedList();
		}
	}
	
	//添加
	public void add(Emp emp) {
		int hashId = hashId(emp.getId());
		empArray[hashId].add(emp);
	}
	
	//查找
	public Emp find_by_id(int id) {
		int hashId = hashId(id);
		Emp emp = empArray[hashId].findById(id);
		if(emp != null) {
			System.out.println("在第" + hashId+1 + "条链表找到了" + emp.toString());
		}
		return emp;
	}
	
	//删除
	public void delete(int id) {
		int hashId = hashId(id);
		int delete = empArray[hashId].delete(id);
		if(delete == 1) {
			System.out.printf("从第%d 个链表删除了一个元素\n", hashId+1);
		}
	}
	
	public void list() {
		for (int i = 0; i < size; i++) {
			empArray[i].list(i);
		}
	}
	
	//创建一个散列id方法根据散列id把传入的emp放入哪个索引的链表
	public int hashId(int id) {
		return id % size;
	}
	
}


//链表的节点每个节点中有一个头元素
class HashLinkedList {
	
	private Emp head;
	
	//添加
	public void add(Emp emp) {
		if(head == null) {
			head = emp;
			return;
		}
		Emp temp = head;
		while(temp.getNext() != null) {
			temp = temp.getNext();
		}
		temp.setNext(emp);
	}
	
	//查找
	public Emp findById(int id) {
		if(head == null) {
			System.out.println("没有找到");
			return null;
		}
		Emp temp = head;
		while(true) {
			if(temp.getId() == id) {
				break;
			}
			if(temp.getNext() == null) {
				temp = null;
				break;
			}
			temp = temp.getNext();
		}
		return temp;
	}
	
	
	public int delete(int id) {
		if(head == null) {
			System.out.println("没有员工");
			return 0;
		}
		Emp temp = head;
		Emp last = null;
		while(true) {
			if(temp.getId() == id) {
				break;
			}
			if(temp.getNext() == null) {
				temp = null;
				break;
			}
			last = temp;
			temp = temp.getNext();
		}
		if(last == null) {
			temp = null; //只有一个元素
		}else {
			last.setNext(temp.getNext());
		}
		return 1;
	}
	
	//展示
	public void list(int no) {
		if(head == null) {
			System.out.println("第" + (no+1) + "条链表为空");
			return;
		}
		System.out.print("第" + (no+1) + "条链表为");
		Emp temp = head;
		while(temp != null) {
			System.out.printf("==>id为%d个员工名称是 %s", temp.getId(), temp.getName());
			temp = temp.getNext();
		}
		System.out.println();
	}
	
}

//创建员工类
class Emp {
	private int id;
	
	private String name;
	
	private Emp next;

	public Emp(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Emp getNext() {
		return next;
	}

	public void setNext(Emp next) {
		this.next = next;
	}
	
	
}
