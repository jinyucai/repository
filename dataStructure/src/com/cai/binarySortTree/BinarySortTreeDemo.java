package com.cai.binarySortTree;

import java.util.HashMap;
import java.util.Map;

/**
 * 二叉树查找排序
 * @author Administrator
 *
 */
public class BinarySortTreeDemo {
	public static void main(String[] args) {
		int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
		BinarySortTree tree = new BinarySortTree();
		for (int i = 0; i < arr.length; i++) {
			Node node = new Node(arr[i]);
			tree.add(node);
		}
		tree.inFixOrder();
//		Map<String, Node> map = tree.deleteSearch(17);
//		System.out.println("===");
		tree.deleteNode(2);
		tree.deleteNode(5);
		tree.deleteNode(9);
		tree.deleteNode(12);
		tree.deleteNode(7);
		tree.deleteNode(3);
		tree.deleteNode(10);
		tree.deleteNode(1);
		System.out.println("===");
		tree.inFixOrder();
	}
}

class BinarySortTree {
	private Node root;
	private Node temp = root;
	
	//添加一个节点
	public void add(Node node) {
		if(root == null) {
			root = node;
			temp = root;
			return;
		}else {
			if(temp == null) {
				return;
			}
			if(node.value < temp.value) {
				if(temp.left == null) {
					temp.left = node;
					temp = root; //temp回复给下次使用
					return;
				}else {
					temp = temp.left;//继续向下遍历
					add(node);
				}
			}else if(node.value >= temp.value) {
				if(temp.right == null) {
					temp.right = node;
					temp = root;
					return;
				}else {
					temp = temp.right;
					add(node);
				}
			}
		}
	}
	
	//重载
	public void inFixOrder() {
		this.inFixOrder(root);
	}
	
	//中序遍历
	public void inFixOrder(Node node) {
		if(root == null) {
			return;
		}
		if(node.left != null) {
			inFixOrder(node.left);
		}
		System.out.println(node);
		if(node.right != null) {
			inFixOrder(node.right);
		}
	}
	
	//重载
	public Map<String, Node> deleteSearch(int value){
		return deleteSearch(value, root, null, new HashMap<String, Node>());
	}
	
	/**
	 * 查找需要删除的节点
	 * @param value
	 * @param node   传入的是root节点开始
	 * @param parentNode 要删除节点的父节点，传入null
	 * @param map  封装的查询结果，一个删除对象 ，一个删除的父对象
	 * @return
	 */
	public Map<String, Node> deleteSearch(int value, Node node, Node parentNode, Map<String, Node> map) {
		
		if(root == null) {
			return null;
		}
		if(node.value == value) {
			map.put("target", node);
			map.put("parentNode", parentNode);
			return map;
		}else {
			//向左
			if(node.left != null) {
				deleteSearch(value, node.left, node, map);
			}
			if(node.right != null) {
				deleteSearch(value, node.right, node, map);
				
			}
		}
		return map;
	}
	
	
	/**
	 * 删除以node为父节点的树下的最小值 | 如果node是node父节点的左节点就是向下找最大的，都可以
	 * @param node
	 * @return
	 */
	public int getMinRight(Node node) {
		Node temp = node;
		if(temp.left != null) {
			temp = temp.left;
		}
		deleteNode(temp.value);
		return temp.value;
	}
	
	//从左子树找最大的
	public int getMaxLeft(Node node) {
		Node temp = node;
		if(temp.right != null) {
			temp = temp.right;
		}
		deleteNode(temp.value);
		return temp.value;
	}
	
	
	/**
	 * 删除节点
	 * @param value
	 */
	public void deleteNode(int value) {
		if(root == null) {
			return;
		}
		Map<String, Node> map = deleteSearch(value);
		Node target = map.get("target");
		Node parentNode = map.get("parentNode");
		if(target == null) {
			return;
		}
		if(root.left == null && root.right == null) {
			root = null;
			return;
		}
		if(target.left == null && target.right == null) { //删除叶子节点
			if(parentNode.left != null && parentNode.left.value == value) {
				parentNode.left = null;
			}else if(parentNode.right != null && parentNode.right.value == value) {
				parentNode.right = null;
			}
		}else if(target.left != null && target.right != null) {
//			int rightMin = getMinRight(target.right);//也可以leftMax
			int maxLeft = getMaxLeft(target.left);
			target.value = maxLeft;
		}else { //删除只有一颗子节点的节点
			if(parentNode == null) {
				root = target.left == null ? target.right : target.left;
			}else {
				if(parentNode.left != null && parentNode.left == target) {
					if(target.left != null) {
						parentNode.left = target.left;
					}else {
						parentNode.left = target.right;
					}
				}else if(parentNode.right != null && parentNode.right == target) {
					if(target.right != null) {
						parentNode.right = target.right;
					}else {
						parentNode.right = target.left;
					}
				}
			}
		}
	}
}

class Node {
	int value;
	Node left;
	Node right;
	
	public Node(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}
	
}