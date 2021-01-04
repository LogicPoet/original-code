package cn.catguild.cfd;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class PdfServiceTest {

    String path = "C:\\Users\\Liuzhi\\Desktop\\github_project\\original-code\\computer-file-demo\\src\\main\\resources\\中华人民共和国标准施工招标文件（2007年版）.pdf";

    @Test
    public void getFullText() {
        PdfService pdfService = new PdfService(path);
        String fullText = pdfService.getFullText();
        log.debug("文档内容：{}",fullText);
    }

    @Test
    public void parseDirStructure() {
        PdfService pdfService = new PdfService(path);
        List<String> list = pdfService.parseDirStructure();
        //log.debug("文档目录结构：{}",list);
    }

    @Test
    public void parseParagraphKeywords() {
    }

    @Test
    public void getBookmarkStructure() {
        PdfService pdfService = new PdfService(path);
        List<String> list = pdfService.getBookmarkStructure();
        log.debug("文档书签目录结构：{}",list);
    }

    @Test
    public void test() throws IOException {
        // 分行解离
        PdfReader reader = new PdfReader(path);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        TextExtractionStrategy strategy = parser.processContent(5, new SimpleTextExtractionStrategy());
        String resultantText = strategy.getResultantText();
        String[] split = resultantText.split("\n");
        List<String> line = new LinkedList<>(Arrays.asList(split));
        log.debug("分离行：{}",line);
        log.debug("-----------------------------------------------------------");
    }
}