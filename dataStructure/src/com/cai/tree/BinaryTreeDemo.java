package com.cai.tree;

/**
 * 二叉树小demo
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先需要创建一个节点
        HeroNode2 node1 = new HeroNode2(1, "宋江");
        HeroNode2 node2 = new HeroNode2(2, "卢俊义");
        HeroNode2 node3 = new HeroNode2(3, "吴用");
        HeroNode2 node4 = new HeroNode2(4, "林冲");
        HeroNode2 node5 = new HeroNode2(5, "关胜");
        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        ThreadedBinaryTree binaryTree = new ThreadedBinaryTree(node1);
//        binaryTree.preOrder();
//        binaryTree.inFixOrder();
//        binaryTree.postOrder();
//        System.out.println("前序遍历查找~~~~~~");
//        HeroNode heroNode = binaryTree.preOrderFind(15);
//        if(heroNode != null){
//            System.out.println(heroNode);
//        }else{
//            System.out.println("没有找到");
//        }
//        System.out.println("中序遍历查找~~~~~~");
//        HeroNode heroNode = binaryTree.inFixOrderFind(5);
//        if(heroNode != null){
//            System.out.println(heroNode);
//        }else{
//            System.out.println("没有找到");
//        }
//        System.out.println("后序遍历查找~~~~~~");
//        HeroNode heroNode = binaryTree.postOrderFind(5);
//        if(heroNode != null){
//            System.out.println(heroNode);
//        }else{
//            System.out.println("没有找到");
//        }
        System.out.println("前序遍历");
        binaryTree.preOrder();
        binaryTree.delete(5);
        System.out.println("前序遍历");
        binaryTree.preOrder();
    }
}


class BinaryTree {
    private HeroNode2 root;// 根节点

    public BinaryTree(HeroNode2 root) {
        this.root = root;
    }

    //前序
    public void preOrder(){
        if(root != null){
            root.preOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }

    //中序
    public void inFixOrder(){
        if(root != null){
            root.inFixOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }

    //后序
    public void postOrder(){
        if(root != null){
            root.postOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }

    //前序查找
    public HeroNode2 preOrderFind(int id){
        if(root != null){
            return root.preOrderFind(id);
        }else{
            return null;
        }
    }

    //中序查找
    public HeroNode2 inFixOrderFind(int id){
        if(root != null){
            return root.inFixOrderFind(id);
        }else{
            return null;
        }
    }

    //后序查找
    public HeroNode2 postOrderFind(int id){
       if(root != null){
           return root.postOrderFind(id);
       }else{
           return null;
       }
    }

    //二叉树删除
    public void delete(int id){
        if(root != null){
            if(root.getId() == id){
                root = null; // 如果root节点是要删除的就将整棵树置空
            }else{
                root.delete(id);
            }
        }else{
            System.out.println("树为空");
            return;
        }
    }
}


//二叉树节点
class HeroNode {
    private Integer id;
    private String name;
    private HeroNode left; //左子节点默认为null
    private HeroNode right;

    public HeroNode(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }

    //中序遍历
    public void inFixOrder(){
        if(this.left != null){
            this.left.inFixOrder();
        }
        System.out.println(this);
        if(this.right != null){
            this.right.inFixOrder();
        }
    }

    //后序遍历
    public void postOrder(){
        if(this.left != null){
            this.left.postOrder();
        }
        if(this.right != null){
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //前序查找
    public HeroNode preOrderFind(int id){
        //先判断当前节点是不是要找的
        if(this.id == id){
            return this;
        }
        //进行向左查找
        HeroNode resNode = null;
        if(this.left != null){
            //如果这里没找到，则开始回溯退出递归,这时resNode仍然为空
            resNode = this.left.preOrderFind(id);
        }
        //如果左边递归最终找到了，则回溯过程在函数一开始的if中会直接返回这个resNode，因为找到了
        if(resNode != null){
            return resNode;
        }
        //如果仍然为空，那么向右查找
        if(this.right != null){
            resNode = this.right.preOrderFind(id);
        }
        //这一步整个树都遍历完成，不管有没有找到，都返回，这里如果没找到，就是返回null
        return resNode;
    }

    //中序查找
    public HeroNode inFixOrderFind(int id){
        HeroNode resNode = null;
        //先进行向左查找
        if(this.left != null){
            resNode = this.left.inFixOrderFind(id);
        }
        //如果resNode不为空，就是上面返回的this
        if(resNode != null){
            return resNode;
        }
        //左边找完，回溯过程中判断没有left的那个节点是不是要找的
        if(this.id == id){
            return this;
        }
        //否则向右查找
        if(this.right != null){
            resNode = this.right.inFixOrderFind(id);
        }
        //不管找没找到都返回
        return resNode;
    }

    //后序查找
    public HeroNode postOrderFind(int id){
        HeroNode resNode = null;
        //先向左查找
        if(this.left != null){
            resNode = this.left.postOrderFind(id);
        }
        //如果找到就返回
        if(resNode != null){
            return resNode;
        }
        //否则向右查找
        if(this.right != null){
            resNode = this.right.postOrderFind(id);
        }
        //如果不为空也返回
        if(resNode != null){
            return resNode;
        }
        //最后如果是叶子节点判断当前节点是不是要找的
        if(this.id == id) {
            return this;
        }
        //否则就算没找到也要返回null
        return null;
    }

    /**
     * 遍历删除节点
     * 如果节点下有子节点就删除整个子树
     * @param id
     */
    public void delete(int id){
        if(this.left != null && this.left.id == id){
            //删除子树或子节点
            this.left = null;
            return;
        }
        //如果左边没有就往右看看
        if(this.right != null && this.right.id == id){
            this.right = null;
            return;
        }

        //如果该节点的所有都没有，就往左递归删除
        if(this.left != null){
            this.left.delete(id);
        }
        //如果左边递归没删掉，就往右递归
        if(this.right != null){
            this.right.delete(id);
        }
    }

}