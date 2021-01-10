package cn.catguild.cfd;

import org.junit.Test;

public class WordByPdfTest {

    @Test
    public void wordOfPdf() {
        String wordPath= "C:\\Users\\Liuzhi\\Desktop\\github_project\\original-code\\computer-file-demo\\src\\main\\resources\\南外环路工程深基坑专项施工方案（污水管道）6.22.docx";
        String pdfPath = "C:\\Users\\Liuzhi\\Desktop\\github_project\\original-code\\computer-file-demo\\src\\main\\resources\\南外环路工程深基坑专项施工方案（污水管道）6.22.pdf";
        WordByPdf.wordOfPdf(wordPath,pdfPath);
    }

}