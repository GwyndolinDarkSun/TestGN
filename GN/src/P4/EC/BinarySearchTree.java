package P4.EC;

import java.nio.BufferUnderflowException;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>{
    //构建树的节点对象
    private static class BinaryNode<AnyType>{
        //构造方法
        BinaryNode(AnyType theElement){
            this(theElement,null,null);
        }

        BinaryNode(AnyType theElement,BinaryNode<AnyType> lt,BinaryNode<AnyType> rt){
            element = theElement;
            left = lt;
            right = rt;
        }

        AnyType element;//存储的数据
        BinaryNode<AnyType> left;//左边的孩子
        BinaryNode<AnyType> right;//右边的孩子
    }

    private BinaryNode<AnyType> root;//当前节点

    public BinarySearchTree(){
        root = null;
    }
    public void makeEmpty(){
        root = null;
    }
    public boolean isEmpty(){
        return root == null;
    }

    //既然是查找,那么就要保证返回为boolean,
    public boolean contains(AnyType x){
        return contains(x,root);
    }
    //这里的递归存在两个基准情况
    public boolean contains(AnyType x,BinaryNode<AnyType> t){//找的元素和方向
        if(t == null){//递归的基准条件(因为要防止空树,因此同时也是判断条件,要放在前面)
            return false;
        }
        int compareResult = x.compareTo(t.element);
        //判断结果从而达到向哪个方向递归
        if(compareResult < 0){
            return contains(x,t.left);
        }else if(compareResult > 0){
            return contains(x,t.right);
        }else{
            return true;//这就是递归的基准条件
        }
    }
    //注意,t的改变时安全的,但是一定要小心树的退化


    //查找小值
    public AnyType findMin(){
        if(isEmpty()){
            throw new BufferUnderflowException();
        }
        return findMin(root).element;
    }
    public BinaryNode<AnyType> findMin(BinaryNode<AnyType> t){
        if(t == null){
            return null;
        }else if(t.left == null){
            return t;//如果右边没有了,那么就是最小
        }
        return findMin(t.left);
    }

    public AnyType findMax(){
        if(isEmpty()){
            throw new BufferUnderflowException();
        }
        return findMax(root).element;
    }

    public BinaryNode<AnyType> findMax(BinaryNode<AnyType> t){
        if(t != null){
            while(t.right != null){
                t = t.right;
            }
        }
        return t;//这里为什么可以用循环呢?其实分析不难发现:因为我们可以知道情况是不变的,如果每一次情况都发生变化,那么相应的循环
        //的层数也要发生变化
    }

    public void insert(AnyType x){
        root = insert(x,root);
    }
    public BinaryNode<AnyType> insert(AnyType x,BinaryNode<AnyType> t){
        if(t == null){
            return new BinaryNode<>(x,null,null);
        }
        int comparaResult = x.compareTo(t.element);
        if(comparaResult < 0){//这里是链接点(该点的节点就是插入的节点)
            t.left = insert(x,t.left);//这层等
        }else if(comparaResult > 0){
            t.right = insert(x,t.right);
        }
        return t;//上层等
    }

    public void remove(AnyType x){
        root = remove(x,root);
    }
    private BinaryNode<AnyType> remove(AnyType x,BinaryNode<AnyType> t){
        if(t == null){
            return t;
        }
        int compareResult = x.compareTo(t.element);
        if(compareResult < 0){
            t.left = remove(x,t.left);
        }else if(compareResult > 0){
            t.right = remove(x,t.right);
        }else if(t.left != null && t.right != null){//两个孩子
            t.element = findMin(t.right).element;
            t.right = remove(t.element,t.right);
        }else{
            t = (t.left != null) ? t.left:t.right;
        }
        return t;
    }

    public void printTree(){

    }


}


