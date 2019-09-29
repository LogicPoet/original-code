package pat_basic_level;

import java.util.Scanner;

/**
 * <p>Title: PAT_1043</p>
 * <p>description:
 * 1043 输出PATest (20 分)
 * 给定一个长度不超过 10
 * ​4
 * ​​  的、仅由英文字母构成的字符串。请将字符重新调整顺序，按 PATestPATest.... 这样的顺序输出，并忽略其它字符。当然，六种字符的个数不一定是一样多的，若某种字符已经输出完，则余下的字符仍按 PATest 的顺序打印，直到所有字符都被输出。
 * <p>
 * 输入格式：
 * 输入在一行中给出一个长度不超过 10
 * ​4
 * ​​  的、仅由英文字母构成的非空字符串。
 * <p>
 * 输出格式：
 * 在一行中按题目要求输出排序后的字符串。题目保证输出非空。
 * <p>
 * 输入样例：
 * redlesPayBestPATTopTeePHPereatitAPPT
 * 输出样例：
 * PATestPATestPTetPTePePee
 *
 * </p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/09/29 20:52
 *
 * <p>
 * 总分20
 * <p>
 * 完成时间：00:20:00
 * 得分20
 **/
public class PAT_1043 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String test = input.next();

        //2.计算 PATest 关键字符的个数
        int[] keys = new int[6];
        char[] chars = test.toCharArray();
        for (char c : chars) {
            if (c == 'P') {
                keys[0] += 1;
            }
            if (c == 'A') {
                keys[1] += 1;
            }
            if (c == 'T') {
                keys[2] += 1;
            }
            if (c == 'e') {
                keys[3] += 1;
            }
            if (c == 's') {
                keys[4] += 1;
            }
            if (c == 't') {
                keys[5] += 1;
            }
        }

        //3.顺序输出PATest，如果keys不为0
        while (
                keys[0] != 0 || keys[1] != 0 || keys[2] != 0 || keys[3] != 0 || keys[4] != 0 || keys[5] != 0) {
            if (keys[0] > 0) {
                System.out.print("P");
                keys[0] -= 1;
            }
            if (keys[1] > 0) {
                System.out.print("A");
                keys[1] -= 1;
            }
            if (keys[2] > 0) {
                System.out.print("T");
                keys[2] -= 1;
            }
            if (keys[3] > 0) {
                System.out.print("e");
                keys[3] -= 1;
            }
            if (keys[4] > 0) {
                System.out.print("s");
                keys[4] -= 1;
            }
            if (keys[5] > 0) {
                System.out.print("t");
                keys[5] -= 1;
            }
        }
    }
}
