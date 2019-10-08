package pat_basic_level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <p>Title: PAT_1004</p>
 * <p>description:
 * 1004 成绩排名 (20 分)
 * 读入 n（>0）名学生的姓名、学号、成绩，分别输出成绩最高和成绩最低学生的姓名和学号。
 * <p>
 * 输入格式：
 * 每个测试输入包含 1 个测试用例，格式为
 * <p>
 * 第 1 行：正整数 n
 * 第 2 行：第 1 个学生的姓名 学号 成绩
 * 第 3 行：第 2 个学生的姓名 学号 成绩
 * ... ... ...
 * 第 n+1 行：第 n 个学生的姓名 学号 成绩
 * 其中姓名和学号均为不超过 10 个字符的字符串，成绩为 0 到 100 之间的一个整数，这里保证在一组测试用例中没有两个学生的成绩是相同的。
 * <p>
 * 输出格式：
 * 对每个测试用例输出 2 行，第 1 行是成绩最高学生的姓名和学号，第 2 行是成绩最低学生的姓名和学号，字符串间有 1 空格。
 * <p>
 * 输入样例：
 * 3
 * Joe Math990112 89
 * Mike CS991301 100
 * Mary EE990830 95
 * 输出样例：
 * Mike CS991301
 * Joe Math990112
 *
 * </p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/10/8 20:05
 **/
public class PAT_1004 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        //学生人数n
        int n = Integer.parseInt(input.readLine());
        //保存最高、最低的数组下标
        int low = 0;
        int lowCore = 0;
        int top = 0;
        int topCore = 0;
        //找出最高最低的分数
        String[] stucore = new String[n];
        stucore[0] = input.readLine();
        String[] s = stucore[0].split(" ");
        //设定初始最高、最低分
        lowCore = topCore = Integer.parseInt(s[2]);

        for (int i = 1; i < n; i++) {
            //接收输入用例个d qq
            stucore[i] = input.readLine();
            String[] str = stucore[i].split(" ");
            int tempCore = Integer.parseInt(str[2]);
            //更新最高分数
            if (tempCore > topCore) {
                topCore = tempCore;
                top = i;
            }
            //更新最低分数
            if (tempCore < lowCore) {
                lowCore = tempCore;
                low = i;
            }
        }

        //获取最高最低分数的学生信息
        String[] st = stucore[top].split(" ");
        String[] sl = stucore[low].split(" ");

        System.out.println(st[0] + " " + st[1]);
        System.out.println(sl[0] + " " + sl[1]);
    }
}