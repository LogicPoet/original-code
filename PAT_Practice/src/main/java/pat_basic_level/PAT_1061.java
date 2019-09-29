package pat_basic_level;

import java.util.Scanner;

/**
 * <p>Title: PAT_1061</p>
 * <p>description:
 * 判断题的评判很简单，本题就要求你写个简单的程序帮助老师判题并统计学生们判断题的得分。
 * <p>
 * 输入格式：
 * 输入在第一行给出两个不超过 100 的正整数 N 和 M，分别是学生人数和判断题数量。第二行给出 M 个不超过 5 的正整数，是每道题的满分值。第三行给出每道题对应的正确答案，0 代表“非”，1 代表“是”。随后 N 行，每行给出一个学生的解答。数字间均以空格分隔。
 * <p>
 * 输出格式：
 * 按照输入的顺序输出每个学生的得分，每个分数占一行。
 * <p>
 * 输入样例：
 * 3 6
 * 2 1 3 3 4 5
 * 0 0 1 0 1 1
 * 0 1 1 0 0 1
 * 1 0 1 0 1 0
 * 1 1 0 0 1 1
 * 输出样例：
 * 13
 * 11
 * 12
 *
 * </p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/09/28 11:41
 *
 * 总分15
 *
 * 完成时间：1:30:00
 * 得分12
 **/
public class PAT_1061 {

    public static void main(String[] args) {
        myAnswer();
        success();
    }

    /**
     * 成功的答案
     */
    private static void success() {
        Scanner input = new Scanner(System.in);
        //判断题数量//学生人数
        int N, M, result = 0;

        //第一行给出两个不超过 100 的正整数 N 和 M，分别是学生人数和判断题数量。
        N = input.nextInt();
        M = input.nextInt();

        //分值
        int[] point = new int[M];
        //答案
        int[] answer = new int[M];
        //学生的答案
        int[] test = new int[M];

        //第二行给出 M 个不超过 5 的正整数，是每道题的满分值。
        for (int i = 0; i < M; i++) {
            point[i] = input.nextInt();
        }

        //第三行给出每道题对应的正确答案，0 代表“非”，1 代表“是”。
        for (int i = 0; i < M; i++) {
            answer[i] = input.nextInt();
        }

        //按照输入的顺序输出每个学生的得分，每个分数占一行。
        for (int i = 0; i < N; i++) {
            result = 0;
            for (int j = 0; j < M; j++) {
                //随后 N 行，每行给出一个学生的解答。数字间均以空格分隔。
                test[j] = input.nextInt();
                if (test[j] == answer[j]) {
                    result += point[j];
                }
            }
            System.out.println(result);
        }
    }

    /**
     * 我的答案，完成2/3，完成时间大约1:30:00
     */
    private static void myAnswer() {
        Scanner input = new Scanner(System.in);
        int N, M;
        //第一行给出两个不超过 100 的正整数 N 和 M，分别是学生人数和判断题数量。
        do {
            //学生人数
            N = input.nextInt();
            //判断题数量
            M = input.nextInt();

        } while (N < 0 || N >= 100 || M < 0 || M >= 100);

        //第二行给出 M 个不超过 5 的正整数，是每道题的满分值。
        int[] point = new int[M];
        int temp1 = -1;
        for (int i = 0; i < M; i++) {
            do {
                temp1 = input.nextInt();
            } while (temp1 < 0 || temp1 > 5);
            point[i] = temp1;
        }

        //第三行给出每道题对应的正确答案，0 代表“非”，1 代表“是”。
        int[] answer = new int[M];
        int temp2 = -1;
        for (int i = 0; i < M; i++) {
            do {
                temp2 = input.nextInt();
            } while (temp2 != 0 && temp2 != 1);
            answer[i] = temp2;
        }

        //随后 N 行，每行给出一个学生的解答。数字间均以空格分隔。
        int[][] test = new int[N][M];
        int temp3 = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                do {
                    temp3 = input.nextInt();
                } while (temp3 != 0 && temp3 != 1);
                test[i][j] = temp3;
            }
        }

        //按照输入的顺序输出每个学生的得分，每个分数占一行。
        int result = 0;
        for (int i = 0; i < N; i++) {
            result = 0;
            for (int j = 0; j < M; j++) {
                if (test[i][j] == answer[j]) {
                    result = result + point[j];
                }
            }
            System.out.println(result);
        }
    }

}
