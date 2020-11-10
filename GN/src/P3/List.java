package P3;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * list接口
 */
public interface List <AnyType> extends Collection<AnyType>{
    AnyType get(int idx);//获取索引位置的项
    AnyType set(int idx,AnyType newVal);//设置索引位置上的项
    void add(int idx,AnyType x);//
    void remove(int idx);

    ListIterator<AnyType> listIterator(int pos);
    //这是创建一个数列(末端)
    public static void makeList1(List<Integer> lst,int N){
        lst.clear();
        for(int i = 0;i < N;i++){
            lst.add(0,i);
        }
    }
    //前端(因为前端的添加是一个O(N),然而循环也是O(N),因此是O(N平方))
    public static void makeList2(List<Integer> lst,int N){
        lst.clear();
        for(int i = 0;i < N;i++){
            lst.add(0,i);
        }
    }

    //这里是求和(如果是Linklist,检索将会是一个O(N),然而循环也是O(N),因此是O(N平方))
    public static int sum(List<Integer> lst,int N){
        int total = 0;
        for(int i = 0;i < N;i++){
            total += (lst).get(i);
        }
        return total;
    }

    //很显然,这是一个十分低效的程序(本身remove就已经足够低效了,再加上低效的利用remove)
    //并且get()每次都要调用,也显得十分的低效(Linked的get不用说大家都知道)
    public static void removeEvensVer1(List<Integer> lst){
        int i= 0;
        while(i < lst.size()){
           if(lst.get(i) % 2 == 0){
               lst.remove(i);
           }else{
               i++;
           }
        }
    }

    //这个时候可以启用一种增强的for()循环
    public static void removEvensVer2(List<Integer> lst){
        for(Integer x:lst){
            if(x % 2 == 0){
                lst.remove(x);
            }
        }
    }
    //但是这里有一个问题,就是增强for循环的迭代器随着remove变得非法了!!!

    //因此有了如下这个成功的思想(迭代器的使用使得层层推进的同时又防止了非法,但是对于Array来说因为get很快则如下方法反而开销更大)
    public static void removeEvensVer3(List<Integer> lst){
        Iterator<Integer> itr = lst.iterator();

        while(itr.hasNext()){
            if(itr.next() % 2 == 0){
                itr.remove();
            }
        }
    }

}

/**
 * 因此对于ArrayList和LinkedList在分别在增删和检索方面是十分低效的
 * 因此对于ArrayList集合要前期就定好容量,尽量减少扩容
 * 但是请注意!!!!!如果使用迭代器,则对于LinkList一直都是O(N),因为迭代器总是有效的向前推进!!!!!
 */

