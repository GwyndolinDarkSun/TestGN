package P6.ZSD;

import java.nio.BufferUnderflowException;

public class LeftistHeap <AnyType extends Comparable<? super AnyType>>{
    public LeftistHeap(){
        root = null;
    }

    public void merge(LeftistHeap<AnyType> rhs){
        if(this == rhs){
            return;
        }
        root = merge(root,rhs.root);
        rhs.root = null;
    }
    public void insert(AnyType x){
        root = merge(new Node<>(x),root);
    }
    public AnyType findMin(){
        return (AnyType) new Object();
    }


    public AnyType deleteMin(){
        if(isEmpty()){
            throw new BufferUnderflowException();
        }
        AnyType minItem = root.element;
        root = merge(root.left,root.right);

        return minItem;
    }

    public boolean isEmpty(){
        return root == null;
    }
    public void makeEmpty(){
        root = null;
    }

    //比起传统二叉树的节点,这里多了一个零路径长
    private static class Node<AnyType>{
        Node(AnyType theElement){
            this(theElement,null,null);
        }
        Node(AnyType theElement,Node<AnyType> lt,Node<AnyType> rt){
            element = theElement;
            left = lt;
            right = rt;
        }
        AnyType element;
        Node<AnyType> left;
        Node<AnyType> right;
        int npl;
    }

    private Node<AnyType> root;

    //就是一个排除特殊情况的方法
    private Node<AnyType> merge(Node<AnyType> h1,Node<AnyType> h2){
        if(h1 == null){
            return h2;
        }
        if(h2 == null){
            return h1;
        }
        //保证一定是把小的拆了合并到大的中间
        if(h1.element.compareTo(h2.element) < 0){
            return merge1(h1,h2);
        }else{
            return merge1(h2,h1);
        }
    }

    //通过把堆堪称单节点来合并
    private Node<AnyType> merge1(Node<AnyType> h1,Node<AnyType> h2){
        if(h1.left == null){
            h1.left = h2;//基准条件
        }else{
            h1.right = merge(h1.right,h2);
            if(h1.left.npl<h1.right.npl){
                swapchildren(h1);//深度不对立马交换
            }
            h1.npl = h1.right.npl + 1;
        }
        return h1;
    }
    private void swapchildren(Node<AnyType> t){

    }
}
