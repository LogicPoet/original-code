package cn.catguild.cfd;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 格式化读取的word内容
 *
 * @author liu.zhi
 * @date 2020/12/28 15:47
 */
@Slf4j
public class FormatContent {

    /**
     * 格式化目录
     *
     * @param s
     */
    public Map<String, String> formatDir(List<String> s) {
        Map<String, String> result = new HashMap<>();
        for (String s1 : s) {
            String[] split = s1.split(" ");
            String s2 = split[0].replaceAll("\\.", "_");
            if (split.length > 1) {
                result.put(s2, split[1]);
            }
        }
        return result;
    }
}
