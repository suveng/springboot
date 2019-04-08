
package my.suveng.server.common.poi.export;

import my.suveng.server.common.exceptions.BusinessStateException;
import my.suveng.server.common.poi.core.POIEntityMeta;
import my.suveng.server.common.poi.core.POIEntityMeta.POIEntityFieldMeta;
import my.suveng.server.common.poi.core.POIException;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedExceptionUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author <a href="linguozhi@52tt.com">soothing</a>
 * @version 1.0.0
 * @description POI导出抽象类
 * @since 2016-07-13
 */
public class AbstractPOIExport {

    public static final Logger logger = LoggerFactory.getLogger(AbstractPOIExport.class);
    public static final String xlsxExtention = ".xlsx";
    private static final int MAX_SIZE = 100000;

    /**
     * 当前数据行索引
     */
    private int rowIndex = 0;

    /**
     * 创建表头
     *
     * @param poiEntityMeta 实体类元数据
     * @param sheet         Excel Sheet页
     * @param rowIndex      行号
     *
     * @return int 行号
     */
    protected int createTableHead(POIEntityMeta poiEntityMeta, SXSSFWorkbook workbook, SXSSFSheet sheet, int rowIndex) {
        return createTableHead(poiEntityMeta, null, workbook, sheet, rowIndex);
    }

    /**
     * 创建表头
     *
     * @param poiEntityMeta 实体类元数据
     * @param props         属性s
     * @param sheet         Excel Sheet页
     * @param rowIndex      行号
     *
     * @return int 行号
     */
    protected int createTableHead(POIEntityMeta poiEntityMeta, List<String> props, SXSSFWorkbook workbook, SXSSFSheet sheet, int rowIndex) {

        /**
         * K: 属性名
         * V: 属性元数据
         */
        HashMap<String, POIEntityFieldMeta> poiPropMap = getPOIEntityMap(props, poiEntityMeta);

        // 工具自动生成表头
        CellStyle headStyle = null;
        if (poiEntityMeta.isGenerateTitle()) {
            Row row = sheet.createRow((int) rowIndex);
            float height = 0.0F;

            /** 导出所有包含注解的属性名称 */
            if (props == null || props.size() == 0) {
                for (POIEntityFieldMeta entityFieldMeta : poiEntityMeta.getEntityMetas()) {
                    Cell cell = row.createCell(entityFieldMeta.getIndex());
                    cell.setCellValue(entityFieldMeta.getName());

                    // 如果配置了样式列, 则复制模板页的样式
                    if (poiEntityMeta.getHeadRowNum() != -1 && poiEntityMeta.getHeadColumnNum() != -1) {
                        if (headStyle == null) {
                            headStyle = workbook.createCellStyle();
                            headStyle.setFillForegroundColor(HSSFColor.ORANGE.index);
                            headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                            headStyle.setBorderTop(CellStyle.BORDER_THIN);
                            headStyle.setBorderBottom(CellStyle.BORDER_THIN);
                            headStyle.setBorderRight(CellStyle.BORDER_THIN);
                            headStyle.setBorderLeft(CellStyle.BORDER_THIN);
                        }
                        cell.setCellStyle(headStyle);
                    }

                    // 默认取第一个属性的行高
                    if (height == 0.0F) {
                        height = entityFieldMeta.getHeadHeight();
                    }
                }
            }
            /** 导出指定的属性名称 */
            else {
                int colIndex = 0;
                for (String prop : props) {
                    POIEntityFieldMeta entityFieldMeta = poiPropMap.get(prop);
                    if (entityFieldMeta == null) {
                        throw new BusinessStateException(prop + " is not found ExcelField.");
                    }

                    Cell cell = row.createCell(colIndex);
                    cell.setCellValue(entityFieldMeta.getName());

                    // 如果配置了样式列, 则复制模板页的样式
                    if (poiEntityMeta.getHeadRowNum() != -1 && poiEntityMeta.getHeadColumnNum() != -1) {
                        if (headStyle == null) {
                            headStyle = workbook.createCellStyle();
                            headStyle.setFillForegroundColor(HSSFColor.ORANGE.index);
                            headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                            headStyle.setBorderTop(CellStyle.BORDER_THIN);
                            headStyle.setBorderBottom(CellStyle.BORDER_THIN);
                            headStyle.setBorderRight(CellStyle.BORDER_THIN);
                            headStyle.setBorderLeft(CellStyle.BORDER_THIN);
                        }
                        cell.setCellStyle(headStyle);
                    }

                    // 默认取第一个属性的行高
                    if (height == 0.0F) {
                        height = entityFieldMeta.getHeadHeight();
                    }

                    colIndex++;
                }
            }
            // 设置行高
            row.setHeightInPoints(height);
            rowIndex = rowIndex + 1;
        }
        // 使用指定模板的表头
        else {
            rowIndex = poiEntityMeta.getFirstRow();
        }
        this.rowIndex = rowIndex;

        // 设置列宽度
        if (props == null || props.size() == 0) {
            for (POIEntityFieldMeta entityFieldMeta : poiEntityMeta.getEntityMetas()) {
                sheet.setColumnWidth(entityFieldMeta.getIndex(), (entityFieldMeta.getWidth() * 256));
            }
        } else {
            int colIndex = 0;
            for (String prop : props) {
                POIEntityMeta.POIEntityFieldMeta entityFieldMeta = poiPropMap.get(prop);
                if (entityFieldMeta == null) {
                    throw new BusinessStateException(prop + " is not found ExcelField.");
                }
                sheet.setColumnWidth(colIndex, (entityFieldMeta.getWidth() * 256));
                colIndex++;
            }
        }
        return rowIndex;
    }

    /**
     * 基于注解创建数据行
     *
     * @param entitys
     * @param poiEntityMeta
     * @param workbook
     * @param sheet
     *
     * @return
     *
     * @throws int
     */
    protected int createTableBody(List<?> entitys, POIEntityMeta poiEntityMeta, SXSSFWorkbook workbook, SXSSFSheet sheet) throws POIException {
        return createTableBody(entitys, null, poiEntityMeta, workbook, sheet);
    }

    /**
     * 创建表体
     *
     * @param entitys
     * @param props
     * @param poiEntityMeta
     * @param sheet
     * @param
     *
     * @return
     *
     * @throws IllegalAccessException
     */
    protected int createTableBody(List<?> entitys, List<String> props, POIEntityMeta poiEntityMeta, SXSSFWorkbook workbook, SXSSFSheet sheet) throws POIException {
        // 创建数据行并填充数据
        float height = -1;
        int rowNum = 1;
        CellStyle crosswalkStyle = null;
        CellStyle style = null;

        try {
            /**
             * K: 属性名
             * V: 属性元数据
             */
            HashMap<String, POIEntityFieldMeta> poiPropMap = getPOIEntityMap(props, poiEntityMeta);

            for (Object entity : entitys) {
                if (rowIndex >= MAX_SIZE) {
                    if (poiEntityMeta.isPaging()) {
                        sheet = (SXSSFSheet) workbook.createSheet();
                        resetSheet(sheet);
                        rowIndex = createTableHead(poiEntityMeta, props, workbook, sheet, 0);
                    } else {
                        throw new POIException(String.format("entitys more than %d.", MAX_SIZE));
                    }
                }

                Row row = sheet.createRow((int) rowIndex);

                /** 导出所有包含注解的属性 */
                if (props == null || props.size() == 0) {
                    for (POIEntityFieldMeta entityFieldMeta : poiEntityMeta.getEntityMetas()) {
                        Object fieldValue = entityFieldMeta.getField().get(entity);
                        int colIndex = entityFieldMeta.getIndex();

                        // 默认取第一个属性的行高
                        if (height == -1) {
                            height = entityFieldMeta.getHeight();
                        }

                        Cell cell = row.createCell(colIndex);

                        // 数据行允许显示斑马线, 设置斑马线
                        if (poiEntityMeta.isCrosswalk() && (rowNum % 2 == 0)) {
                            if (crosswalkStyle == null) {
                                crosswalkStyle = workbook.createCellStyle();
                                crosswalkStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
                                crosswalkStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                                crosswalkStyle.setBorderTop(CellStyle.BORDER_THIN);
                                crosswalkStyle.setBorderBottom(CellStyle.BORDER_THIN);
                                crosswalkStyle.setBorderRight(CellStyle.BORDER_THIN);
                                crosswalkStyle.setBorderLeft(CellStyle.BORDER_THIN);
                            }
                            cell.setCellStyle(crosswalkStyle);
                        } else {
                            if (style == null) {
                                style = workbook.createCellStyle();
                                style.setBorderTop(CellStyle.BORDER_THIN);
                                style.setBorderBottom(CellStyle.BORDER_THIN);
                                style.setBorderRight(CellStyle.BORDER_THIN);
                                style.setBorderLeft(CellStyle.BORDER_THIN);
                            }
                            cell.setCellStyle(style);
                        }

                        setCellValue(entityFieldMeta, fieldValue, cell);
                    }
                }
                /** 导出指定属性 */
                else {
                    int colIndex = 0;
                    for (String prop : props) {
                        POIEntityFieldMeta entityFieldMeta = poiPropMap.get(prop);
                        if (entityFieldMeta == null) {
                            throw new BusinessStateException(prop + " is not found ExcelField.");
                        }

                        Object fieldValue = entityFieldMeta.getField().get(entity);

                        // 默认取第一个属性的行高
                        if (height == -1) {
                            height = entityFieldMeta.getHeight();
                        }

                        Cell cell = row.createCell(colIndex);

                        // 数据行允许显示斑马线, 设置斑马线
                        if (poiEntityMeta.isCrosswalk() && (rowNum % 2 == 0)) {
                            if (crosswalkStyle == null) {
                                crosswalkStyle = workbook.createCellStyle();
                                crosswalkStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
                                crosswalkStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                                crosswalkStyle.setBorderTop(CellStyle.BORDER_THIN);
                                crosswalkStyle.setBorderBottom(CellStyle.BORDER_THIN);
                                crosswalkStyle.setBorderRight(CellStyle.BORDER_THIN);
                                crosswalkStyle.setBorderLeft(CellStyle.BORDER_THIN);
                            }
                            cell.setCellStyle(crosswalkStyle);
                        } else {
                            if (style == null) {
                                style = workbook.createCellStyle();
                                style.setBorderTop(CellStyle.BORDER_THIN);
                                style.setBorderBottom(CellStyle.BORDER_THIN);
                                style.setBorderRight(CellStyle.BORDER_THIN);
                                style.setBorderLeft(CellStyle.BORDER_THIN);
                            }
                            cell.setCellStyle(style);
                        }

                        setCellValue(entityFieldMeta, fieldValue, cell);
                        colIndex++;
                    }
                }

                if (height != -1) {
                    row.setHeightInPoints(height);
                }

                rowNum += 1;
                rowIndex = rowIndex + 1;
            }
        } catch (Exception e) {
            throw new POIException(NestedExceptionUtils.buildMessage("createTableBody exception.", e));
        }
        return rowIndex;
    }

    /**
     * 获得POI实体类属性名与POI元数据的映射关系
     *
     * @param props
     * @param poiEntityMeta
     *
     * @return HashMap<String, POIEntityFieldMeta>
     */
    private HashMap<String, POIEntityFieldMeta> getPOIEntityMap(List<String> props,
                                                                POIEntityMeta poiEntityMeta) {
        HashMap<String, POIEntityFieldMeta> poiPropMap = new HashMap<String, POIEntityFieldMeta>(
                poiEntityMeta.getEntityMetas().size());
        if (props != null && props.size() > 0) {
            for (POIEntityFieldMeta poiEntityFieldMeta : poiEntityMeta.getEntityMetas()) {
                if (!poiPropMap.containsKey(poiEntityFieldMeta.getField().getName())) {
                    poiPropMap.put(poiEntityFieldMeta.getField().getName(), poiEntityFieldMeta);
                }
            }
        }
        return poiPropMap;
    }

    /**
     * 设置单元格的值
     *
     * @param entityFieldMeta
     * @param fieldValue
     * @param cell            void
     */
    private void setCellValue(POIEntityFieldMeta entityFieldMeta, Object fieldValue, Cell cell) {
        // 如果字段值为空时, 使用缺省值
        if (fieldValue == null) {
            cell.setCellValue(entityFieldMeta.getDefaultValue());
            return;
        }

        // 日期类型
        if (entityFieldMeta.getField().getGenericType() == Date.class) {
            if (fieldValue != null) {
                SimpleDateFormat sdf = entityFieldMeta.getSimpleDateFormat();
                cell.setCellValue(sdf.format(fieldValue));
            }
        }
        // 字符串类型
        else if (entityFieldMeta.getField().getGenericType() == String.class) {
            if (StringUtils.isEmpty((String) fieldValue)) {
                cell.setCellValue(entityFieldMeta.getDefaultValue());
            } else {
                cell.setCellValue((String) fieldValue);
            }
        }
        // Integer类型
        else if (entityFieldMeta.getField().getGenericType() == Integer.TYPE
                || entityFieldMeta.getField().getGenericType() == Integer.class) {
            cell.setCellValue((Integer) fieldValue);
        }
        // Long类型
        else if (entityFieldMeta.getField().getGenericType() == Long.TYPE
                || entityFieldMeta.getField().getGenericType() == Long.class) {
            cell.setCellValue((Long) fieldValue);
        }
        // Short类型
        else if (entityFieldMeta.getField().getGenericType() == Short.TYPE
                || entityFieldMeta.getField().getGenericType() == Short.class) {
            cell.setCellValue((Short) fieldValue);
        }
        // Flat类型 | Double类型
        else if (entityFieldMeta.getField().getGenericType() == Double.TYPE
                || entityFieldMeta.getField().getGenericType() == Double.class
                || entityFieldMeta.getField().getGenericType() == Float.TYPE
                || entityFieldMeta.getField().getGenericType() == Float.class
                || entityFieldMeta.getField().getGenericType() == BigDecimal.class) {
            if (fieldValue != null) {
                DecimalFormat doubleFormat = entityFieldMeta.getDecimalFormat();
                cell.setCellValue(doubleFormat.format(fieldValue));
            }
        } else {
            logger.warn("Non support type: " + entityFieldMeta.getField().getGenericType());
        }
    }

    /**
     * 获得模板单元格的样式
     *
     * @param rowNum
     * @param columnNum
     * @param templetSheet
     *
     * @return Cell
     */
    private Cell getStyleTempletCell(int rowNum, int columnNum, Sheet templetSheet) {
        if (templetSheet == null || templetSheet.getRow(rowNum) == null) {
            return null;
        }
        return templetSheet.getRow(rowNum).getCell(columnNum);
    }

    /**
     * 重新设置sheet页，这里是为了给子类传递sheet页对象
     */
    protected void resetSheet(SXSSFSheet sheet) {
    }
}
