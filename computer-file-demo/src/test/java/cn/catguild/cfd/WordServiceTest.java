package cn.catguild.cfd;

import cn.catguild.cfd.entity.DirContent;
import cn.catguild.cfd.node.ForestNode;
import cn.catguild.cfd.node.ForestNodeMerger;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.Test;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
public class WordServiceTest {

    String pathTest = "C:\\Users\\Liuzhi\\Desktop\\github_project\\original-code\\computer-file-demo\\src\\main\\resources\\南外环路工程深基坑专项施工方案（污水管道）6.22.docx";

    @Test
    public void fileTest() {
        File file = new File(pathTest);
        String absolutePath = file.getAbsolutePath();
        log.debug("文件绝对路径为：{}",absolutePath);
        boolean exists = file.exists();
        log.debug(exists ? "文件存在" : "文件不存在");
    }

    @Test
    public void parseDirStructureTest() {
        WordService wordService = new WordService(pathTest);
        List<String> s = wordService.parseDirStructure();
        log.debug("文章目录为：{}",s);

        FormatContent fc = new FormatContent();
        Map<String, String> map = fc.formatDir(s);
        log.debug("文章目录格式化后：{}",map);
    }

    @Test
    public void parseParagraphKeywordsTest() {
        WordService wordService = new WordService(pathTest);
        Map<String, String> s = wordService.parseParagraphKeywords();
        //log.debug("段落关键字：{}",s);
        log.debug("结果：{}",s);
        //List<ForestNode> merge = ForestNodeMerger.merge(s);
    }

    @Test
    public void paragraphTest() throws IOException {
        OPCPackage opcPackage = XWPFDocument.openPackage(pathTest);
        XWPFDocument xwpfDocument = new XWPFDocument(opcPackage);
        List<XWPFParagraph> paragraphs = xwpfDocument.getParagraphs();
        XWPFParagraph paragraph = paragraphs.get(31);

        log.debug("getText:{}",paragraph.getText());
        log.debug("getCTP:{}", paragraph.getCTP());
        log.debug("getRuns:{}",paragraph.getRuns());
        log.debug("getIRuns:{}",paragraph.getIRuns());
        log.debug("isEmpty:{}",paragraph.isEmpty());
        log.debug("getDocument:{}",paragraph.getDocument());
        log.debug("getStyleID:{}",paragraph.getStyleID());
        log.debug("getNumID:{}",paragraph.getNumID());
        log.debug("getNumIlvl:{}",paragraph.getNumIlvl());
        log.debug("getNumFmt:{}",paragraph.getNumFmt());
        log.debug("getNumLevelText:{}",paragraph.getNumLevelText());
        log.debug("getNumStartOverride:{}",paragraph.getNumStartOverride());
        log.debug("getParagraphText:{}",paragraph.getParagraphText());
        log.debug("getPictureText:{}",paragraph.getPictureText());
        log.debug("getFootnoteText:{}",paragraph.getFootnoteText());
        log.debug("getAlignment:{}",paragraph.getAlignment());
        log.debug("getFontAlignment:{}",paragraph.getFontAlignment());
        log.debug("getVerticalAlignment:{}",paragraph.getVerticalAlignment());
        log.debug("getBorderTop:{}",paragraph.getBorderTop());
        log.debug("getBorderBottom:{}",paragraph.getBorderBottom());
        log.debug("getBorderLeft:{}",paragraph.getBorderLeft());
        log.debug("getBorderRight:{}",paragraph.getBorderRight());
        log.debug("getBorderBetween:{}",paragraph.getBorderBetween());
        log.debug("isPageBreak:{}",paragraph.isPageBreak());
        log.debug("getSpacingAfter:{}",paragraph.getSpacingAfter());
        log.debug("getSpacingAfterLines:{}",paragraph.getSpacingAfterLines());
        log.debug("getSpacingBefore:{}",paragraph.getSpacingBefore());
        log.debug("getSpacingBeforeLines:{}",paragraph.getSpacingBeforeLines());
        log.debug("getSpacingLineRule:{}",paragraph.getSpacingLineRule());
        log.debug("getSpacingBetween:{}",paragraph.getSpacingBetween());
        log.debug("getIndentationLeft:{}",paragraph.getIndentationLeft());
        log.debug("getIndentationRight:{}",paragraph.getIndentationRight());
        log.debug("getIndentationHanging:{}",paragraph.getIndentationHanging());
        log.debug("getIndentationFirstLine:{}",paragraph.getIndentationFirstLine());
        log.debug("getIndentFromLeft:{}",paragraph.getIndentFromLeft());
        log.debug("getIndentFromRight:{}",paragraph.getIndentFromRight());
        log.debug("getFirstLineIndent:{}",paragraph.getFirstLineIndent());
        log.debug("isWordWrapped:{}",paragraph.isWordWrapped());
        log.debug("isWordWrap:{}",paragraph.isWordWrap());
        log.debug("getStyle:{}",paragraph.getStyle());
        log.debug("createRun:{}",paragraph.createRun());
        //log.debug("insertNewRun:{}",paragraph.insertNewRun());
        //log.debug("searchText:{}",paragraph.searchText());
        log.debug("getText:{}",paragraph.getText());
        //log.debug("removeRun:{}",paragraph.removeRun());
        log.debug("getElementType:{}",paragraph.getElementType());
        log.debug("getBody:{}",paragraph.getBody());
        log.debug("getPart:{}",paragraph.getPart());
        log.debug("getPartType:{}",paragraph.getPartType());
        //log.debug("addRun:{}",paragraph.addRun());
        //log.debug("getRun:{}",paragraph.getRun());
    }


}