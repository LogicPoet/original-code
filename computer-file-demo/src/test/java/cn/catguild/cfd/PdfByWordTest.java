package cn.catguild.cfd;

import org.junit.Test;

import static org.junit.Assert.*;

public class PdfByWordTest {

    @Test
    public void pdfOfWord() {
        String wordPath= "C:\\Users\\Liuzhi\\Desktop\\github_project\\original-code\\computer-file-demo\\src\\main\\resources\\基于深度学习的图像本征属性预测方法综述_沙浩.docx";
        String pdfPath = "C:\\Users\\Liuzhi\\Desktop\\github_project\\original-code\\computer-file-demo\\src\\main\\resources\\基于深度学习的图像本征属性预测方法综述_沙浩.pdf";
        PdfByWord.pdfOfWord(pdfPath,wordPath);
    }
}