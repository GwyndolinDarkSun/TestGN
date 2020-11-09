package P2;

/**
 * 欧几里得算法求gcd
 */
public class demo4 {
    public static long gcd(long m,long n){
        while(n != 0){
            long rem = m%n;
            m = n;
            n = rem;
        }
        return m;
    }
}
