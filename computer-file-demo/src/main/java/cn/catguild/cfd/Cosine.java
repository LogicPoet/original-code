package cn.catguild.cfd;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author liu.zhi
 * @date 2021/1/5 16:38
 */
public class Cosine {

    public static double getSimilarity(String doc1, String doc2) {
        if (doc1 != null && doc1.trim().length() > 0 && doc2 != null&& doc2.trim().length() > 0) {

            Map<Integer, int[]> AlgorithmMap = new HashMap<Integer, int[]>();

            //将两个字符串中的中文字符以及出现的总数封装到，AlgorithmMap中
            for (int i = 0; i < doc1.length(); i++) {
                char d1 = doc1.charAt(i);
                if(isHanZi(d1)){//标点和数字不处理
                    int charIndex = getGB2312Id(d1);//保存字符对应的GB2312编码
                    if(charIndex != -1){
                        int[] fq = AlgorithmMap.get(charIndex);
                        if(fq != null && fq.length == 2){
                            fq[0]++;//已有该字符，加1
                        }else {
                            fq = new int[2];
                            fq[0] = 1;
                            fq[1] = 0;
                            AlgorithmMap.put(charIndex, fq);//新增字符入map
                        }
                    }
                }
            }

            for (int i = 0; i < doc2.length(); i++) {
                char d2 = doc2.charAt(i);
                if(isHanZi(d2)){
                    int charIndex = getGB2312Id(d2);
                    if(charIndex != -1){
                        int[] fq = AlgorithmMap.get(charIndex);
                        if(fq != null && fq.length == 2){
                            fq[1]++;
                        }else {
                            fq = new int[2];
                            fq[0] = 0;
                            fq[1] = 1;
                            AlgorithmMap.put(charIndex, fq);
                        }
                    }
                }
            }

            Iterator<Integer> iterator = AlgorithmMap.keySet().iterator();
            double sqdoc1 = 0;
            double sqdoc2 = 0;
            double denominator = 0;
            while(iterator.hasNext()){
                int[] c = AlgorithmMap.get(iterator.next());
                denominator += c[0]*c[1];
                sqdoc1 += c[0]*c[0];
                sqdoc2 += c[1]*c[1];
            }

            return denominator / Math.sqrt(sqdoc1*sqdoc2);//余弦计算
        } else {
            throw new NullPointerException(" the Document is null or have not cahrs!!");
        }
    }

    public static boolean isHanZi(char ch) {
        // 判断是否汉字
        return (ch >= 0x4E00 && ch <= 0x9FA5);
        /*if (ch >= 0x4E00 && ch <= 0x9FA5) {//汉字
            return true;
        }else{
            String str = "" + ch;
            boolean isNum = str.matches("[0-9]+");
            return isNum;
        }*/
        /*if(Character.isLetterOrDigit(ch)){
            String str = "" + ch;
            if (str.matches("[0-9a-zA-Z\\u4e00-\\u9fa5]+")){//非乱码
                return true;
            }else return false;
        }else return false;*/
    }

    /**
     * 根据输入的Unicode字符，获取它的GB2312编码或者ascii编码，
     *
     * @param ch 输入的GB2312中文字符或者ASCII字符(128个)
     * @return ch在GB2312中的位置，-1表示该字符不认识
     */
    public static short getGB2312Id(char ch) {
        try {
            byte[] buffer = Character.toString(ch).getBytes("GB2312");
            if (buffer.length != 2) {
                // 正常情况下buffer应该是两个字节，否则说明ch不属于GB2312编码，故返回'?'，此时说明不认识该字符
                return -1;
            }
            int b0 = (int) (buffer[0] & 0x0FF) - 161; // 编码从A1开始，因此减去0xA1=161
            int b1 = (int) (buffer[1] & 0x0FF) - 161;
            return (short) (b0 * 94 + b1);// 第一个字符和最后一个字符没有汉字，因此每个区只收16*6-2=94个汉字
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) {
        String str1="1.目前手机版应用程序和PC端平台均无法打开；（服务器未续费）\n" +
                "2.PC端系统登录，只支持谷歌浏览器，其他浏览器无法登录；？（火狐也行）\n" +
                "3.简化设备软件绑定。目前的绑定方式需要通过身份证、手机号以及验证码，三者缺一不可，过程繁琐，要求绑定方式调整为使用二维码扫描进行绑定及登录；\n";
        String str2="4.简化登录模式。登录方式目前只支持身份证号登录，要求调整为微信或账号绑定手机及项目所含硬件；（及项目所含硬件？没懂是什么登陆方式）\n" +
                "5.医生手机端，要求增加“添加患者绑定设备”的功能；（确实没有，但pc端有这个功能）\n" +
                "6.树形目录化客户及其周边关注关系；\n";
        long start=System.currentTimeMillis();
        double Similarity=Cosine.getSimilarity(str1, str2);
        System.out.println("用时:"+(System.currentTimeMillis()-start));
        System.out.println(Similarity);
    }

}