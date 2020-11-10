package P3;

/**
 * 下面是Collection接口
 */
public class demo3 {

}

interface Collection<AnyType> extends  Iterable<AnyType>{
    int size();
    boolean isEmpty();
    void clear();
    boolean contains(AnyType x);
    boolean add(AnyType x);
    boolean remove(AnyType x);
    //以上是其拥有方法,因此在继承了这个接口的API接口或者类(集合)便拥有了这些方法
    java.util.Iterator<AnyType> iterator();
    //因为该接口继承了Iterator接口,因此拥有了许多增强for循环的功能(iterator()迭代器方法)
}
