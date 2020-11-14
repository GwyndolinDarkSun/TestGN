package P3;

/**
 * 迭代器,java内部类和嵌套类
 */
public class MyArrayList2<AnyType> implements Iterator{

    //外部类声明一个内部类的时候会添加一个隐式引用(外部类名字.this)
    private int theSize;
    private AnyType [] theItems;

    public java.util.Iterator<AnyType> iterator(){
        return new AarrayListIterator<AnyType>(this);//使用第二种方式之后这里需要添加一个MyArrayList2对象
    }
    public int size(){
        return theSize;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Object next() {
        return null;
    }

    @Override
    public void remove() {//这里如果有current的减少,那么最好就是放在前面,因为直接对准的是减少后的(迭代器开始在虚空状态)

    }
    //好了,这就是最终方案了,不但用了内部类来保证可以用更高一级的权限来访问上一级的私有变量,而且还有链接
    //一定要链接,虽然我们不连接也看不出什么错,但是编译器却无法识别究竟是哪一个MyArrayList2<AnyType>
    private static class  AarrayListIterator<AnyType> implements java.util.Iterator<AnyType>{
        //这里一定要声明private和static
        //前者是保证了隐秘性
        //后者如果不存在那么就会导致theList可能被删除(因为MyArrayList2.this完全可以替代它的作用)这里用了一个提前量的方式
        private int current = 0;
        private MyArrayList2<AnyType> theList;//这就是通过属性来链接两者,使其中一个可以调用另外一个的方法

        public AarrayListIterator(MyArrayList2<AnyType> list){
            this.theList = list;
        }

        public boolean hasNext(){
            return current < theList.size();//只要当前下标小于总长度,那么就可以有下一项
        }
        public AnyType next(){
            return theList.theItems[current++];
        }
        //还可以更加的简化用MyArrayList2.this来引用(构造一个不需要传递对象的隐式引用)
    }

}
/**
class AarrayListIterator<AnyType> implements java.util.Iterator<AnyType>{

    private int current = 0;


    @Override
    public boolean hasNext() {
        return current < size();
    }

    @Override
    public AnyType next() {//该方法使得向前推进

        return (AnyType) theItems[current++];//要保证迭代器下一个还是AnyType
        //这里++是一种后缀的形式,因此第一次的的时候就是原本的值(0)然后current增加了
        //问题就是相当明显了,我们不能调用其他类的theItems[current++]和size()属性,这是非法的
    }
}
*/

/**
class AarrayListIterator<AnyType> implements java.util.Iterator<AnyType>{
    private int current = 0;
    private MyArrayList2<AnyType> theList;//这就是通过属性来链接两者,使其中一个可以调用另外一个的方法

    public AarrayListIterator(MyArrayList2<AnyType> list){
        this.theList = list;
    }

    public boolean hasNext(){
        return current < theList.size();//只要当前下标小于总长度,那么就可以有下一项
    }
    public AnyType next(){
        return theList.theItems[current++];//这里问题十分的明显,那就是属性是私有的,一般方法无法访问
    }

}
 */
