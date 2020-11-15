package P3;

public class MyLinkList<AnyType> implements Iterator<AnyType> {
    //这里是数据成员
    private int theSize;//帮助迭代器检测数据变化(要排除头尾的)
    private int modCount;//记录迭代器改变的次数(保证和迭代器的次数是一致的)

    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;//这两个是引用头节点和尾节点(因为十分特殊因此可以排除在我们可以填充的位置之外)

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public AnyType next() {
        return null;
    }

    @Override
    public void remove() {

    }


    //其中的嵌套Node类
    private static class Node<AnyType>{
        public Node(AnyType d,Node<AnyType> p,Node<AnyType> n){//诞生之时就赋予了头尾和本体
            data = d;
            prev = p;
            next = n;
        }
        //两个链接
        public AnyType data;//本体
        public Node<AnyType> prev;
        public Node<AnyType> next;//数据成员都是公用的(因为类是私有的,因此无关紧要)
    }

    //构造方法,无参证明需要一个空的
    public MyLinkList(){
        doClear();
    }

    //clear历程
    public void clear(){
        doClear();
    }
    private void doClear(){
        beginMarker = new Node<AnyType>(null,null,null);
        endMarker = new Node<AnyType>(null,beginMarker,null);
        beginMarker.next = endMarker;//这里必须是清空了end才能,不然的话两头接不上(尾巴是新new出来的)
        //一个船新的链表
        theSize = 0;
        modCount++;
    }

    //获取长度
    public int size(){
        return theSize;
    }

    //添加历程
    public boolean add(AnyType x){
        add(size(),x);
        return true;
    }
    public void add(int idx,AnyType x){
        addBefore(getNode(idx,0,size()),x);
    }//查到了才能添加
    //添加不是添加节点,而是节点上的元素

    //get()set()
    public AnyType get(int idx){
        return getNode(idx).data;
    }
    public AnyType set(int idx,AnyType newVal){
        Node<AnyType> p = getNode(idx);
        AnyType oldVal = p.data;
        p.data = newVal;
        return oldVal;
    }

    public AnyType remove(int idx){
        return remove(getNode(idx));
    }

    //add历程(链接历程)
    public void addBefore(Node<AnyType> p,AnyType x){
        Node<AnyType> newNode = new Node<>(x,p.prev,p);
        newNode.prev.next = newNode;//因为一开始只有新节点连接了旧节点,因此需要一个来回来连接
        p.prev = newNode;
        theSize++;
        modCount++;
    }
    //删除历程
    private AnyType remove(Node<AnyType> p){
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize --;
        modCount ++;

        return p.data;
    }


    //获取历程(一切的基础,只有依次找到了节点,才能对其增删改查)
    private Node<AnyType> getNode(int idx){
        return getNode(idx,0,size()-1);
    }
    //第一个是为了让使用者拥有更好的体验,一般查找都是直接给idx没什么会给你范围的
    private Node<AnyType> getNode(int idx,int lower,int upper){
        Node<AnyType> p;//因为要抛出异常,因此这里会断开

        if(idx < lower || idx > upper){//越界问题
            throw new IndexOutOfBoundsException();
        }

        if(idx < size()/2){
            p = beginMarker.next;
            for(int i = 0;i < idx;i ++){
                p = p.next;//这里就可以看出,如果不使用迭代器的话,那么每次查找都会挨个查一遍
            }
        }else{
            p = endMarker;
            for(int i = size();i > idx; i--){
                p = p.prev;
            }
        }

        return p;
    }


    //这里就是迭代器的实现过程了
    private class LinkListIterator implements java.util.Iterator<AnyType>{
        private Node<AnyType> current = beginMarker.next;//第一个元素(这就是链表中的推进元素)
        private int expectedModCount =modCount;
        private boolean okToRemove = false;

        public boolean hasNext(){
            return current != endMarker;
        }//只要下一个不是最终那就证明迭代器长度不为零

        public AnyType next(){
            if(modCount != expectedModCount){
                throw new java.util.ConcurrentModificationException();//防止迭代器非法
            }
            if(!hasNext()){
                throw new java.util.NoSuchElementException();
            }

            AnyType nextItem = current.data;
            current = current.next;//向前推进
            okToRemove = true;
            return nextItem;//返回之前指向的数(这和Array不同,因为是链表,头和尾没有从开头就指向,因此一开始就是指向元素的,而不是虚空元素)
        }

        public void remove(){
            if(modCount != expectedModCount){
                throw new java.util.ConcurrentModificationException();
            }
            if(!okToRemove){
                throw new IllegalStateException();
            }

            MyLinkList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;//此时当前位置就不能再remove了
        }
    }

}
