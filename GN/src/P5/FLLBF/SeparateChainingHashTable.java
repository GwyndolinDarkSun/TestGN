package P5.FLLBF;

import java.util.LinkedList;
import java.util.List;

public class SeparateChainingHashTable<AnyType> {

    private static final int DEFAULT_TABLE_SIZE = 10;
    private List<AnyType> [] theLists;
    private int currentSize;

    public SeparateChainingHashTable(){
        this(DEFAULT_TABLE_SIZE);
    }
    public SeparateChainingHashTable(int size){
        theLists =new LinkedList[nextPrime(size)];
        for(int i = 0;i<theLists.length;i++){
            theLists[i] = new LinkedList<AnyType>();
        }
    }

    //暂时没有实现的方法
    private static int nextPrime(int n){
        return 1;
    }
    private static boolean isPrime(int n){
        return false;
    }
    private void rehash(){//扩大列表的操作

    }



    public void makeEmpty(){
        for(int i = 0;i < theLists.length;i++){
            theLists[i].clear();
        }
        currentSize = 0;
    }

    private int myhash(AnyType x){
        int hashVal = x.hashCode();
        hashVal %= theLists.length;
        if(hashVal < 0){
            hashVal += theLists.length;
        }
        return hashVal;
    }

    public boolean contains(AnyType x){
        List<AnyType> whichList = theLists[myhash(x)];//正因为列表中的元素都是一样的(定义的,大多情况有微笑差别),才敢这么玩
        return  whichList.contains(x);
    }

    public void insert(AnyType x){
        List<AnyType> whichList = theLists[myhash(x)];
        if(!whichList.contains(x)){
            whichList.add(x);
            if(++currentSize > theLists.length){
                rehash();
            }
        }
    }

    public void remove(AnyType x){
        List<AnyType> whichList = theLists[myhash(x)];
        if(whichList.contains(x)){
            whichList.remove(x);
            currentSize--;
        }
    }


}

//这里是重写了equals()方法的要存储的元素元素
class Employee{
    public boolean equals(Object rhs){
        return (rhs instanceof Employee) && name.equals(((Employee)rhs).name);
    }

    private String name;
    private double salary;
    private int seniority;
}