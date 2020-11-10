package P3;

public class MyArrayList<AnyType> implements Iterator<AnyType>{
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


    private static final int DEFAULT_CAPACITY = 10;

    private int theSize;
    private AnyType[] theItems;

    public MyArrayList(){
        doClear();
    }

    public void clear(){
        doClear();
    }

    private void doClear(){
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);//通过new一个新的但是内容为空的数组代替原数组(但是容量一样)
    }

    public int size(){
        return theSize;
    }
    public boolean isEmpty(){
        return size() == 0;
    }
    public void trimToSize(){
        ensureCapacity(size());
    }

    public AnyType get(int idx){
        if(idx < 0 || idx >= size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        return theItems[idx];
    }

    public void ensureCapacity(int newCapacity){
        if(newCapacity < theSize){
            return;
        }

        AnyType[] old = theItems;
        theItems = (AnyType[]) new Object[newCapacity];//因为泛型数组的创建是违法的
        for(int i = 0;i < size();i++){
            theItems[i] = old[i];
        }
    }

    public boolean add(AnyType x){
        add(size(),x);
        return true;//直接添加到末端的方式
    }

    public void add(int idx,AnyType x){
        if(theItems.length == size()){
            ensureCapacity(size()*2+1);
        }//这里可能会要求扩容
        for(int i = theSize;i > idx;i--){
            theItems[i] = theItems[i + 1];
        }//通过将idx以及其以后的元素全部往后赋值,最后将该位置附上新的值,前面的保持不动的方式
        theItems[idx] = x;
        theSize++;
    }

    public AnyType remove(int idx){
        AnyType removedItem = theItems[idx];
        for(int i = idx;i < size() - 1;i ++){
            theItems[i] = theItems[i + 1];
        }
        theSize --;
        return removedItem;
    }

    public java.util.Iterator<AnyType> iterator(){
        return (java.util.Iterator<AnyType>) new ArrayListIterator();//这里体现了将返回一个对象的体现
    }
    class ArrayListIterator<AnyType> implements Iterator<AnyType>{
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public AnyType next() {
            if(!hasNext()){
                throw new java.util.NoSuchElementException();
            }
            return (AnyType) theItems[current++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }
}

