package pat_basic_level;

import java.util.Scanner;

/**
 * <p>Title: PAT_1006</p>
 * <p>description:
 * 1006 换个格式输出整数 (15 分)
 * 让我们用字母 B 来表示“百”、字母 S 表示“十”，用 12...n 来表示不为零的个位数字 n（<10），换个格式来输出任一个不超过 3 位的正整数。例如 234 应该被输出为 BBSSS1234，因为它有 2 个“百”、3 个“十”、以及个位的 4。
 * <p>
 * 输入格式：
 * 每个测试输入包含 1 个测试用例，给出正整数 n（<1000）。
 * <p>
 * 输出格式：
 * 每个测试用例的输出占一行，用规定的格式输出 n。
 * <p>
 * 输入样例 1：
 * 234
 * 输出样例 1：
 * BBSSS1234
 * 输入样例 2：
 * 23
 * 输出样例 2：
 * SS123
 *
 * </p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/09/28 20:35
 *
 * 总分15
 *
 * 完成时间：00:08:05
 * 得分15
 **/
public class PAT_1006 {

    //换个格式输出整数
    public static void main(String[] args) {
        //     *              1006 换个格式输出整数 (15 分)
        // * 让我们用
        // 字母 B 来表示“百”、
        // 字母 S 表示“十”，
        // 用 12...n 来表示不为零的个位数字 n（<10），换个格式来输出任一个不超过 3 位的正整数。
        // 例如 234 应该被输出为 BBSSS1234，因为它有 2 个“百”、3 个“十”、以及个位的 4。
        // *
        // * 输入格式：
        // * 每个测试输入包含 1 个测试用例，给出正整数 n（<1000）。
        Scanner input = new Scanner(System.in);
        int test = input.nextInt();
        // * 输出格式：
        // * 每个测试用例的输出占一行，用规定的格式输出 n。

        for (int i = 0; i < test / 100; i++) {
            System.out.print("B");
        }
        for (int i = 0; i < test % 100 / 10; i++) {
            System.out.print("S");
        }
        for (int i = 0; i < test % 100 % 10; i++) {
            System.out.print(i+1);
        }
    }
}
