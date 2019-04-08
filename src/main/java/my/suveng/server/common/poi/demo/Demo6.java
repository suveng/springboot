package my.suveng.server.common.poi.demo;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC;

/**
 * @author suwenguang
 * email suveng@163.com
 * since 2019/4/7
 * description:
 **/
public class Demo6 {

    /**
     * 遍历工作簿
     **/
    public static void main(String[] args) throws IOException {
        HSSFWorkbook workbook=new HSSFWorkbook(new FileInputStream("/demo6.xls"));

        //获取sheet页
        Sheet sheetAt = workbook.getSheetAt(0);
        //遍历行
        for (int i = 0; i <=sheetAt.getLastRowNum(); i++) {
            Row row = sheetAt.getRow(i);
            //遍历列
            for (int j = 0; j <=row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                if (cell==null){
                    break;
                }
                System.out.println(getValue(cell));
            }
        }

    }
    private static String getValue(Cell cell){
        if (cell==null){
            return null;
        }
        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
            return String.valueOf(cell.getNumericCellValue());
        }
        if (cell.getCellType()==HSSFCell.CELL_TYPE_STRING){
            return cell.getStringCellValue();
        }
        if (cell.getCellType()==HSSFCell.CELL_TYPE_BOOLEAN){
            return String.valueOf(cell.getBooleanCellValue());
        }
        return cell.getStringCellValue();
    }

}
