package my.suveng.server.common.poi.demo;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Now;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author suwenguang
 * email suveng@163.com
 * since 2019/4/7
 * description:
 **/
public class Demo3 {

    /**
     * 创建单元格
     **/
    public static void main(String[] args) throws IOException {
        Workbook workbook=new HSSFWorkbook();
        Sheet first_sheet = workbook.createSheet("first sheet");
        //创建行和列
        Row row = first_sheet.createRow(0);
        Cell cell = row.createCell(0);
        //设置值
        cell.setCellValue(LocalDate.now().toDate().toString());

        Sheet second_sheet = workbook.createSheet("second sheet");
        Row row1 = second_sheet.createRow(2);
        Cell cell1 = row1.createCell(2);
        cell1.setCellValue(LocalDateTime.now().plusDays(5).toDate().toString());

        //输出
        FileOutputStream fileOutputStream = new FileOutputStream("/demo3.xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();

    }
}
