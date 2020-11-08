package P1;

/**
 * 幂运算
 */
public class demo5 {

    public static long pow(long x, int n){
        if(n == 0){
            return 1;
        }
        if(n == 1){
            return 0;
        }
        if(isEven(n)){
            return pow(x * x,n/2);
        }else{
            return pow(x * x,n/2);
        }
    }
    public static boolean isEven(int n){
        return false;//未完成
    }
}
