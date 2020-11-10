package P3;

/**
 *迭代器的使用
 */
public interface Iterator <AnyType>{
    boolean hasNext();//是否存在下一项
    AnyType next();//调整到下一项
    void remove();//删除由next所返回的最新的项(相比于Collection的同名方法,该方法不用寻找删除对象,因此开销更小)
    //通过方法,每个集合均可返回给客户一个iterator接口的对象,并将当前位置的概念在对象内部储存下来

    public static <AnyType> void print(Collection<AnyType> coll){
        java.util.Iterator<AnyType> itr = coll.iterator();//通过方法来获得对象
        while (itr.hasNext()){//判断是否拥有下一项
            AnyType item = itr.next();
            System.out.println(item);
        }//迭代器只有在未改变集合结构的时候是合法的
    }


}
