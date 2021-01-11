//package cn.catguild.cfd;
//
//import com.aspose.pdf.Document;
//import com.aspose.pdf.License;
//
//import java.io.*;
//
///**
// * word转pdf
// */
//public class PdfByWord {
//
//    /**
//     * wpdf转ord
//     *
//     * @param pdfPath  资源pdf路径
//     * @param wordPath 生成的word路径
//     */
//    public static void pdfOfWord(String pdfPath, String wordPath) {
//        //if (!judgeLicense()) {
//        //    System.out.println("license错误");
//        //    return;
//        //}
//        if (!getpdfLicense()) {
//            System.out.println("license错误");
//            return;
//        }
//        File file = new File(wordPath);
//        FileOutputStream os = null;
//        Document doc;
//        try {
//            os = new FileOutputStream(file);
//            doc = new Document(pdfPath);
//            new com.aspose.pdf.Document(pdfPath);
//            //FileFormatInfo fileFormatInfo = FileFormatUtil.detectFileFormat(pdfPath);
//            //int loadFormat = fileFormatInfo.getLoadFormat();
//            //System.out.println(loadFormat);
//            //switch (loadFormat) {
//            //    case LoadFormat.DOC:
//            //        System.out.println("\tMicrosoft Word 97-2003 document.");
//            //        break;
//            //    case LoadFormat.DOT:
//            //        System.out.println("\tMicrosoft Word 97-2003 template.");
//            //        break;
//            //    case LoadFormat.DOCX:
//            //        System.out.println("\tOffice Open XML WordprocessingML Macro-Free Document.");
//            //        break;
//            //    case LoadFormat.DOCM:
//            //        System.out.println("\tOffice Open XML WordprocessingML Macro-Enabled Document.");
//            //        break;
//            //    case LoadFormat.DOTX:
//            //        System.out.println("\tOffice Open XML WordprocessingML Macro-Free Template.");
//            //        break;
//            //    case LoadFormat.DOTM:
//            //        System.out.println("\tOffice Open XML WordprocessingML Macro-Enabled Template.");
//            //        break;
//            //    case LoadFormat.FLAT_OPC:
//            //        System.out.println("\tFlat OPC document.");
//            //        break;
//            //    case LoadFormat.RTF:
//            //        System.out.println("\tRTF format.");
//            //        break;
//            //    case LoadFormat.WORD_ML:
//            //        System.out.println("\tMicrosoft Word 2003 WordprocessingML format.");
//            //        break;
//            //    case LoadFormat.HTML:
//            //        System.out.println("\tHTML format.");
//            //        break;
//            //    case LoadFormat.MHTML:
//            //        System.out.println("\tMHTML (Web archive) format.");
//            //        break;
//            //    case LoadFormat.ODT:
//            //        System.out.println("\tOpenDocument Text.");
//            //        break;
//            //    case LoadFormat.OTT:
//            //        System.out.println("\tOpenDocument Text Template.");
//            //        break;
//            //    case LoadFormat.DOC_PRE_WORD_60:
//            //        System.out.println("\tMS Word 6 or Word 95 format.");
//            //        break;
//            //    case LoadFormat.UNKNOWN:
//            //    default:
//            //        System.out.println("\tUnknown format.");
//            //        break;
//            //}
//            doc.save(os, com.aspose.words.SaveFormat.DOCX);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                assert os != null;
//                os.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * 该校验可以去除word转pdf之后，上面的水印
//     *
//     * @return
//     */
//    //校验license
//    private static boolean judgeLicense() {
//        boolean result = false;
//        try {
//            InputStream is = PdfByWord.class.getClassLoader().getResourceAsStream("license2.xml");
//            License aposeLic = new License();
//            aposeLic.setLicense(is);
//            result = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    public static boolean getpdfLicense() {
//        boolean result2 = false;
//        try {
//            String license = "<License>\n"
//                    + "  <Data>\n"
//                    + "    <Products>\n"
//                    + "      <Product>Aspose.Total for Java</Product>\n"
//                    + "      <Product>Aspose.Words for Java</Product>\n"
//                    + "    </Products>\n"
//                    + "    <EditionType>Enterprise</EditionType>\n"
//                    + "    <SubscriptionExpiry>20991231</SubscriptionExpiry>\n"
//                    + "    <LicenseExpiry>20991231</LicenseExpiry>\n"
//                    + "    <SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber>\n"
//                    + "  </Data>\n"
//                    + "  <Signature>111</Signature>\n"
//                    + "</License>";
//            InputStream is = new ByteArrayInputStream(
//                    license.getBytes("UTF-8"));
//            License asposeLic2 = new License();
//            asposeLic2.setLicense(is);
//            result2 = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result2;
//    }
//}
