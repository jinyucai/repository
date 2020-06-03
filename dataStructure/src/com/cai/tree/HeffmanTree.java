package com.cai.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 赫夫曼树
 * @author Administrator
 *
 */
public class HeffmanTree {
	
	public static void main(String[] args) {
		int[] arr = {13, 7, 8, 3, 29, 6, 1};
		Node root = createHeffmanTree(arr);
		preOrder(root);
	}
	
	public static Node createHeffmanTree(int[] arr) {
		List<Node> nodes = new ArrayList<Node>();
		for (int value : arr) {
			nodes.add(new Node(value));
		}
		
		while (nodes.size() > 1) {
			Collections.sort(nodes);
			Node node1 = nodes.get(0);
			Node node2 = nodes.get(1);
			Node parent = new Node(node1.value + node2.value);
			parent.leftNode = node1;
			parent.rightNode = node2;
			//将处理过的子节点删除
			nodes.remove(node1);
			nodes.remove(node2);
			nodes.add(parent);
		}
		//最终返回赫夫曼树的根节点
		return nodes.get(0);
	}
		
	public static void preOrder(Node root) {
		if(root == null) {
			return;
		}
		System.out.println(root.value);
		if(root.leftNode != null) {
			preOrder(root.leftNode);
		}
		if(root.rightNode != null) {
			preOrder(root.rightNode);
		}
	}
	
}

class Node implements Comparable<Node> {
	int value;
	Node leftNode;
	Node rightNode;
	
	public Node(int value) {
		this.value = value;
	}
	
	//排序方法
	@Override
	public int compareTo(Node o) {
		//从小到大排序
		return this.value - o.value;
	}
	
	
}