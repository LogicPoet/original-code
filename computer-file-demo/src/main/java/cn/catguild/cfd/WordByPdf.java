package cn.catguild.cfd;

import com.aspose.words.Document;
import com.aspose.words.License;

import java.io.*;
import java.net.URL;

/**
 * word转pdf
 */
public class WordByPdf {

	/**
	 * word转pdf
	 *
	 * @param wordPath word路径
	 * @param pdfPath 生成的pdf路径
	 */
	public static void wordOfPdf(String wordPath, String pdfPath) {
		if (!judgeLicense()) {
			System.out.println("license错误");
			return;
		}
		File file = new File(pdfPath);
		FileOutputStream os = null;
		Document doc;
		try {
			os = new FileOutputStream(file);
			doc = new Document(wordPath);
			doc.save(os, com.aspose.words.SaveFormat.PDF);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				assert os != null;
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 该校验可以去除word转pdf之后，上面的水印
	 * @return
	 */
	//校验license
	private static boolean judgeLicense() {
		boolean result = false;
		try {
			InputStream is = WordByPdf.class.getClassLoader().getResourceAsStream("license.xml");
			License aposeLic = new License();
			aposeLic.setLicense(is);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
