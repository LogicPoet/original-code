package cn.catguild.cfd;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.SimpleBookmark;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.*;

/**
 * @author liu.zhi
 * @date 2020/12/29 17:08
 */
@Slf4j
public class PdfService {

    private String path;

    public PdfService(String path) {
        this.path = path;
    }

    /**
     * 读取全文
     *
     * @return 全文字符串
     */
    public String getFullText() {
        String result = "";
        try {
            PdfReader reader = new PdfReader(path);
            PdfReaderContentParser parser = new PdfReaderContentParser(reader);
            StringBuilder buff = new StringBuilder();
            TextExtractionStrategy strategy;
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
                buff.append(strategy.getResultantText());
            }
            result = buff.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 解析出目录结构
     *
     * @return 目录List
     */
    public List<String> parseDirStructure() {
        List<String> result = new ArrayList<>();
        try {
            // 获取目录页
            // 按行读取
            PdfReader reader = new PdfReader(path);
            PdfReaderContentParser parser = new PdfReaderContentParser(reader);
            StringBuilder buff = new StringBuilder();
            TextExtractionStrategy strategy;

            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
                buff.append(strategy.getResultantText());
                String resultantText = strategy.getResultantText();
                log.debug(resultantText);
                log.debug(i+"-----------------------------------------------------------");
            }
            String s = buff.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 解析段落内容
     *
     * @return
     */
    public Map<String, String> parseParagraphKeywords() {
        Map<String, String> dirMap = new LinkedHashMap<>();
        //首先判断文件中的是doc/docx
        return dirMap;
    }


    /**
     * 获取书签目录
     *
     * @return
     */
    public List<String> getBookmarkStructure(){
        List<String> result = new ArrayList<>();
        try {
            PdfReader reader = new PdfReader(path);
            List list = SimpleBookmark.getBookmark(reader);
            for (Iterator i = list.iterator(); i.hasNext(); ) {
                showBookmark((Map) i.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static void showBookmark(Map bookmark) {
        System.out.println(bookmark.get("Title"));
        ArrayList kids = (ArrayList) bookmark.get("Kids");
        if (kids == null)
            return;
        for (Iterator i = kids.iterator(); i.hasNext(); ) {
            showBookmark((Map) i.next());
        }
    }
}
