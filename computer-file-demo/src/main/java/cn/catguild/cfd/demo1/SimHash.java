package cn.catguild.cfd.demo1;

import cn.catguild.cfd.demo2.Murmur3;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zhi.liu
 * @version V1.0
 * @package cn.catguild.cfd.demo1
 * @description
 * @date Created in 2021-01-10 19:31
 * @copyright Copyright (c) 2021
 * @modified zhi.liu
 */
public class SimHash {

    /**
     * 输入的文本容
     */
    private final String content;

    /**
     * 使用结巴分词
     */
    private final JiebaSegmenter segmenter = new JiebaSegmenter();

    /**
     * SimHash签名 长度
     */
    private final int hashBits = 64;

    public SimHash(String content) {
        this.content = content;
    }

    /**
     * 获取 SimHash签名
     * 分词 -> hash -> 加权 -> 合并 -> 降维
     *
     * @return SimHash签名
     */
    public long simHash(){
        // 1、分词
        String filterContent = content.trim().replaceAll("\\p{Punct}|\\p{Space}", "");
        // 切词
        List<SegToken> lsegStr = segmenter.process(filterContent, JiebaSegmenter.SegMode.SEARCH);

        // 按照词语的hash值，计算simHashWeight(低位对齐)
        Integer[] weight = new Integer[hashBits];
        System.out.println("分词结果："+lsegStr);
        Arrays.fill(weight, 0);
        for (SegToken st : lsegStr) {
            // 2、hash
            long wordHash = Murmur3.hash64(st.word.getBytes());

            System.out.println(wordHash);
            // 3、加权
            for (int i = 0; i < hashBits; i++) {
                if (((wordHash >> i) & 1) == 1) {
                    weight[i] += 1;
                } else {
                    weight[i] -= 1;
                }
            }
        }
        System.out.println("加权之后的结果："+ Arrays.toString(weight));

        // 5、降维
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hashBits; i++) {
            if (weight[i] > 0) {
                sb.append(1);
            } else {
                sb.append(0);
            }
        }

        return new BigInteger(sb.toString(), 2).longValue();
    }

    /**
     * 计算 两个 SimHash 的海明距离
     *
     * @param simHash1
     * @param simHash2
     * @return
     */
    public static int hammingDistance(SimHash simHash1, SimHash simHash2) {
        simHash1.simHash();
        //simHash2.simHash();
        return 0;
    }

    private BigInteger hash() {
        String source = content;
        if (source == null || source.length() == 0) {
            return new BigInteger("0");
        } else {
            char[] sourceArray = source.toCharArray();
            BigInteger x = BigInteger.valueOf(((long) sourceArray[0]) << 7);
            BigInteger m = new BigInteger("1000003");
            BigInteger mask = new BigInteger("2").pow(this.hashBits).subtract(
                    new BigInteger("1"));
            for (char item : sourceArray) {
                BigInteger temp = BigInteger.valueOf((long) item);
                x = x.multiply(m).xor(temp).and(mask);
            }
            x = x.xor(new BigInteger(String.valueOf(source.length())));
            if (x.equals(new BigInteger("-1"))) {
                x = new BigInteger("-2");
            }
            return x;
        }
    }
}
