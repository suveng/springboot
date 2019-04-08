package my.suveng.server.common.poi.demo;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author suwenguang
 * email suveng@163.com
 * since 2019/4/7
 * description:
 **/
public class Demo1 {

    /**
     * 创建新的工作簿excel
     **/
    public static void main(String[] args) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        FileOutputStream fileOutputStream = new FileOutputStream("/Demo1.xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }
}
