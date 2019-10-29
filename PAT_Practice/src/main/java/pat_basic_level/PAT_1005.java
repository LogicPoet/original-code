package pat_basic_level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * <p>Title: PAT_1005</p>
 * <p>description:
 * 1005 继续(3n+1)猜想 (25 分)
 * 卡拉兹(Callatz)猜想已经在1001中给出了描述。在这个题目里，情况稍微有些复杂。
 * <p>
 * 当我们验证卡拉兹猜想的时候，为了避免重复计算，可以记录下递推过程中遇到的每一个数。例如对 n=3 进行验证的时候，我们需要计算 3、5、8、4、2、1，则当我们对 n=5、8、4、2 进行验证的时候，就可以直接判定卡拉兹猜想的真伪，而不需要重复计算，因为这 4 个数已经在验证3的时候遇到过了，我们称 5、8、4、2 是被 3“覆盖”的数。我们称一个数列中的某个数 n 为“关键数”，如果 n 不能被数列中的其他数字所覆盖。
 * <p>
 * 现在给定一系列待验证的数字，我们只需要验证其中的几个关键数，就可以不必再重复验证余下的数字。你的任务就是找出这些关键数字，并按从大到小的顺序输出它们。
 * <p>
 * 输入格式：
 * 每个测试输入包含 1 个测试用例，第 1 行给出一个正整数 K (<100)，第 2 行给出 K 个互不相同的待验证的正整数 n (1<n≤100)的值，数字间用空格隔开。
 * <p>
 * 输出格式：
 * 每个测试用例的输出占一行，按从大到小的顺序输出关键数字。数字间用 1 个空格隔开，但一行中最后一个数字后没有空格。
 * <p>
 * 输入样例：
 * 6
 * 3 5 6 7 8 11
 * 输出样例：
 * 7 6
 * </p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/10/9 21:10
 **/
public class PAT_1005 {
    /**
     * 1.对所有用例进行 卡拉兹 验证，将这些数字存带
     * 2.将关键字数组排序Arrays.sort();
     * 3.逆序输出该已排序数组
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        //输入测试用例个数k
        int k = Integer.parseInt(input.readLine());
        //获取输入的数字
        String[] str = (input.readLine()).split(" ");
        //存储非关键字数组
        List<Integer> candidate = new ArrayList<>();
        //存储关键字
        int[] keys = new int[str.length];

        //进行 卡拉兹 运算，找出这一过程中所有数字的数组
        for (int i = 0; i < str.length; i++) {
            int parseInt = Integer.parseInt(str[i]);
            if (!candidate.contains(parseInt)) {
                int n = parseInt;
                while (1 < n) {
                    //1.首先判断奇偶性
                    if (n % 2 == 0) {
                        //2.1它是偶数，那么把它砍掉一半
                        n = n / 2;
                        if (!candidate.contains(n)) {
                            candidate.add(n);
                        }
                    } else {
                        //2.2它是奇数，那么把 (3n+1) 砍掉一半。
                        n = (3 * n + 1) / 2;
                        if (!candidate.contains(n)) {
                            candidate.add(n);
                        }
                    }
                }

                //将关键字存储到数组中
                if (i > 0) {
                    keys[i] = parseInt;
                }

            }
        }

        int parseInt = Integer.parseInt(str[0]);
        if (!candidate.contains(parseInt)) {
            keys[keys.length - 1] = parseInt;
        }

        //将关键字从小到大排序
        Arrays.sort(keys);
        //逆序输出关键字
        for (int i = keys.length - 1; i >= 0; i--) {
            if (keys[keys.length - 1] == 0) {
                break;
            }
            System.out.print(keys[i]);
            if (keys[i - 1] != 0) {
                System.out.print(" ");
            } else {
                break;
            }

        }
    }
}
