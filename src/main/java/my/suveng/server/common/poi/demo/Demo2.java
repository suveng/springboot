package my.suveng.server.common.poi.demo;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author suwenguang
 * email suveng@163.com
 * since 2019/4/7
 * description:
 **/
public class Demo2 {

    /**
     * 创建新的sheet页
     **/
    public static void main(String[] args) throws IOException {
        Workbook workbook=new HSSFWorkbook();
        workbook.createSheet("第一个sheet页");
        workbook.createSheet("第二个sheet页");
        FileOutputStream fileOutputStream = new FileOutputStream("/Demo2.xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }
}
