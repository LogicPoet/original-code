package cn.catguild.cfd;

import cn.catguild.cfd.entity.DirContent;
import cn.catguild.cfd.node.ForestNode;
import com.aspose.words.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.*;
import java.util.List;

/**
 * 文档操作
 *
 * @author liu.zhi
 * @date 2020/12/28 11:03
 */
@Slf4j
public class WordService {

    private String path;

    public WordService(String path) {
        this.path = path;
    }

    /**
     * 读取全文
     *
     * @return 全文字符串
     */
    public String getFullText() {
        String resullt = "";
        //首先判断文件中的是doc/docx
        try {
            if (path.endsWith(".doc")) {
                InputStream is = new FileInputStream(new File(path));
                WordExtractor re = new WordExtractor(is);
                resullt = re.getText();
                re.close();
            } else if (path.endsWith(".docx")) {
                OPCPackage opcPackage = POIXMLDocument.openPackage(path);
                POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                resullt = extractor.getText();
                extractor.close();
            } else {
                System.out.println("此文件不是word文件");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resullt;
    }

    /**
     * 解析出目录结构
     *
     * @return 目录List
     */
    public List<String> parseDirStructure() {
        List<String> result = new ArrayList<>();
        try {
            InputStream is = new FileInputStream(new File(path));
            Document doc = new Document(is);
            FieldCollection fields = doc.getRange().getFields();
            for (Field field : fields) {
                if (field.getType() == FieldType.fromName("FIELD_HYPERLINK")) {
                    FieldHyperlink hyperlink = (FieldHyperlink) field;
                    if (hyperlink.getSubAddress() != null && hyperlink.getSubAddress().startsWith("_Toc")) {
                        Paragraph tocItem = (Paragraph) field.getStart().getAncestor(NodeType.fromName("PARAGRAPH"));
                        String text = tocItem.toString(SaveFormat.fromName("TEXT")).trim();
                        String[] split = text.split("\t");
                        result.add(split[0].trim());
                    }
                }
            }
        } catch (Exception e) {
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
        try {
            if (path.endsWith(".doc")) {
                InputStream is = new FileInputStream(new File(path));
                WordExtractor re = new WordExtractor(is);
                //result = re.getText();
                re.close();
            } else if (path.endsWith(".docx")) {
                OPCPackage opcPackage = XWPFDocument.openPackage(path);
                XWPFDocument xwpfDocument = new XWPFDocument(opcPackage);

                List<XWPFParagraph> paragraphs = xwpfDocument.getParagraphs();

                StringBuilder sb = new StringBuilder();
                String beforeDirName = paragraphs.get(0).getParagraphText();

                //获取目录列表
                List<String> list = parseDirStructure();
                int index = 0;
                for (XWPFParagraph paragraph : paragraphs) {
                    if (null != paragraph.getStyleID() && 10 > Integer.parseInt(paragraph.getStyleID())) {
                        // 标题
                        if (list.get(index).contains(beforeDirName)){
                            dirMap.put(list.get(index), sb.toString());
                        }else {
                            dirMap.put(beforeDirName, sb.toString());
                        }
                        beforeDirName = paragraph.getParagraphText();
                        sb.delete(0, sb.length());
                    } else {
                        sb.append(paragraph.getParagraphText());
                    }
                    //log.debug("-----------------------------------");
                }
                xwpfDocument.close();
            } else {
                log.debug("此文件不是word文件");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dirMap;
    }
    
}
