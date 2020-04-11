package com.cat.prologue;

/**
 * <p>Title: MaxSub</p>
 * <p>description:
 * 求给定N个整数的序列[A,B,C,D,....]最大的连续子列和
 * </p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/11/2 21:54
 **/
public class MaxSub {
    public static void main(String[] args) {
        int[] test1 = {1, 2, 3, 4, 5, 6};
        int[] test2 = {1, -2, 3, -4, 5, -6, 4, 6, -1, -3, 4, -7, 1, 2, 3, 4, -5, -6, 1, 1, 8, 9, -2, 3};
        int[] test3 = {4, -3, 5, -2, -1, 2, 6, -2};

        int result = maxSubseqSum1(test3, test3.length);
        System.out.println(result);

        result = maxSubseqSum2(test3, test3.length);
        System.out.println(result);

        result = maxSubseqSum3(test3, test3.length);
        System.out.println(result);
    }

    /**
     * 求给整数的序列最大子列和
     * n^3
     *
     * @param list 需要计算的数组
     * @return 最大子列和
     */
    private static int maxSubseqSum1(int[] list, int length) {
        int thisSum, maxSum;

        maxSum = 0;
        int i, j, k;
        //遍历整个数组,i是子列左端位置
        for (i = 0; i < length; i++) {
            //从当前下标处，遍历出自当前下标开始的所有连续的子列
            //j是子列右端位置
            for (j = i; j < length; j++) {
                //thisSum是从A[i]到A[j]的子列和
                thisSum = 0;
                //计算当前子列的和
                for (k = i; k <= j; k++) {
                    //从左端累加到右端
                    thisSum += list[k];
                }
                //判断刚才得到的这个子列和是否更大
                if (thisSum > maxSum) {
                    //更新最大子列和
                    maxSum = thisSum;
                }
            }
        }

        return maxSum;
    }

    /**
     * 求给整数的序列最大子列和
     * 对maxSum1的改进
     * n^2
     *
     * @param list 需要计算的数组
     * @param length 序列长度
     * @return 最大子列和
     */
    private static int maxSubseqSum2(int[] list, int length) {
        int thisSum, maxSum = 0;
        int i, j;
        //遍历目标数组，i是子列的左端
        for (i = 0; i < length; i++) {
            //ThisSum是从A[i]到A[j]的子列和
            thisSum = 0;
            //j是子列的左端
            //对于相同的i，不同的j，只要在j-1次循环的基础上累加1项即可
            for (j = i; j < length; j++) {
                //将上一个连续子列的和作为下一个连续子列的和的一部分，并加上连续子列的下一位
                thisSum += list[j];

                //如果刚得到的这个子列和更大
                if (thisSum > maxSum) {
                    //更新最大子列和
                    maxSum = thisSum;
                }
            }
        }

        return maxSum;
    }

    /**
     * 返回3个整数中的最大值
     *
     * @param a 其中一个数
     * @param b 其中一个数
     * @param c 其中一个数
     * @return 最大的一个数
     */
    private static int max3(int a, int b, int c) {
        return a > b ? a > c ? a : c : b > c ? b : c;
    }

    /**
     * 分治法求List[left]到List[right]的最大子列和
     *
     * @param list  目标序列
     * @param left  左端下标
     * @param right 右端下标
     * @return 最大连续子列和
     */
    private static int divideAndConquer(int[] list, int left, int right) {
        /* 存放左右子问题的解 */
        int maxLeftSum, maxRightSum;
        /*存放跨分界线的结果*/
        int maxLeftBorderSum, maxRightBorderSum;

        int leftBorderSum, rightBorderSum;
        int center, i;

        /* 递归的终止条件，子列只有1个数字 */
        if (left == right) {
            if (list[left] > 0) {
                return list[left];
            } else {
                return 0;
            }
        }

        /* 下面是"分"的过程 */
        /* 找到中分点 */
        center = (left + right) / 2;
        /* 递归求得两边子列的最大和 */
        maxLeftSum = divideAndConquer(list, left, center);
        maxRightSum = divideAndConquer(list, center + 1, right);

        /* 下面求跨分界线的最大子列和 */
        maxLeftBorderSum = 0;
        leftBorderSum = 0;
        /* 从中线向左扫描 */
        for (i = center; i >= left; i--) {
            leftBorderSum += list[i];
            if (leftBorderSum > maxLeftBorderSum) {
                maxLeftBorderSum = leftBorderSum;
            }

        } /* 左边扫描结束 */

        maxRightBorderSum = 0;
        rightBorderSum = 0;
        /* 从中线向右扫描 */
        for (i = center + 1; i <= right; i++) {
            rightBorderSum += list[i];
            if (rightBorderSum > maxRightBorderSum) {
                maxRightBorderSum = rightBorderSum;
            }
        } /* 右边扫描结束 */

        /* 下面返回"治"的结果 */
        return max3(maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum);
    }

    /**
     * 求给整数的序列最大子列和
     * 分治法求解
     * n log n
     *
     * @param list   目标序列
     * @param length 序列长度
     * @return 最大连续子列和
     */
    private static int maxSubseqSum3(int[] list, int length) { /* 保持与前2种算法相同的函数接口 */
        return divideAndConquer(list, 0, length - 1);
    }

    /**
     * 求给整数的序列最大子列和
     * 采用在线处理的方式
     * “在线”的意思是指每输入一个数据就进行即时处理，在任
     * 何一个地方中止输入，算法都能正确给出当前的解。
     *
     * @param list 需要计算的数组
     * @return 最大子列和
     */
    private static int maxSubseqSum4(int[] list, int length) {
        int thisSum, maxSum;
        int i;
        thisSum = maxSum = 0;
        for (i = 0; i < length; i++) {
            /* 向右累加 */
            thisSum += list[i];
            if (thisSum > maxSum) {
                /* 发现更大和则更新当前结果 */
                maxSum = thisSum;
            } else if (thisSum < 0) {
                /* 如果当前子列和为负 */
                /* 则不可能使后面的部分和增大，抛弃之 */
                /* 因为如果是负的话，后续部分即使会超过最大的，但此时也说明抛弃这个负值之后，该子列和比如会超过之前的，因此只需直接抛弃就行 */
                thisSum = 0;
            }
        }
        return maxSum;
    }
}
