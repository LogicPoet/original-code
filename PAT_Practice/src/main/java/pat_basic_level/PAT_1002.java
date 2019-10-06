package pat_basic_level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <p>Title: PAT_1002</p>
 * <p>description:
 * 1002 写出这个数 (20 分)
 * 读入一个正整数 n，计算其各位数字之和，用汉语拼音写出和的每一位数字。
 * <p>
 * 输入格式：
 * 每个测试输入包含 1 个测试用例，即给出自然数 n 的值。这里保证 n 小于 10的100次方。
 * <p>
 * 输出格式：
 * 在一行内输出 n 的各位数字之和的每一位，拼音数字间有 1 空格，但一行中最后一个拼音数字后没有空格。
 * <p>
 * 输入样例：
 * 1234567890987654321123456789
 * 输出样例：
 * yi san wu
 * </p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/10/6 19:56 -20:36
 *
 * 总分20
 *
 * 完成时间：00:30:00
 * 得分20
 **/
public class PAT_1002 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        //接收输入
        String str = input.readLine();
        //输入样例：
        //1234567890987654321123456789
        //1.计算这行数字的和
        //接收积和
        int sum = 0;
        //1.1.将这个字符串转化为字符数组
        char[] chars = str.toCharArray();
        //1.2.遍历这个数组并积和
        for (char c:chars) {
            //字符转数字,然后积和
            sum += c-'0';
        }

        //2.用拼音读出这个和
        //最终输出字符
        StringBuilder stringBuilder=new StringBuilder();
        //2.1获得这个和的位数
        String s = String.valueOf(sum);
        char[] chars1 = s.toCharArray();
        //2.2按位比对拼音并输出
        for (int i = 0; i < chars1.length; i++) {
            switch (chars1[i]) {
                case '0':
                    stringBuilder.append("ling");
                    break;
                case '1':
                    stringBuilder.append("yi");
                    break;
                case '2':
                    stringBuilder.append("er");
                    break;
                case '3':
                    stringBuilder.append("san");
                    break;
                case '4':
                    stringBuilder.append("si");
                    break;
                case '5':
                    stringBuilder.append("wu");
                    break;
                case '6':
                    stringBuilder.append("liu");
                    break;
                case '7':
                    stringBuilder.append("qi");
                    break;
                case '8':
                    stringBuilder.append("ba");
                    break;
                case '9':
                    stringBuilder.append("jiu");
                    break;
                default:
                    break;
            }
            //消除最后一个空格
            if (i < chars1.length - 1) {
                stringBuilder.append(" ");
            }
        }
        // 输出最终字符串
        System.out.println(stringBuilder.toString());
    }
}
