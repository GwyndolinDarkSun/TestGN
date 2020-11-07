package P1;

public class demo2 {
    public static int maxSubSum4(int[] a){//通过过大(负数)作为节点切分(一旦前面的足以抵消负数那么就可以以他作为起点)
        int maxSum = 0;
        int thisSum = 0;
        for(int j = 0;j < a.length;j ++){
            thisSum += a[j];
            if(thisSum > maxSum){
                maxSum = thisSum;
            }else if(thisSum < 0){
                thisSum = 0;//一旦小于零证明前面所有值都不足以抵消哪个最负值,因此前面往后加已经没有意义了
            }
        }
        return maxSum;
    }
}
