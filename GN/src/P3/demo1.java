package P3;

/**
 * 以下是通过数组对列表的简单实现
 */
public class demo1 {
    public static void main(String[] args) {
        int[] arr = new int[10];
        //接下来对表进行简单的扩容
        int[] newArr = new int[arr.length * 2];
        for(int i = 0;i < arr.length;i ++){
            newArr[i] = arr[i];
        }//遍历arr中的元素依次赋值给newArry
        arr = newArr;//最后再将arr指向扩容后数组的地址
    }
}
/**
 * 很显然,列表的优势在于查找和输出,而增删操作则是具有十分昂贵的开销
 * (越靠前的插入开销越大)
 */
