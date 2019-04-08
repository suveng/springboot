package my.suveng.server.common.poi.demo;

import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author suwenguang
 * email suveng@163.com
 * since 2019/4/7
 * description:
 **/
public class Demo7 {

    /**
     * 文本提取
     **/
    public static void main(String[] args) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream("/demo6.xls"));
        ExcelExtractor excelExtractor = new ExcelExtractor(workbook);
        //不需要sheet名字
        excelExtractor.setIncludeSheetNames(false);
        System.out.println(excelExtractor.getText());
    }
}
