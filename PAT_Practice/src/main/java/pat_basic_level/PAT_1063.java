package pat_basic_level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <p>Title: PAT_1063</p>
 * <p>description:
 * 1063 计算谱半径 (20 分)
 * 在数学中，矩阵的“谱半径”是指其特征值的模集合的上确界。换言之，对于给定的 n 个复数空间的特征值
 * { a1+b1i,⋯,an+bni }，它们的模为实部与虚部的平方和的开方，而“谱半径”就是最大模。
 * <p>
 * 现在给定一些复数空间的特征值，请你计算并输出这些特征值的谱半径。
 * <p>
 * 输入格式：
 * 输入第一行给出正整数 N（≤ 10 000）是输入的特征值的个数。随后 N 行，每行给出 1 个特征值的实部和虚部，其间以空格分隔。注意：题目保证实部和虚部均为绝对值不超过 1000 的整数。
 * <p>
 * 输出格式：
 * 在一行中输出谱半径，四舍五入保留小数点后 2 位。
 * <p>
 * 输入样例：
 * 5
 * 0 1
 * 2 0
 * -1 0
 * 3 3
 * 0 -3
 * 输出样例：
 * 4.24
 * </p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/10/1 20:36
 *
 * <p>
 * 总分20
 * <p>
 * 完成时间：01:10:00
 * 得分20
 **/
public class PAT_1063 {
    public static void main(String[] args) throws IOException {
        //输入
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        //获取N 行
        int n = Integer.parseInt(input.readLine());
        //1.计算出最大平方和
        int max = 0;

        for (int i = 0; i < n; i++) {
            String s = input.readLine();
            String[] split = s.split(" ");
            int i0 = Integer.parseInt(split[0]);
            int i1 = Integer.parseInt(split[1]);
            int current = i0 * i0 + i1 * i1;
            if (current > max) {
                max = current;
            }
        }

        //2.max开方
        double sqrt = Math.sqrt(max);

        //输出格式：
        //在一行中输出谱半径，四舍五入保留小数点后 2 位。
        System.out.printf("%.2f", sqrt);
    }
}
