package my.suveng.server.common.poi.demo;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC;

/**
 * @author suwenguang
 * email suveng@163.com
 * since 2019/4/7
 * description:
 **/
public class Demo5 {

    /**
     * 处理不同内容样式
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
        row.createCell(0).setCellValue(CELL_TYPE_NUMERIC);
        row.createCell(1).setCellValue(HSSFCell.CELL_TYPE_BOOLEAN);
        row.createCell(2).setCellValue(HSSFCell.CELL_TYPE_STRING);



        //输出
        FileOutputStream fileOutputStream = new FileOutputStream("/demo4.xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();

    }
}
