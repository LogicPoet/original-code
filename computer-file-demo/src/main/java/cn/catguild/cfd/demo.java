package cn.catguild.cfd;

import com.huaban.analysis.jieba.JiebaSegmenter;

/**
 * @author liu.zhi
 * @date 2021/1/5 17:39
 */
public class demo {
    public static void main(String[] args) {
        //String content="孩子上了幼儿园 安全防拐教育要做好";
        //int topN=5;
        //TFIDFAnalyzer tfidfAnalyzer=new TFIDFAnalyzer();
        //List<Keyword> list=tfidfAnalyzer.analyze(content,topN);
        //for(Keyword word:list)
        //    System.out.println(word.getName()+":"+word.getTfidfvalue()+",");
        //// 防拐:0.1992,幼儿园:0.1434,做好:0.1065,教育:0.0946,安全:0.0924

        JiebaSegmenter segmenter = new JiebaSegmenter();
        String[] sentences =
                new String[] {"这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱Python和C++。", "我不喜欢日本和服。", "雷猴回归人间。",
                        "工信处女干事每月经过下属科室都要亲口交代24口交换机等技术性器件的安装工作", "结果婚的和尚未结过婚的"};
        for (String sentence : sentences) {
            System.out.println(segmenter.process(sentence, JiebaSegmenter.SegMode.INDEX).toString());
        }
    }
}
