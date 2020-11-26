package P5.BGN;

import java.util.Random;

public class CuckooHashTable <AnyType>{
    public CuckooHashTable(HashFamily<? super AnyType> hf){
        this(hf,DEFAULT_TABLE_SIZE);
    }
    //通过传入一个HashFamily的对象来储存所有函数,一个数组
    public CuckooHashTable(HashFamily<? super AnyType> hf,int size){
        allovateArray(nextPrime(size));
        doClear();
        hashFunctions = hf;
        numHashFunctions = hf.getNumberOfFunctions();
    }

    public void makeEmpty(){
        doClear();
    }

    public boolean contains(AnyType x){
        return findPos(x) != -1;
    }

    //用于选择函数,并且计算"下标"
    private int myhash(AnyType x,int which){
        int hashVal= hashFunctions.hash(x,which);

        hashVal %= array.length;
        if(hashVal < 0){
            hashVal += array.length;
        }

        return hashVal;

    }

    //查找所有函数,返回包含x的数组下标
    private int findPos(AnyType x){
        for(int i = 0;i < numHashFunctions;i++){
            int pos = myhash(x,i);
            if(array[pos] != null && array[pos].equals(x)){
                return pos;
            }
        }
        return -1;
    }

    //依旧是接住了findPos()方法,如果有就移除(总是要返回是否存在)
    public boolean remove(AnyType x){
        int pos = findPos(x);

        if(pos != -1){
            array[pos] = null;
            currentSize--;
        }

        return pos != -1;
    }

    public void expand(){
        rehash((int)(array.length/MAX_LOAD));
    }

    //让insertHelper1()来干最脏最累的活儿
    public boolean insert(AnyType x){
        if(contains(x)){
            return false;
        }
        if(currentSize >= array.length*MAX_LOAD){
            expand();
        }

        return insertHelper1(x);
    }

    private void rehash(){
        hashFunctions.generateNewFunctions();
        rehash(array.length);
    }
    //构造一个新的数组然后依次插入进去(不同于一般数组)
    private void rehash(int newLength){
        AnyType[] oldArray = array;
        allovateArray(nextPrime(newLength));
        currentSize = 0;
        for(AnyType str : oldArray){
            if(str != null){
                insert(str);
            }
        }
    }

    private void doClear(){
        currentSize = 0;
        for(int i = 0;i < array.length;i++){
            array[i] = null;
        }
    }

    private void allovateArray(int arraySize){
        array = (AnyType[]) new Object[arraySize];
    }

    private static final double MAX_LOAD = 0.4;
    private static final int ALLOWED_REHASHES = 1;
    private static final int DEFAULT_TABLE_SIZE = 101;

    private final HashFamily<? super AnyType> hashFunctions;
    private final int numHashFunctions;
    private AnyType[] array;
    private int currentSize;

    public static int nextPrime(int n){
        return n;
    }

    //工具方法
    private int rehashes = 0;
    private Random r = new Random();
    private boolean insertHelper1(AnyType x){
        final int COUNT_LIMIT = 100;

        while(true){//一直寻找着,直到填充完成
            int lastPos = -1;
            int pos;

            for(int count = 0;count < COUNT_LIMIT;count++){
                for(int i = 0;i < numHashFunctions;i ++){
                    pos = myhash(x,i);
                    if(array[pos] == null){
                        array[pos] = x;
                        currentSize++;
                        return true;
                    }
                }
            }
            //如果进行到这里,证明散列处具有元素
            int i = 0;
            do{
                pos = myhash(x,r.nextInt(numHashFunctions));
            }while (pos == lastPos && i ++ < 5);//连续最多寻找五次(防止两个函数同时探测同一个位置)

            AnyType tmp = array[lastPos = pos];
            array[pos] = x;
            x = tmp;

            if(++rehashes > ALLOWED_REHASHES){//只要下一次大于,那么就千万别继续了!停下来拓展下课空间吧
                expand();
                rehashes = 0;
            }else{
                rehash();
            }
        }
    }
}

interface HashFamily<AnyType>{
    int hash(AnyType x,int which);
    int getNumberOfFunctions();
    void generateNewFunctions();
}
