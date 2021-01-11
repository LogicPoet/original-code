package cn.catguild.cfd.demo2;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class SimhashTest {

    @Test
    public void test() throws IOException {
        List<String> ls = FileUtils.readLines(new File("D:\\Document\\Programme\\Java\\project\\original-code\\computer-file-demo\\src\\main\\resources\\test.txt"));
        Simhash simhash = new Simhash(4, 3);
        for (String content : ls) {
            Long simhashVal = simhash.calSimhash(content);
            System.out.println(Long.toBinaryString(simhashVal));
            System.out.println(simhash.isDuplicate(content));
            simhash.store(simhashVal, content);
        }
    }

}