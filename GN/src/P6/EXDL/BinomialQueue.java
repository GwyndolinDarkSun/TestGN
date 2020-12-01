package P6.EXDL;


import java.nio.BufferUnderflowException;

public class BinomialQueue<AnyType extends Comparable<? super AnyType>>{
    //储存方式
    public BinomialQueue(){

    }
    public BinomialQueue(AnyType item){

    }

    public void merge(BinomialQueue<AnyType> rhs){
        if(this == rhs){
            return;
        }

        //解决储存空间问题
        currentSize += rhs.currentSize;
        if(currentSize > capacity()){
            int maxLength = Math.max(theTrees.length,rhs.theTrees.length);
            expendTheTrees(maxLength + 1);
        }

        //这里依据第一个树的规则来
        Node<AnyType> carry = null;
        for(int i = 0,j = 1;j<=currentSize;i++,j*=2){
            Node<AnyType> t1 = theTrees[i];
            Node<AnyType> t2 = i < rhs.theTrees.length ? rhs.theTrees[i]:null;

            int whichCase = t1 ==null ? 0:1;
            whichCase += t2 ==null ? 0:2;
            whichCase += carry == null? 0:4;

            switch (whichCase){
                case 0:
                case 1:
                    break;
                case 2:
                    theTrees[i] = carry;
                    carry = null;
                    break;
                case 4:
                    theTrees[i] = carry;
                    carry = null;
                    break;
                case 3:
                    carry = combineTrees(t1,t2);
                    theTrees[i] = null;
                    break;
                case 5:
                    carry = combineTrees(t1,carry);
                    theTrees[i] = null;
                    break;
                case 6:
                    carry = combineTrees(t2,carry);
                    carry = combineTrees(t1,t2);
                    rhs.theTrees[i] = null;
                    break;
            }
        }
        //清空操作
        for(int k = 0;k < rhs.theTrees.length;k++){
            rhs.theTrees[k] = null;
        }
        rhs.currentSize = 0;

    }


    public void insert(AnyType x){
        merge(new BinomialQueue<>(x));
    }
    public AnyType findMin(){
        return (AnyType) new Object();
    }
    public AnyType deleteMin(){
        if(isEmpty()){
            throw new BufferUnderflowException();
        }

        int minIndex = findMinIndex();
        AnyType minItem = theTrees[minIndex].element;

        Node<AnyType> deletedTree = theTrees[minIndex].leftChild;
        BinomialQueue<AnyType> deletedQueue = new BinomialQueue<>();
        deletedQueue.expendTheTrees(minIndex + 1);

        deletedQueue.currentSize = (1 << minIndex) - 1;
        for(int j = minIndex - 1;j >= 0;j --){
            deletedQueue.theTrees[j] = deletedTree;
            deletedTree = deletedTree.nextSibling;
            deletedQueue.theTrees[j].nextSibling = null;
        }
        //扯出来然后塞进去

        theTrees[minIndex] = null;
        currentSize -= deletedQueue.currentSize + 1;

        merge(deletedQueue);

        return minItem;

    }

    public boolean isEmpty(){
        return currentSize == 0;
    }
    public void makeEmpty(){

    }

    private static class Node<AnyType>{
        Node(AnyType theElement){
            this(theElement,null,null);
        }

        Node(AnyType theElement,Node<AnyType> lt,Node<AnyType> nt){
            element = theElement;
            leftChild = lt;
            nextSibling = nt;
        }

        AnyType element ;
        Node<AnyType> leftChild;
        Node<AnyType> nextSibling;

    }

    private static final int DEFAULT_TREES = 1;

    private int currentSize;
    private Node<AnyType> [] theTrees;

    private void expendTheTrees(int newNumTrees){

    }

    //比较的是值,看成一个个小的单元,整体和个体要结合分析
    //链表就是现成接出来的(模拟一下就好了)
    private Node<AnyType> combineTrees(Node<AnyType> t1,Node<AnyType> t2){
        if(t1.element.compareTo(t2.element) > 0){
            return combineTrees(t2,t1);
        }
        t2.nextSibling = t1.leftChild;
        t1.leftChild = t2;
        return t1;

    }

    private int capacity(){
        return (1 << theTrees.length) - 1;
    }
    private int findMinIndex(){
        return 1;
    }

}
