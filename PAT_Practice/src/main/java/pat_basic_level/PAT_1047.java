package pat_basic_level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * <p>Title: PAT_1047</p>
 * <p>description:
 * 1047 编程团体赛 (20 分)
 * 编程团体赛的规则为：每个参赛队由若干队员组成；所有队员独立比赛；参赛队的成绩为所有队员的成绩和；成绩最高的队获胜。
 * <p>
 * 现给定所有队员的比赛成绩，请你编写程序找出冠军队。
 * <p>
 * 输入格式：
 * 输入第一行给出一个正整数 N（≤10
 * ​4
 * ​​ ），即所有参赛队员总数。随后 N 行，每行给出一位队员的成绩，格式为：队伍编号-队员编号 成绩，其中队伍编号为 1 到 1000 的正整数，队员编号为 1 到 10 的正整数，成绩为 0 到 100 的整数。
 * <p>
 * 输出格式：
 * 在一行中输出冠军队的编号和总成绩，其间以一个空格分隔。注意：题目保证冠军队是唯一的。
 * <p>
 * 输入样例：
 * 6
 * 3-10 99
 * 11-5 87
 * 102-1 0
 * 102-3 100
 * 11-9 89
 * 3-2 61
 * 输出样例：
 * 11 176
 * </p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/09/30 20:44
 *
 * <p>
 * 总分20
 * <p>
 * 完成时间：01:13:45
 * 得分14
 **/
public class PAT_1047 {

    public static void main(String[] args) throws IOException {
        //myAnswer();
        successAnswer();
    }

    private static void successAnswer() throws IOException {
        //准备流输入
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        //读入第一行，总参数人数
        int n = Integer.parseInt(input.readLine());
        //准备存储参数人员的成绩
        int[] team = new int[1001];
        //冠军队伍的队伍编号
        int maxIndex = 0;
        //遍历所有人
        for (int i = 0; i < n; i++) {
            //截取出队伍编号-队员编号  split("[ -]")正则表达式截取空格和-
            String[] in = input.readLine().split("[ -]");
            //队伍编号=数组下标
            int temp = Integer.parseInt(in[0]);
            //队员分数=数组内容
            team[temp] += Integer.parseInt(in[2]);

            //最高分数和最新的分数
            if (team[maxIndex] < team[temp]) {
                maxIndex = temp;
            }
        }
        System.out.println(maxIndex + " " + team[maxIndex]);
    }


    private static void myAnswer() {
        Scanner input = new Scanner(System.in);
        //N:参赛队员总数

        //1.输入数据
        //6 队员个数=输入行数
        //3-10 99 队伍编号-队员队号 分数

        //队员个数
        int N = 0;
        //输入的存储格式用二维数组
        int[][] test = new int[10][2];
        //接收键盘输入
        N = input.nextInt();
        //用来判断队伍是否存在的标志
        boolean flag = true;
        //标记存储位置
        int index = 0;
        //冠军队伍的下标
        int champion = 0;

        String split;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            //split = (input.next()).split("-");
            //split = (input.next()).split("-");

            split = mySplit(input.next(), "-");


            //队伍名
            int number = Integer.parseInt(split);
            //分数
            int core = input.nextInt();

            //存储,遍历数组
            for (int j = 0; j < test.length && j < index; j++) {
                sum++;
                //1.要输入的队伍已存在
                if (test[j][0] == number) {
                    //更新该队伍的总分数
                    test[j][1] += core;
                    //判断更新的数据是否大于原来的最大值
                    if (test[j][1] > test[champion][1]) {
                        champion = j;

                    }
                    flag = false;
                    break;
                }
            }
            if (flag) {
                //2.要输入的队伍不存在
                test[index][0] = number;
                test[index][1] = core;
                if (test[index][1] > test[champion][1]) {
                    champion = index;
                }
                index++;
                //数组扩容
                if (index == test.length) {
                    test = Arrays.copyOf(test, test.length * 2);
                }
            }
        }

        //输出结果
        System.out.println(test[champion][0] + " " + test[champion][1]);

        //判断输入数组正确性
        //System.out.println("参数总人数：" + N);
        //System.out.println("参数队员分数：");
        //for (int i = 0; i < test.length; i++) {
        //    if (test[i][0] == 0) {
        //        break;
        //    }
        //    System.out.println(Arrays.toString(test[i]));
        //}
        //System.out.println("比较最大值的次数：" + sum);
        //System.out.println("数组长度：" + test.length);
    }


    /**
     * 字符串切割
     *
     * @param str
     * @param delim
     * @return
     */
    public static String mySplit(String str, String delim) {
        int indexOf = str.indexOf(delim);
        return str.substring(0, indexOf);
    }

}
