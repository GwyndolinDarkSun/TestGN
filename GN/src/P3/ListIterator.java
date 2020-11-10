package P3;

/**
 * 这是关于ListIterator接口的
 */
public interface ListIterator<AnyType> extends Iterator<AnyType>{
    boolean hasPrevious();
    AnyType previous();//这二者使得从后向前的遍历得以完成

    void add(AnyType x);
    void set(AnyType newVal);
}
/**
 * 就这???
 * 不过是一个可以遍历后方的加强版了
 * 别忘记前后端的"非法"
 */