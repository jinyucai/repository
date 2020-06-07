package com.cai.avltree;

import java.util.HashMap;
import java.util.Map;

public class AVLTreeDemo {
	public static void main(String[] args) {
//		int[] arr = {4, 3, 6, 5, 7, 8};
//		int[] arr = {10, 12, 8, 9, 7, 6};
		int[] arr = {10, 11, 7, 6, 8, 9};
		AVLTree avlTree = new AVLTree();
		for (int i = 0; i < arr.length; i++) {
			avlTree.add(new Node(arr[i]));
		}
		System.out.println("当前树的高度是：" + avlTree.getRoot().height());
		System.out.println("当前树的左子树高度是：" + avlTree.getRoot().left.height());
		System.out.println("当前树的右子树高度是：" + avlTree.getRoot().right.height());
	}
}


class AVLTree {
	private Node root;
	private Node temp = root;
	
	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

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
		//添加完看看符不符合平衡二叉树
		if(root.rightHeight() - root.leftHeight() > 1) {
			//左旋的时候如果当前节点的右子树高度大于左子树高度
			if(root.right != null && root.right.leftHeight() > root.right.rightHeight()) {
				//子树进行右旋
				root.right.rightRotate();
				//在进行左旋
				root.leftRotate();
			}else {
				//需要左旋
				root.leftRotate();
			}
			return;//保险一点可以不写return
		}
		
		if(root.leftHeight() - root.rightHeight() > 1) {
			if(root.left != null && root.left.rightHeight() > root.left.leftHeight()) {
				//子树左旋
				root.left.leftRotate();
				root.rightRotate();
			}else {
				root.rightRotate();
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
	
	//返回当前根节点树的高度
	public int height() {
		return Math.max(left == null?0:left.height(), right == null?0:right.height()) + 1;
	}
	
	//返回根节点左子树的高度
	public int leftHeight() {
		if(left != null) {
			return left.height();
		}else {
			return 0;
		}
	}
	
	//返回根节点的右子树的高度
	public int rightHeight() {
		if(right != null) {
			return right.height();
		}else {
			return 0;
		}
	}
	
	/**
	 * 进行左旋
	 */
	public void leftRotate() {
		//创建新节点，以当前节点为值
		Node newNode = new Node(value);
		//把新节点的左子树设置成当前节点的左子树
		newNode.left = left;
		//把新节点的右子树设置成当前节点右子树的左子树
		newNode.right = right.left;
		//把当前节点的值设置成当前节点的右子树的值
		newNode.value = right.value;
		//把当前节点的右子树设置成当前节点右子树的右子树
		right = right.right;
		//把当前节点的左子树设置成新的节点
		left = newNode;
	}
	
	//右旋
	public void rightRotate() {
		//创建新节点以当前节点为值
		Node newNode = new Node(value);
		//新节点的右子节点设置成当前节点的右子节点
		newNode.right = right;
		//新节点的值设置成新节点右子节点的右子节点的值
		newNode.left = left.right;
		//当前节点的值设置成当前左子节点的值
		value = left.value;
		//当前节点的左子节点设置成当前节点的左子节点的左子节点
		left = left.left;
		//当前节点的右子节点指向新节点
		right = newNode;
	}
	
	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}
	
}