package com.cat.sort.basic;

/**
 * <p>Title: BubbleSortDemo</p>
 * <p>description: </p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/9/9 8:19
 **/
public class BubbleSortDemo {
    public static void main(String[] args) {
        int[] test = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        bubbleSort(test);
    }

    /**
     * 冒泡排序
     *      交换排序的一种
     * 算法描述：
     *     1.比较相邻的元素。如果第一个比第二个大，就交换它们两个；
     *     2.对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素将会是最大的数；
     *     3.针对所有的元素重复以上的步骤，除了最后一个；
     *     4.重复步骤1~3，直到排序完成。
     * 效率分析：
     *      最佳情况：T(n) = O(n)   最差情况：T(n) = O(n^2)   平均情况：T(n) = O(n^2)
     * @param test 排序数组
     */
    private static void bubbleSort(int[] test) {
        int sortCount = 0;
        int compareCount = 0;

        for (int i = 0; i < test.length -1; i++) {
            sortCount++;
            for (int j = 0; j < test.length - 1 - i; j++) {
                if (test[j] > test[j + 1]) {
                    int temp = test[j + 1];
                    test[j + 1] = test[j];
                    test[j] = temp;
                    compareCount++;
                }
            }
            System.out.print("第" + (i + 1) + "趟\t");
            for (int index:test) {
                System.out.print(index + "\t");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("排序数组长度\t"+test.length);
        System.out.println("排序趟数\t" + sortCount);
        System.out.println("比较次数\t" + compareCount);
    }
}
