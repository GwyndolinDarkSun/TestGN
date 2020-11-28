package P6.ECD;

import java.nio.BufferUnderflowException;

public class BinaryHeap <AnyType extends Comparable<? super AnyType>>{
    public BinaryHeap(){

    }
    public BinaryHeap(int capacity){

    }
    public BinaryHeap(AnyType[] items){
        currentSize = items.length;
        array = (AnyType[]) new Comparable[(currentSize + 2) *11/10];//因为继承了Comparable,否则是Object

        int i = 1;
        for(AnyType item:items){
            array[i ++] = item;
        }
        buildHeap();
    }

    //一直使用交换历程耗费太大,因此可以使用下移的操作
    public void insert(AnyType x){
        if(currentSize == array.length - 1){
            enlargeArray(array.length *2+1);
        }
        int hole = ++currentSize;
        for(array[0] = x;x.compareTo(array[hole/2]) < 0;hole /= 2){
            array[hole] = array[hole/2];
        }
        array[hole] = x;
    }

    public AnyType finMin(){
        return (AnyType) new Object();
    }

    //下滤则是将最后加入的插到合适的位置
    public AnyType deleteMin(){//不能直接删除,因为数组增删消耗太大了
        if(isEmpty()){
            throw new BufferUnderflowException();
        }

        AnyType minItem = finMin();
        array[1] = array[currentSize--];//只是先放上去,然后用工具方法调整
        percolateDown(1);

        return minItem;
    }
    public boolean isEmpty(){
        return false;
    }
    public void makeEmpty(){

    }

    private static final int DEFAULT_CAPACITY = 10;

    private int currentSize;
    private AnyType[] array;

    //删除之后的调整工作
    private void percolateDown(int hole){
        int child;
        AnyType tmp = array[hole];//防止被覆盖,先将它保留起来(形成一个空位)

        while(hole*2 <= currentSize){
            child = hole*2;
            if(child != currentSize && array[child + 1].compareTo(array[child]) < 0){
                child ++;//保证一定不是每一层最后一个(右儿子)
            }
            if(array[child].compareTo(tmp) < 0){
                array[hole] = array[child];
            }else {
                break;
            }
            hole = child;
        }
        array[hole] = tmp;
    }
    private void buildHeap(){
        for(int i = currentSize/2;i > 0;i --){
            percolateDown(i);//每次都调整以下(从中间开始调整)
        }
    }
    private void enlargeArray(int newSize){

    }
}
