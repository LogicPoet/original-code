package pat_basic_level;

import java.util.Scanner;

/**
 * <p>Title: PAT_1046</p>
 * <p>description:
 * 1046 划拳 (15 分)
 * 划拳是古老中国酒文化的一个有趣的组成部分。酒桌上两人划拳的方法为：每人口中喊出一个数字，同时用手比划出一个数字。如果谁比划出的数字正好等于两人喊出的数字之和，谁就赢了，输家罚一杯酒。两人同赢或两人同输则继续下一轮，直到唯一的赢家出现。
 * <p>
 * 下面给出甲、乙两人的划拳记录，请你统计他们最后分别喝了多少杯酒。
 * <p>
 * 输入格式：
 * 输入第一行先给出一个正整数 N（≤100），随后 N 行，每行给出一轮划拳的记录，格式为：
 * <p>
 * 甲喊 甲划 乙喊 乙划
 * 其中喊是喊出的数字，划是划出的数字，均为不超过 100 的正整数（两只手一起划）。
 * <p>
 * 输出格式：
 * 在一行中先后输出甲、乙两人喝酒的杯数，其间以一个空格分隔。
 * <p>
 * 输入样例：
 * 5
 * 8 10 9 12
 * 5 10 5 10
 * 3 8 5 12
 * 12 18 1 13
 * 4 16 12 15
 * 输出样例：
 * 1 2
 * </p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/09/29 21:28
 *
 *
 * 总分15
 *
 * 完成时间：00:13:45
 * 得分15
 **/
public class PAT_1046 {
    public static void main(String[] args) {
        //甲喝酒的杯数
        int jia = 0;
        //乙喝酒的杯数
        int yi = 0;
        //甲、乙喊数和
        int sum = 0;
        //游戏场数
        int N = 0;
        //1.输入
        Scanner input = new Scanner(System.in);
        N = input.nextInt();
        int[][] test = new int[N][4];

        for (int i = 0; i < N; i++) {
            //2.每场游戏的输入
            for (int j = 0; j < 4; j++) {
                test[i][j] = input.nextInt();
            }

            //3.计算喊数和
            sum = test[i][0] + test[i][2];

            //4.根据规则判断胜败
            if (test[i][1] == sum && test[i][3] != sum) {
                //甲胜乙喝酒
                yi++;
            }
            if (test[i][1] != sum && test[i][3] == sum) {
                //乙胜甲喝酒
                jia++;
            }
        }

        //5.输出
        System.out.println(jia + " " + yi);
    }
}
