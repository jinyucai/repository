package com.cai.tree;

import javax.xml.soap.Node;

/**
 * 线索化二叉树
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode2 root = new HeroNode2(1, "tom");
        HeroNode2 node1 = new HeroNode2(3, "rose");
        HeroNode2 node2 = new HeroNode2(8, "jack");
        HeroNode2 node3 = new HeroNode2(10, "smith");
        HeroNode2 node4 = new HeroNode2(6, "jhon");
        HeroNode2 node5 = new HeroNode2(14, "jhony");
        root.setLeft(node1);
        node1.setLeft(node2);
        node1.setRight(node3);
        root.setRight(node4);
        node4.setLeft(node5);
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree(root);
        //线索化
//        threadedBinaryTree.threadedBinaryTree();
//        System.out.println(node3.getLeft());
//        System.out.println(node3.getRight());
        //输出中序遍历线索化二叉树
//        threadedBinaryTree.threadInfixOrder();
        //前序遍历
//        threadedBinaryTree.preThreadTree(root);
//        System.out.println(node3.getLeft());
//        System.out.println(node3.getRight());
//        threadedBinaryTree.threadOrderPre();

        //后序线索化
        threadedBinaryTree.postThreadTree(root);
        System.out.println(node4.getLeft());
        System.out.println(node4.getRight());
        System.out.println("后序线索化遍历");
        threadedBinaryTree.postThreadOrder();
    }
}


class ThreadedBinaryTree {
    private HeroNode2 root;// 根节点

    private HeroNode2 pre; //线索化用的，表示当前节点的前继节点

    public ThreadedBinaryTree(HeroNode2 root) {
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

    //重载
    public void threadedBinaryTree(){
        this.threadedBinaryTree(root);
    }

    /**
     * 中序遍历线索化二叉树
     */
    public void threadInfixOrder(){
        if(root == null){
            System.out.println("树为空");
            return;
        }
        HeroNode2 node = root;
        while(node != null){
            while(node.getLeftType() == 0){
                //先遍历左子树，找到第一个数字
                node = node.getLeft();
            }
            //这是是第一个数字
            System.out.println(node);
            //来时不断往右,输出后继节点
            while (node.getRightType() == 1){
                System.out.println(node.getRight());
                node = node.getRight();
            }
            node = node.getRight();
        }
    }

    /**
     * 按照中序遍历进行线索化
     * @param node 穿过来的节点，一般开始是root
     */
    public void threadedBinaryTree(HeroNode2 node){
        if(node == null){
            System.out.println("二叉树为空");
            return;
        }
        //先向左遍历
        if(node.getLeft() != null){
            threadedBinaryTree(node.getLeft());
        }
        //表示遍历到了最后一个子左节点,且该左节点的左节点还没有指向
        //代码第一次走到这里这个子左节点是所有节点中第一个，所以没有前驱节点，就是当前的pre为空
        if(node != null && node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //向右遍历,如果遍历到最后一个右节点，name此时pre是上一个节点此时的pre是有右节点的，
        //但是当前节点没有右节点，但是当前节点没有后继节点，所以不走此方法，当前最后节点的右节点仍然为null，是正确的
        if(pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node; //当下个节点回溯的时候前驱节点就有了
        //再向右遍历
        if(node.getRight() != null){
            threadedBinaryTree(node.getRight());
        }
    }

    //前序线索化遍历
    public void threadOrderPre(){
        if(root == null){
            return;
        }
        System.out.println(root);
        HeroNode2 node = root;
        while(node.getRight() != null){
            if(node.getLeftType() == 0){
                System.out.println(node.getLeft());
                node = node.getLeft();
            }else{
                System.out.println(node.getRight());
                node = node.getRight();
            }

        }
    }


    //前序线索化
    public void preThreadTree(HeroNode2 node){
        if(node == null){
            return;
        }
        if(node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if(pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        if(node.getLeftType() == 0){
            preThreadTree(node.getLeft());
        }
        if(node.getRightType() == 0){
            preThreadTree(node.getRight());
        }

    }

    /**
     * 后序线索化遍历
     */
    public void postThreadOrder(){
        if(root == null){
            return;
        }
        HeroNode2 node = root;
        while(node != null){
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRight() != null){
                if(node.getNextNode() != null){
                    System.out.println(node.getNextNode());
                    node = node.getNextNode();
                }else{
                    System.out.println(node.getRight());
                    node = node.getRight();
                }
                if(node.getId() == root.getId()){
                    break;
                }
            }
            break;
        }
    }


    /**
     * 后序线索化
     * @param node
     */
    public void postThreadTree(HeroNode2 node){
        if(node == null){
            return;
        }

        postThreadTree(node.getLeft());

        postThreadTree(node.getRight());

        if(node != null && node.getLeft() == null){
            if(pre != null && pre.getRight() != null && pre.getRight().getRight() == pre && pre.getRight().getRightType() == 1){
                //后序线索化遍历的连接线
                pre.setNextNode(node);
            }
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if(pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }

        pre = node;

    }

}

//二叉树节点
class HeroNode2 {
    private Integer id;
    private String name;
    private HeroNode2 left; //左子节点默认为null
    private HeroNode2 right;
    private HeroNode2 nextNode; //右边下个指针，仅用于后序线索化遍历
    private int leftType; //线索化用的节点状态，0表示该左节点还没有分配,1表示已分配
    private int rightType; //通left

    public HeroNode2(Integer id, String name) {
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

    public HeroNode2 getLeft() {
        return left;
    }

    public void setLeft(HeroNode2 left) {
        this.left = left;
    }

    public HeroNode2 getRight() {
        return right;
    }

    public void setRight(HeroNode2 right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public HeroNode2 getNextNode() {
        return nextNode;
    }

    public void setNextNode(HeroNode2 nextNode) {
        this.nextNode = nextNode;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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
    public HeroNode2 preOrderFind(int id){
        //先判断当前节点是不是要找的
        if(this.id == id){
            return this;
        }
        //进行向左查找
        HeroNode2 resNode = null;
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
    public HeroNode2 inFixOrderFind(int id){
        HeroNode2 resNode = null;
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
    public HeroNode2 postOrderFind(int id){
        HeroNode2 resNode = null;
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