package cn.catguild.cfd;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 创建pdf，文件工具类
 */
public class CreatePdfUtil {

	//使用生成pdf的字符集
	public static final String FONT = "/font/NotoSansCJKsc-Regular.otf";

	/**
	 * 将传入的字符串进行替换，按照用于生成pdf的规则进行替换
	 * 1、首先需要添加头和尾，用于生成pdf
	 * 2、其次需要将html字符串中的 page-break-before:always 全部置换为 break-before: page;
	 * 		为了去除百度富文本编辑框中的分页设置，是文档紧凑。
	 * 3、将字符串中的中文《》、“”、【】等中的前面字符，统一置换一下，将《、“、【统一在后面添加空格
	 *		防止中文的这些字符错位，遮挡后面的文字。
	 * @param htmlString 	html字符串
	 * @return
	 */
	public static String replaceString(String htmlString) {
		//拼接头尾
		htmlString = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
			"<html xmlns=\"http://www.w3.org/1999/xhtml\">" + htmlString + "</html>";
		//将字符串中的page-break-before:always替换为break-before: page;
		htmlString = htmlString.replaceAll("page-break-before:always","break-before: page;");
		//防止中文字符错位，遮挡后面的文字
		htmlString = htmlString.replaceAll("《","《 ");
		htmlString = htmlString.replaceAll("“","“ ");
		htmlString = htmlString.replaceAll("【","【 ");
		return htmlString;
	}

	/**
	 * 使用html生成pdf文件
	 * @param htmlString	html字符串
	 * @param filePath		储存文件路径
	 * @return
	 */
	public static void createPdf(String htmlString, String filePath) throws IOException {
		//将字符串转换为InputStream
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(htmlString.getBytes());
		InputStream inputStream = (InputStream) byteArrayInputStream;
		//创建转换连接
		ConverterProperties props = new ConverterProperties();
		DefaultFontProvider defaultFontProvider = new DefaultFontProvider(false, false, false);
		defaultFontProvider.addFont(FONT);
		props.setFontProvider(defaultFontProvider);
		PdfWriter writer = new PdfWriter(filePath);
		PdfDocument pdf = new PdfDocument(writer);
		pdf.setDefaultPageSize(new PageSize(595.0F, 842.0F));
		//输出至文档
		Document document = HtmlConverter.convertToDocument(inputStream, pdf, props);
		document.close();
		pdf.close();
	}

	/**
	 * 传入字符串，返回字符串中的image_*，并且返回image_在该字符串中的i的下标与后面\n的下标
	 * @param str
	 * @return
	 */
	public static List<Map<String, Map<String,Integer>>> dataParser(String str) {
		Matcher m = Pattern.compile("(?=(image_))").matcher(str);
		List<Integer> pos = new ArrayList<Integer>();
		while (m.find()) {
			pos.add(m.start());
		}
		List<Map<String,Map<String,Integer>>> list = new ArrayList<>();
		pos.forEach(e->{
			Map<String,Map<String,Integer>> mapMap = new HashMap<>();
			Map<String, Integer> map = new HashMap<>();
			Integer index = str.indexOf("\\n",e);
			String image = str.substring(e,index);
			map.put("start",e);
			map.put("end",index);
			mapMap.put(image,map);
			list.add(mapMap);
		});
		return list;
	}

	/**
	 * 传入目录labelOne，labelTwo，给目录进行排序，并输出
	 * @param labelOne
	 * @param labelTwo
	 * @return
	 */
	public static String getDirectories(String labelOne, String labelTwo) {
		int index = 0;
		List<String> list = new ArrayList<>();
		String[] a = labelTwo.split("\n");
		String[] b = labelOne.split("\n");
		for(int i = 0; i < a.length; i++) {
			list.add(a[i]);
		}
		for(int i = 0; i < b.length; i++) {
			//如果包含附录，则不插入
			if (b[i].contains("附录")) {
				index++;
				continue;
			}
			int a1 = b[i].indexOf(" ",0);
			String[] b1 = b[i].substring(0,a1).split("．");
			for(int j = 0; j < list.size(); j++) {
				if(!list.get(j).contains(" ")) {
					continue;
				}
				if (!list.get(j).contains("．")) {
					continue;
				}
				int a2 = list.get(j).indexOf(" ",0);
				String[] b2 = list.get(j).substring(0,a2).split("．");
				if(b1.length == 1) {
					if (Integer.parseInt(b1[0]) < Integer.parseInt(b2[0])) {
						list.add(j,b[i]);
						break;
					}else if(b1[0].equals(b2[0])) {
						list.add(j,b[i]);
						break;
					}else {
						continue;
					}
				}
			}
		}
		boolean result = true;
		//判断labelOne是否包含附录
		if (index > 0) {
			List<String> list1 = new ArrayList<>();
			//取出所有的附录
			for (int i = 0; i < b.length; i++) {
				if (b[i].contains("附录")) {
					list1.add(b[i]);
				}
			}
			//取出所有labelTwo中的附录
			for (int i = 0; i < a.length; i++) {
				if (a[i].contains("附录")) {
					result = false;
					list1.add(a[i]);
				}
			}
			//排序
			Collections.sort(list1);
			//如果labelTwo中没有附录，则直接放在最后
			if (!result) {
				list.addAll(list1);
			} else {
				//将list中的附录移除，然后将labelOne直接添加在最后面
				Iterator iterator = list.iterator();
				while (iterator.hasNext()) {
					if (iterator.next().toString().contains("附录")) {
						iterator.remove();
					}
				}
				list.addAll(list1);
			}

		}
		String string = "";
		for(int i = 0; i < list.size(); i++) {
			string += list.get(i) + "\n";
		}
		return string;
	}

	/**
	 * 设置标题
	 * @param doc
	 * @param strTitle
	 * @return
	 */
	public static void title(XWPFDocument doc, String strTitle) {
		//新建段落
		XWPFParagraph para = doc.createParagraph();
		//新建Run：一个XWPFRun代表具有相同属性的一个区域，一段文本
		XWPFRun run = para.createRun();
		run.setText(strTitle);
		run.setFontFamily("黑体");
		run.setBold(true);
		run.setFontSize(20);
		//居中
		para.setFontAlignment(2);
	}

	/**
	 * 设置一级标题
	 * @param doc
	 * @param oneTitle
	 * @return
	 */
	public static void oneTitle(XWPFDocument doc,String oneTitle) {
		//新建段落
		XWPFParagraph para = doc.createParagraph();
		//新建Run：一个XWPFRun代表具有相同属性的一个区域，一段文本
		XWPFRun run = para.createRun();
		run.setText(oneTitle);
		run.setFontFamily("黑体");
		run.setBold(true);
		run.setFontSize(18);
		//居左
		para.setFontAlignment(1);
	}
	/**
	 * 设置二级标题
	 * @param doc
	 * @param twoTitle
	 * @return
	 */
	public static void twoTitle(XWPFDocument doc,String twoTitle) {
		//新建段落
		XWPFParagraph para = doc.createParagraph();
		//新建Run：一个XWPFRun代表具有相同属性的一个区域，一段文本
		XWPFRun run = para.createRun();
		run.setText(twoTitle);
		run.setFontFamily("黑体");
		run.setBold(true);
		run.setFontSize(16);
		//居左
		para.setFontAlignment(1);
	}
	/**
	 * 设置正文格式
	 * @param doc
	 * @param wordInfo
	 * @return
	 */
	public static void wordInfo(XWPFDocument doc,String wordInfo) {
		//新建段落
		XWPFParagraph para = doc.createParagraph();
		//新建Run：一个XWPFRun代表具有相同属性的一个区域，一段文本
		XWPFRun run = para.createRun();
		run.setText(wordInfo);
		run.setFontFamily("宋体");
		run.setFontSize(12);
		run.setTextPosition(10);
		//首行缩进
		para.setIndentationFirstLine(567);
	}

	/**
	 * 设置正文 三级标题格式
	 *
	 * @param doc
	 * @param wordInfo 内容
	 * @return
	 */
	public static void threeLevelHeadingFormat(XWPFDocument doc,String wordInfo) {
		//新建段落
		XWPFParagraph para = doc.createParagraph();
		//新建Run：一个XWPFRun代表具有相同属性的一个区域，一段文本
		XWPFRun run = para.createRun();
		run.setText(wordInfo);
		run.setFontFamily("宋体");
		run.setFontSize(12);
		//run.setTextPosition(10);

		// 左对齐
		para.setAlignment(ParagraphAlignment.LEFT);
	}

	/**
	 * 设置正文 目录标题格式
	 *
	 * @param doc
	 * @param wordInfo 内容
	 * @return
	 */
	public static void dirTitle(XWPFDocument doc,String wordInfo) {
		//新建段落
		XWPFParagraph para = doc.createParagraph();
		//新建Run：一个XWPFRun代表具有相同属性的一个区域，一段文本
		XWPFRun run = para.createRun();
		run.setText(wordInfo);
		run.setFontFamily("黑体");
		run.setBold(true);
		run.setFontSize(14);
		// 居中
		para.setAlignment(ParagraphAlignment.CENTER);
	}

	/**
	 * 设置seg_1中的内容
	 * @param doc
	 * @param wordInfo
	 * @return
	 */
	public static void wordSegOne(XWPFDocument doc,String wordInfo) {
		//新建段落
		XWPFParagraph para = doc.createParagraph();
		//新建Run：一个XWPFRun代表具有相同属性的一个区域，一段文本
		XWPFRun run = para.createRun();
		run.setText(wordInfo);
		run.setFontFamily("宋体");
		run.setFontSize(12);
		run.setTextPosition(10);
		//居中
		para.setFontAlignment(2);
	}

	/**
	 * 将多个字符串中的换行置换成一个
	 * @param str
	 * @return
	 */
	public static String replaceLineBlanks(String str) {
		String result = "";
		if (str != null) {
			Pattern p = Pattern.compile("(\r?\n(\\s*\r?\n)+)");
			Matcher m = p.matcher(str);
			result = m.replaceAll("\n");
		}
		return result;
	}

	/**
	 * 删除指定路径的文件
	 * @param filePath
	 */
	public static void deleteWord(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
		}
	}

	/**
	 * 生成pdf首页的缩略图
	 * @param inputFile        生成缩略图的pdf的路径
	 * @param outputFile    生成缩略图的放置路径
	 */
	public static void generateBookIamge(String inputFile, String outputFile) {
		org.icepdf.core.pobjects.Document document = new org.icepdf.core.pobjects.Document();

		try {
			float rotation = 0f;
			//缩略图显示倍数，1表示不缩放，0.5表示缩小到50%
			float zoom = 0.5f;
			//document = new org.icepdf.core.pobjects.Document();
			document.setFile(inputFile);
			BufferedImage image = (BufferedImage)document.getPageImage(0, GraphicsRenderingHints.SCREEN,
				Page.BOUNDARY_CROPBOX, rotation, zoom);
			Iterator iter = ImageIO.getImageWritersBySuffix("jpg");
			ImageWriter writer = (ImageWriter)iter.next();
			FileOutputStream out = new FileOutputStream(new File(outputFile));
			ImageOutputStream outImage = ImageIO.createImageOutputStream(out);
			writer.setOutput(outImage);
			writer.write(new IIOImage(image, null, null));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 过滤字符串中创建文件时的非法字符
	 * \/:*?"<>|
	 * @param title
	 * @return
	 */
	public static String getString(String title) {
		String index = "\\,/,:,*,?,\",<,>,|";
		String[] indexs = index.split(",");
		for (int i = 0; i < indexs.length; i++) {
			if (title.contains(indexs[i])) {
				title = title.replaceAll(indexs[i],"");
			}
		}
		return title;
	}

	/**
	 * 将字符串中的第几章，替换为数字
	 * @param str
	 * @return
	 */
	public static String replaceAllString(String str) {
		String[] str1 = str.split("\n");
		for (int i = 0; i < str1.length; i++) {
			String str2 = str1[i];
			if (!str2.contains("第") && !str2.contains("章")) {
				continue;
			}
			String str3 = str2.substring(str2.indexOf("第") + 1,str2.indexOf("章"));
			int a = chineseNumber2Int(str3);
			String str4 = str2.substring(str2.indexOf("第"),str2.indexOf("章") + 1);
			str1[i] = str2.replace(str4,"" + a + "");
		}
		String result = "";
		for (int i = 0; i < str1.length; i++) {
			result += str1[i] + "\n";
		}
		return result;
	}

	/**
	 * 将字符串中的第几节，替换为数字
	 * @param str
	 * @return
	 */
	public static  String replaceAllString2(String str) {
		String[] str1 = str.split("\n");
		for (int i = 0; i < str1.length; i++) {
			String str2 = str1[i];
			if (!str2.contains("第") && !str2.contains("节")) {
				continue;
			}
			String str3 = str2.substring(str2.indexOf("第") + 1,str2.indexOf("节"));
			int a = chineseNumber2Int(str3);
			String str4 = str2.substring(str2.indexOf("第") - 1,str2.indexOf("节") + 1);
			str1[i] = str2.replace(str4,"．" + a + "");
		}
		String result = "";
		for (int i = 0; i < str1.length; i++) {
			result += str1[i] + "\n";
		}
		return result;
	}

	/**
	 * 将汉字替换为数字
	 * @param chineseNumber
	 * @return
	 */
	private static int chineseNumber2Int(String chineseNumber){
		int result = 0;
		int temp = 1;//存放一个单位的数字如：十万
		int count = 0;//判断是否有chArr
		char[] cnArr = new char[]{'一','二','三','四','五','六','七','八','九'};
		char[] chArr = new char[]{'十','百','千','万','亿'};
		for (int i = 0; i < chineseNumber.length(); i++) {
			boolean b = true;//判断是否是chArr
			char c = chineseNumber.charAt(i);
			for (int j = 0; j < cnArr.length; j++) {//非单位，即数字
				if (c == cnArr[j]) {
					if(0 != count){//添加下一个单位之前，先把上一个单位值添加到结果中
						result += temp;
						temp = 1;
						count = 0;
					}
					// 下标+1，就是对应的值
					temp = j + 1;
					b = false;
					break;
				}
			}
			if(b){//单位{'十','百','千','万','亿'}
				for (int j = 0; j < chArr.length; j++) {
					if (c == chArr[j]) {
						switch (j) {
							case 0:
								temp *= 10;
								break;
							case 1:
								temp *= 100;
								break;
							case 2:
								temp *= 1000;
								break;
							case 3:
								temp *= 10000;
								break;
							case 4:
								temp *= 100000000;
								break;
							default:
								break;
						}
						count++;
					}
				}
			}
			if (i == chineseNumber.length() - 1) {//遍历到最后一个字符
				result += temp;
			}
		}
		return result;
	}

	/**
	 * 解析废止时间
	 * @param str
	 * @return
	 */
	public static String getAbandTime(String str) {
		String regEx="[^0-9]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		String str1 = m.replaceAll("-").trim();
		Pattern p1 = Pattern.compile("(-+)");
		Matcher m1 = p1.matcher(str1);
		String result = m1.replaceAll("-");
		String splitter = "-";
		String regex = "^" + splitter + "*|" + splitter + "*$";
		return result.replaceAll(regex, "");
	}

}
