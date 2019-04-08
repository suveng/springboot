package my.suveng.server.common.poi.demo;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * @author suwenguang
 * email suveng@163.com
 * since 2019/4/7
 * description:
 **/
public class Demo4 {

    /**
     * 创建单元格样式
     **/
    public static void main(String[] args) throws IOException {
        Workbook workbook=new HSSFWorkbook();
        //单元格样式
        CreationHelper creationHelper = workbook.getCreationHelper();
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-mm-dd hh:MM:ss"));
        Sheet first_sheet = workbook.createSheet("first sheet");
        //创建行和列
        Row row = first_sheet.createRow(0);
        Cell cell = row.createCell(0);
        //设置值
        Date value = LocalDate.now().toDate();
        cell.setCellValue(value);
        //设置样式
        cell.setCellStyle(cellStyle);
        Sheet second_sheet = workbook.createSheet("second sheet");
        Row row1 = second_sheet.createRow(2);
        Cell cell1 = row1.createCell(2);
        Date date = LocalDateTime.now().plusDays(5).toDate();
        cell1.setCellValue(date);
        //设置样式
        cell1.setCellStyle(cellStyle);

        //输出
        FileOutputStream fileOutputStream = new FileOutputStream("/demo4.xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();

    }
}
