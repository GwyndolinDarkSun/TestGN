package P1;

public class demo1 {
    private static int maxSumRec(int[] a,int left,int right){//首先对数组的左边界和有边界做出了规定
        //只要左右边界相等那么这个就只有一个元素,该元素只要大于零就一定是最大子序列
        if(left == right){
            if(a[left] > 0){
                return a[left];
            }else{
                return 0;
            }
        }//这里就是递归的基准了！！

        int center = (left + right)/2;//控制递归(减小)的数值(不断从中间切分开)
        int maxLeftSum = maxSumRec(a,left,center);
        int maxRightSum = maxSumRec(a,center + 1,right);

        int maxLeftBorderSum = 0;
        int leftBorderSum = 0;
        for(int i = center;i >= left;i--){
            leftBorderSum += a[i];//依次相加,如果更大那么就把更大的赋予max
            if(leftBorderSum > maxLeftBorderSum);
        }

        int maxRightBorderSum = 0;
        int rightBorderSum = 0;
        for(int i = center + 1;i <= right;i++){
            rightBorderSum += a[i];
            if(rightBorderSum > maxRightBorderSum){
                maxRightBorderSum = rightBorderSum;
            }
        }

        return 0;//未完成
        //max3(maxLeftSum,maxRightSum,maxLeftBorderSum + maxRightBorderSum);

    }


    public static int maxSubSum3(int[] a){
        return maxSumRec(a,0,a.length - 1);
    }
}
