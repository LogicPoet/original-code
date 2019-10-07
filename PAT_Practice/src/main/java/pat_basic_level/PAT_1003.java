package pat_basic_level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title: PAT_1003</p>
 * <p>description:
 * 1003 我要通过！ (20 分)
 * “答案正确”是自动判题系统给出的最令人欢喜的回复。本题属于 PAT 的“答案正确”大派送 —— 只要读入的字符串满足下列条件，系统就输出“答案正确”，否则输出“答案错误”。
 * <p>
 * 得到“答案正确”的条件是：
 * <p>
 * 字符串中必须仅有 P、 A、 T这三种字符，不可以包含其它字符；
 * 任意形如 xPATx 的字符串都可以获得“答案正确”，其中 x 或者是空字符串，或者是仅由字母 A 组成的字符串；
 * 如果 aPbTc 是正确的，那么 aPbATca 也是正确的，其中 a、 b、 c 均或者是空字符串，或者是仅由字母 A 组成的字符串。
 * 现在就请你为 PAT 写一个自动裁判程序，判定哪些字符串是可以获得“答案正确”的。
 * <p>
 * 输入格式：
 * 每个测试输入包含 1 个测试用例。第 1 行给出一个正整数 n (<10)，是需要检测的字符串个数。接下来每个字符串占一行，字符串长度不超过 100，且不包含空格。
 * <p>
 * 输出格式：
 * 每个字符串的检测结果占一行，如果该字符串可以获得“答案正确”，则输出 YES，否则输出 NO。
 * <p>
 * 输入样例：
 * 8
 * PAT
 * PAAT
 * AAPATAA
 * AAPAATAAAA
 * xPATx
 * PT
 * Whatever
 * APAAATAA
 * 输出样例：
 * YES
 * YES
 * YES
 * YES
 * NO
 * NO
 * NO
 * NO
 * </p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/10/6 20:46
 **/
public class PAT_1003 {

    public static void main(String[] args) throws IOException {
        //接收测试用例
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        //需要判断的字符串个数
        int n = Integer.parseInt(input.readLine());

        //P是否存在的标志
        int hasp;
        //以P-T为界，P左边A的个数、PT之间的A个数、T右边的A个数
        int la;
        int ra;
        int ma;

        //判断是否成功标志
        int flag;

        //遍历所有输出
        while (n-- > 0) {
            //标志初始化
            hasp = 0;
            la = 0;
            ra = 0;
            ma = 0;
            flag = -1;

            //测试字符串
            String str = input.readLine();
            //遍历该字符串
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                //判断规则
                //1.只能有PAT三中字符
                if (c == 'P' || c == 'A' || c == 'T') {
                    //判断P是否存在
                    if (c == 'P') {
                        hasp = 1;
                    }

                    if (c == 'A') {
                        //判断该字符的位置
                        if (hasp != 1) {
                            //没有找到P，该A在P左边
                            la++;
                        } else {
                            //找到了P，该A在P的右边
                            //A在T的右边的情形，在找到T的时候会一次性计数之后直接结束当前循环，
                            //所以此处不用考虑A在T右边的情况
                            ma++;
                        }
                    }

                    if (c == 'T') {
                        //一个正确的字符串，T之后的字符，必然都是A,
                        for (int j = i + 1; j < str.length(); j++) {
                            c = str.charAt(j);
                            if (c == 'A') {
                                // 此时的A必然都是在T的右边的
                                ra++;
                            } else {
                                //如果该字符不是A，那么该字符就不符合了
                                flag = 0;
                                break;
                            }
                        }

                        //中间的A数量要大于零
                        if (ma < 1) {
                            flag = 0;
                        }

                        if (flag == -1) {
                            //左边的A数量*中间的A数量=右边的A数量
                            if (la * ma == ra) {
                                flag = 1;
                            }
                        }

                        // 整个字符串已经遍历完成，直接跳出
                        break;
                    }
                } else {
                    flag = 0;
                }
            }

            //输出最终结果
            System.out.println(flag == 1 ? "YES" : "NO");
        }
    }
}
