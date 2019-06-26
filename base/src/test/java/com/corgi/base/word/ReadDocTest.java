package com.corgi.base.word;

import org.apache.poi.POIXMLProperties;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.*;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @title: ReadDocTest
 * @description:
 * @author: dengmiao
 * @create: 2019-06-17 10:35
 **/
public class ReadDocTest {

    /**
     * https://elim.iteye.com/blog/2049110?utm_source=hacpai.com
     * @throws Exception
     */

    @Test
    public void testReadByDoc() throws Exception {
        ReadDoc rd = new ReadDoc();
        rd.testReadByDoc("e:\\demo\\test.docx");
    }

    /**
     * 通过XWPFWordExtractor访问XWPFDocument的内容
     * @throws Exception
     */
    @Test
    public void testReadByExtractor() throws Exception {
        InputStream is = new FileInputStream("e:\\demo\\test.docx");
        XWPFDocument doc = new XWPFDocument(is);
        XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
        String text = extractor.getText();
        System.out.println(text);
        POIXMLProperties.CoreProperties coreProps = extractor.getCoreProperties();
        this.printCoreProperties(coreProps);
        this.close(is);
    }

    /**
     * 输出CoreProperties信息
     * @param coreProps
     */
    private void printCoreProperties(POIXMLProperties.CoreProperties coreProps) {
        System.out.println(coreProps.getCategory());   //分类
        System.out.println(coreProps.getCreator()); //创建者
        System.out.println(coreProps.getCreated()); //创建时间
        System.out.println(coreProps.getTitle());   //标题
    }

    /**
     * 关闭输入流
     * @param is
     */
    private void close(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过XWPFDocument对内容进行访问。对于XWPF文档而言，用这种方式进行读操作更佳。
     * @throws Exception
     */
    @Test
    public void testReadByDocx() throws Exception {
        InputStream is = new FileInputStream("e:\\demo\\test.docx");
        XWPFDocument doc = new XWPFDocument(is);
        List<XWPFParagraph> paras = doc.getParagraphs();
        for (XWPFParagraph para : paras) {
            //当前段落的属性
//       CTPPr pr = para.getCTP().getPPr();
            System.out.println(para.getText());
        }
        //获取文档中所有的表格
        List<XWPFTable> tables = doc.getTables();
        List<XWPFTableRow> rows;
        List<XWPFTableCell> cells;
        for (XWPFTable table : tables) {
            //表格属性
//            CTTblPr pr = table.getCTTbl().getTblPr();
            //获取表格对应的行
            rows = table.getRows();
            for (XWPFTableRow row : rows) {
                //获取行对应的单元格
                cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {
                    System.out.println(cell.getText());
                }
            }
        }
        this.close(is);
    }
}
