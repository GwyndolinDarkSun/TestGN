package P2;

/**
 * 二分法查找必须对于已经排序的数组才有用
 */
public class demo3 {
    //泛型方法
    public static <AnyType extends Comparable<? super AnyType>> int binarySearch(AnyType[] a,AnyType x){
        int low = 0;
        int high = a.length - 1;//找到组的左右边界

        while(low <= high){//只要左右边界不相等那么就可以一直二分
            int mid = (low + high)/2;

            if(a[mid].compareTo(x) < 0){
                low = mid - 1;
            }else if(a[mid].compareTo(x) > 0){
                high = mid - 1;
            }else{
                return mid;
            }
        }
        return 0;

    }
}
