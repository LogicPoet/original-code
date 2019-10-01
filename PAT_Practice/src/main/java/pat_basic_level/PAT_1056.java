package pat_basic_level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <p>Title: PAT_1056</p>
 * <p>description:
 * 1056 组合数的和 (15 分)
 * 给定 N 个非 0 的个位数字，用其中任意 2 个数字都可以组合成 1 个 2 位的数字。要求所有可能组合出来的 2 位数字的和。例如给定 2、5、8，则可以组合出：25、28、52、58、82、85，它们的和为330。
 * <p>
 * 输入格式：
 * 输入在一行中先给出 N（1 < N < 10），随后给出 N 个不同的非 0 个位数字。数字间以空格分隔。
 * <p>
 * 输出格式：
 * 输出所有可能组合出来的2位数字的和。
 * <p>
 * 输入样例：
 * 3 2 8 5
 * 输出样例：
 * 330
 * </p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/10/1 19:46
 *
 * <p>
 * 总分15
 * <p>
 * 完成时间：00:42:00
 * 得分15
 **/
public class PAT_1056 {

    public static void main(String[] args) throws IOException {
        //输入
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String s = input.readLine();
        String[] split = s.split(" ");

        //积和
        int result = 0;


        // 遍历输入的数组
        for (int i = 1; i < split.length; i++) {
            // 当前每个数字都能和之后的每一个数组成一个2位数
            for (int j = i + 1; j < split.length; j++) {
                // 正反都是一个新数
                int i1 = Integer.parseInt(split[i] + split[j]);
                int i2 = Integer.parseInt(split[j] + split[i]);
                // 更新积和
                result += i1 + i2;
            }

        }

        // 输出结果
        System.out.println(result);
    }
}
