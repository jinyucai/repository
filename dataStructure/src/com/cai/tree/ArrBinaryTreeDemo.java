package com.cai.tree;

/**
 * 顺序存储二叉树
 * 数组的下标n ==> 2*n+1 是对应顺序树中的左节点元素值
 * 数组的下标n ==> 2*n+2 是对应顺序树中的右节点元素值
 * 数组的下标n ==> (n-1)/2 是对应顺序树中的父节点的值
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
//        arrBinaryTree.preOrder();
//        arrBinaryTree.inFixOrder();
        arrBinaryTree.postOrder();
    }
}

class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载前序遍历
    public void preOrder(){
        this.preOrder(0);
    }

    //前序遍历
    public void preOrder(int index){
        if(arr == null || arr.length == 0){
            System.out.println("数组为空");
            return;
        }
        System.out.println(arr[index]);
        int n = 2 * index + 1;
        if(n < arr.length){
            //向左递归
            preOrder(n);
        }
        //向右
        n = 2 * index + 2;
        if(n < arr.length){
            preOrder(n);
        }
    }

    //重载中序遍历
    public void inFixOrder(){
        this.inFixOrder(0);
    }

    //中序遍历
    public void inFixOrder(int index){
        if(arr == null || arr.length == 0){
            System.out.println("数组为空");
            return;
        }
        int n = 2 * index + 1;
        if(n < arr.length){
            inFixOrder(n);
        }
        //输出当前
        System.out.println(arr[index]);
        n = 2 * index + 2;
        if(n < arr.length){
            inFixOrder(n);
        }
    }

    //重载后序
    public void postOrder(){
        this.postOrder(0);
    }

    //后序遍历
    public void postOrder(int index){
        if(arr == null || arr.length == 0){
            System.out.println("数组为空");
            return;
        }
        int n = 2 * index + 1;
        if(n < arr.length){
            postOrder(n);
        }
        n = 2* index + 2;
        if(n < arr.length){
            postOrder(n);
        }
        System.out.println(arr[index]);
    }
}