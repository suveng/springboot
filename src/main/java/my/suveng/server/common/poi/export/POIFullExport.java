
package my.suveng.server.common.poi.export;


import my.suveng.server.common.poi.exceptions.NestedExceptionUtils;
import my.suveng.server.common.poi.core.ClassMetaResolver;
import my.suveng.server.common.poi.core.POIEntityMeta;
import my.suveng.server.common.poi.core.POIEntityMetaCache;
import my.suveng.server.common.poi.core.POIException;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author <a href="linguozhi@52tt.com">soothing</a>
 * @version 1.0.0
 * @description 全量导出（特别注意，该类是非线程安全的，如果需要在多线程环境下使用，请自行处理同步问题）
 * @since 2016-07-13
 */
@Component
public class POIFullExport extends AbstractPOIExport {
    /**
     * 导出Excel
     *
     * @param entitys
     * @param response
     * @param fileName
     */
    public void export(List<?> entitys, HttpServletResponse response, String fileName) throws POIException {
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", "UTF-8"));
            response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            export(entitys, response.getOutputStream());
        } catch (Exception e) {
            throw new POIException(NestedExceptionUtils.buildMessage("export exception.", e));
        }
    }

    /**
     * 导出Excel
     *
     * @param entitys
     * @param out     void
     *
     * @throws IOException
     * @throws IllegalAccessException
     */
    public void export(List<?> entitys, OutputStream out) throws POIException {
        if (CollectionUtils.isEmpty(entitys)) {
            throw new POIException("entitys must not empty");
        }
        Object obj = entitys.get(0);
        if (!ClassMetaResolver.isExcelEntity(obj.getClass())) {
            throw new POIException("entitys must is ExcelEntity");
        }

        POIEntityMeta poiEntityMeta = POIEntityMetaCache.getEntityMeta(obj.getClass());
        if (StringUtils.isEmpty(poiEntityMeta.getTempletPath())) {
            throw new POIException("TempletPath must not null.");
        }

        String templetFilePath = getClass().getClassLoader().getResource("export/poi_templet.xls").getPath();
        InputStream in = null;

        try {
            in = new FileInputStream(templetFilePath);

            SXSSFWorkbook workbook = new SXSSFWorkbook();
            SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet();
            workbook.setActiveSheet(poiEntityMeta.getSheet());

            int rowIndex = 0;
            // 创建表头
            rowIndex = createTableHead(poiEntityMeta, workbook, sheet, rowIndex);
            // 创建表体
            rowIndex = createTableBody(entitys, poiEntityMeta, workbook, sheet);

            workbook.write(out);
            out.flush(); // 这里只做刷新流处理, 关闭流的动作由调用方实现
        } catch (Exception e) {
            throw new POIException(NestedExceptionUtils.buildMessage("export exception.", e));
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new POIException(NestedExceptionUtils.buildMessage("export exception.", e));
                }
            }
        }
    }
}
