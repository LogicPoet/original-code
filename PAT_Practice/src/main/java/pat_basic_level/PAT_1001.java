package pat_basic_level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <p>Title: PAT_1001</p>
 * <p>description:
 * 1001 害死人不偿命的(3n+1)猜想 (15 分)
 * 卡拉兹(Callatz)猜想：
 * <p>
 * 对任何一个正整数 n，如果它是偶数，那么把它砍掉一半；如果它是奇数，那么把 (3n+1) 砍掉一半。这样一直反复砍下去，最后一定在某一步得到 n=1。卡拉兹在 1950 年的世界数学家大会上公布了这个猜想，传说当时耶鲁大学师生齐动员，拼命想证明这个貌似很傻很天真的命题，结果闹得学生们无心学业，一心只证 (3n+1)，以至于有人说这是一个阴谋，卡拉兹是在蓄意延缓美国数学界教学与科研的进展……
 * <p>
 * 我们今天的题目不是证明卡拉兹猜想，而是对给定的任一不超过 1000 的正整数 n，简单地数一下，需要多少步（砍几下）才能得到 n=1？
 * <p>
 * 输入格式：
 * 每个测试输入包含 1 个测试用例，即给出正整数 n 的值。
 * <p>
 * 输出格式：
 * 输出从 n 计算到 1 需要的步数。
 * <p>
 * 输入样例：
 * 3
 * 输出样例：
 * 5
 *
 * </p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/10/6 19:39 - 19:50
 *
 * 总分15
 *
 * 完成时间：00:11:00
 * 得分15
 **/
public class PAT_1001 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        //输入测试用例，整数n
        int n = Integer.parseInt(input.readLine());
        //从 n 计算到 1 需要的步数
        int step = 0;

        //重复1和2.直到n=1,就结束
        while (1 != n) {
            //1.首先判断奇偶性
            if (n % 2 == 0) {
                //2.1它是偶数，那么把它砍掉一半
                n = n / 2;
                step++;
            } else {
                //2.2它是奇数，那么把 (3n+1) 砍掉一半。
                n = (3 * n + 1) / 2;
                step++;
            }
        }
        //输出从 n 计算到 1 需要的步数。
        System.out.println(step);
    }
}
