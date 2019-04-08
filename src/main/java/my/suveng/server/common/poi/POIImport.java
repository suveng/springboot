
package my.suveng.server.common.poi;


import my.suveng.server.common.poi.core.ClassMetaResolver;
import my.suveng.server.common.poi.core.POIEntityMeta;
import my.suveng.server.common.poi.core.POIEntityMetaCache;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author <a href="linguozhi@52tt.com">soothing</a>
 * @version 1.0.0
 * @description POI导入工具
 * @since 2016-07-13
 */
public class POIImport {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(POIImport.class);
    public static final String xlsType = "xls";
    public static final String xlsxType = "xlsx";
    private static final String dot = ".";

    /**
     * 将Excel转换为实体Bean
     *
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ParseException
     * @throws IllegalArgumentException
     */
    public static List<Object> transformToBean(InputStream in, Class<?> clazz) throws IOException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, ParseException {
        return transformToBean(in, clazz, xlsxType);
    }

    /**
     * 将Excel转换为实体Bean,添加excel类型(xls/xlsx)
     *
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ParseException
     * @throws IllegalArgumentException
     */
    public static List<Object> transformToBean(InputStream in, Class<?> clazz, String type) throws IOException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, ParseException {
        if (ClassMetaResolver.isExcelEntity(clazz)) {
            POIEntityMeta poiEntityMeta = POIEntityMetaCache.getEntityMeta(clazz);
            Workbook workbook = null;

            if (xlsType.equals(type)) {
                workbook = new HSSFWorkbook(in);
            } else if (xlsxType.equals(type)) {
                workbook = new XSSFWorkbook(in);
            } else {
                //throw new BusinessArgumentException("excel文件类型错误");
            }

            Sheet sheet = workbook.getSheetAt(poiEntityMeta.getSheet());

            Row row = null;
            Cell cell = null;
            List<Object> entitys = new ArrayList<Object>(30);
            int rowNum = 0;
            for (Iterator<Row> rowIter = sheet.rowIterator(); rowIter.hasNext(); ) {
                row = rowIter.next();
                if (rowNum < poiEntityMeta.getFirstRow()) {
                    rowNum = rowNum + 1;
                    continue;
                }

                if (row != null) {
                    Object obj = clazz.newInstance();
                    entitys.add(obj);

                    for (POIEntityMeta.POIEntityFieldMeta entityFieldMeta : poiEntityMeta.getEntityMetas()) {
                        cell = row.getCell(entityFieldMeta.getIndex());
                        if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                            continue;
                        }

                        // 日期类型
                        if (entityFieldMeta.getField().getGenericType() == Date.class) {
                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                entityFieldMeta.getField().set(obj,
                                        entityFieldMeta.getSimpleDateFormat().parseObject(cell.getStringCellValue()));
                            } else {
                                logger.warn(String.format("%s cell type is %s, ignore.", entityFieldMeta.getField()
                                        .getName(), cell.getCellType()));
                            }
                        }
                        // 字符串类型
                        else if (entityFieldMeta.getField().getGenericType() == String.class) {
                            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                Double value = cell.getNumericCellValue();
                                String valueStr = Double.toString(value);
                                //格式化数字,原始的excel中不能保存.0
                                if (!StringUtils.isEmpty(valueStr)) {
                                    //将小数点后为0的 去掉小数点以后的部分
                                    if (valueStr.indexOf(dot) > 0 && ".0".equals(valueStr.substring(valueStr.indexOf(dot)))) {
                                        valueStr = valueStr.substring(0, valueStr.indexOf(dot));
                                    }
                                }
                                entityFieldMeta.getField().set(obj, valueStr);
                            } else {
                                entityFieldMeta.getField().set(obj, cell.getStringCellValue());
                            }
                        }
                        // Integer类型
                        else if (entityFieldMeta.getField().getGenericType() == Integer.TYPE
                                || entityFieldMeta.getField().getGenericType() == Integer.class) {
                            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                Double cellValue = cell.getNumericCellValue();
                                if (cellValue != null) {
                                    entityFieldMeta.getField().set(obj, (int) cellValue.doubleValue());
                                }
                            } else {
                                logger.warn(String.format("%s cell type is %s, ignore.", entityFieldMeta.getField()
                                        .getName(), cell.getCellType()));
                            }
                        }
                        // Short类型
                        else if (entityFieldMeta.getField().getGenericType() == Short.TYPE
                                || entityFieldMeta.getField().getGenericType() == Short.class) {
                            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                Double cellValue = cell.getNumericCellValue();
                                if (cellValue != null) {
                                    entityFieldMeta.getField().set(obj, (short) cellValue.doubleValue());
                                }
                            } else {
                                logger.warn(String.format("%s cell type is %s, ignore.", entityFieldMeta.getField()
                                        .getName(), cell.getCellType()));
                            }
                        }
                        // Double类型
                        else if (entityFieldMeta.getField().getGenericType() == Double.TYPE
                                || entityFieldMeta.getField().getGenericType() == Double.class) {
                            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                Double cellValue = cell.getNumericCellValue();
                                if (cellValue != null) {
                                    entityFieldMeta.getField().set(obj, cellValue.doubleValue());
                                }
                            } else {
                                logger.warn(String.format("%s cell type is %s, ignore.", entityFieldMeta.getField()
                                        .getName(), cell.getCellType()));
                            }
                        }
                        // Float类型
                        else if (entityFieldMeta.getField().getGenericType() == Float.TYPE
                                || entityFieldMeta.getField().getGenericType() == Float.class) {
                            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                Double cellValue = cell.getNumericCellValue();
                                if (cellValue != null) {
                                    entityFieldMeta.getField().set(obj, (float) cellValue.doubleValue());
                                }
                            } else {
                                logger.warn(String.format("%s cell type is %s, ignore.", entityFieldMeta.getField()
                                        .getName(), cell.getCellType()));
                            }
                        } else {
                            logger.warn("Non support type: " + entityFieldMeta.getField().getGenericType());
                        }
                    }
                }
            }
            return entitys;
        } else {
            logger.warn(clazz + " is not ExcelEntity.");
        }
        return null;
    }
}
