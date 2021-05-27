package cn.catguild.cfd;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liu.zhi
 * @date 2021/3/17 15:27
 */
public class DemoTest {

    static String file1 = "C:\\Users\\Liuzhi\\Desktop\\github_project\\original-code\\computer-file-demo\\src\\main\\resources\\list1.txt";
    static String file2 = "C:\\Users\\Liuzhi\\Desktop\\github_project\\original-code\\computer-file-demo\\src\\main\\resources\\list2.txt";

    static String file3 = "C:\\Users\\Liuzhi\\Desktop\\github_project\\original-code\\computer-file-demo\\src\\main\\resources\\proejct1.txt";
    static String file4 = "C:\\Users\\Liuzhi\\Desktop\\github_project\\original-code\\computer-file-demo\\src\\main\\resources\\proejct2.txt";
    public static void main(String[] args) throws IOException {
        List<String> bpms  = FileUtils.readLines(new File(file1));
        List<String> gcop  = FileUtils.readLines(new File(file2));
        int size = 0;
        for (String s : gcop) {
            if (bpms.contains(s)){
                System.out.println(s);
                size ++;
            }
        }
        System.out.println("共用的："+size);

        int size2 = 0;
        System.out.println("bpms有，gcop没有的");
        for (String s : bpms) {
            if (!gcop.contains(s)){
                System.out.println(s);
                size2 ++;
            }
        }
        System.out.println(size2);

        int size3 = 0;
        System.out.println("gcop有，bpms没有的");
        for (String s : gcop) {
            if (!bpms.contains(s)){
                System.out.println(s);
                size3 ++;
            }
        }
        System.out.println(size3);

        int size4 = 0;
        List<String> version  = FileUtils.readLines(new File(file3));
        List<String> project  = FileUtils.readLines(new File(file4));
        for (String s : project) {
            if (!version.contains(s)){
                System.out.println(s);
                size4 ++;
            }
        }
        System.out.println(size4);
    }
}
