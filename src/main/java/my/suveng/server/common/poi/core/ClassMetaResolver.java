package my.suveng.server.common.poi.core;


import my.suveng.server.common.poi.annotation.ExcelEntity;
import my.suveng.server.common.poi.annotation.ExcelField;
import my.suveng.server.common.poi.core.POIEntityMeta.POIEntityFieldMeta;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 解析包含特定的注解的类，并生成源数据
 *
 * @author gzeyu
 */
public class ClassMetaResolver {

    /**
     * 解析类并生成元数据
     *
     * @param clazz
     *
     * @return
     */
    public static POIEntityMeta resolver(Class<?> clazz) {
        POIEntityMeta poiEntityMeta = null;
        if (isExcelEntity(clazz)) {
            poiEntityMeta = generateMeta(clazz);
        }
        return poiEntityMeta;
    }

    /**
     * 检查是否包含ExcelEntity注解
     *
     * @param clazz
     *
     * @return
     */
    public static boolean isExcelEntity(Class<?> clazz) {
        Annotation classAnnotation = clazz.getAnnotation(ExcelEntity.class);
        return classAnnotation != null;
    }

    private static boolean isExcelEntityField(Field field) {
        return field.isAnnotationPresent(ExcelField.class);
    }

    /**
     * 生成源数据
     *
     * @param clazz
     *
     * @return
     */
    private static POIEntityMeta generateMeta(Class<?> clazz) {
        POIEntityMeta poiEntityMeta = new POIEntityMeta();
        List<POIEntityFieldMeta> entityMetas = new ArrayList<POIEntityFieldMeta>();

        ExcelEntity excelEntity = clazz.getAnnotation(ExcelEntity.class);
        poiEntityMeta.setTempletPath(excelEntity.templetPath());
        poiEntityMeta.setDetail(excelEntity.detail());
        poiEntityMeta.setGenerateTitle(excelEntity.generateTitle());
        poiEntityMeta.setSheet(excelEntity.sheet());
        poiEntityMeta.setFirstRow(excelEntity.firstRow());
        poiEntityMeta.setHeadRowNum(excelEntity.headRowNum());
        poiEntityMeta.setHeadColumnNum(excelEntity.headColumnNum());
        poiEntityMeta.setCrosswalk(excelEntity.crosswalk());
        poiEntityMeta.setPaging(excelEntity.paging());

        POIEntityFieldMeta fieldMeta = null;
        for (Field field : clazz.getDeclaredFields()) {
            if (isExcelEntityField(field)) {
                ExcelField excelField = field.getAnnotation(ExcelField.class);

                fieldMeta = new POIEntityFieldMeta();
                fieldMeta.setIndex(excelField.index());
                fieldMeta.setName(excelField.name());
                fieldMeta.setDefaultValue(excelField.defaultValue());

                fieldMeta.setDetail(excelField.detail());
                fieldMeta.setField(field);
                field.setAccessible(true);

                fieldMeta.setDateFormat(excelField.dateFormat());
                fieldMeta.setDoubleFormat(excelField.doubleFormat());

                fieldMeta.setHeadHeight(excelField.headHeight());
                fieldMeta.setHeadWidth(excelField.headWidth());
                fieldMeta.setHeight(excelField.height());
                fieldMeta.setWidth(excelField.width());

                entityMetas.add(fieldMeta);
            }
        }

        // 获取父类属性
        Class farther = clazz.getSuperclass();
        if (farther != Object.class && isExcelEntity(farther)) {
            for (Field field : farther.getDeclaredFields()) {
                if (isExcelEntityField(field)) {
                    ExcelField excelField = field.getAnnotation(ExcelField.class);

                    fieldMeta = new POIEntityFieldMeta();
                    fieldMeta.setIndex(excelField.index());
                    fieldMeta.setName(excelField.name());
                    fieldMeta.setDefaultValue(excelField.defaultValue());

                    fieldMeta.setDetail(excelField.detail());
                    fieldMeta.setField(field);
                    field.setAccessible(true);

                    fieldMeta.setDateFormat(excelField.dateFormat());
                    fieldMeta.setDoubleFormat(excelField.doubleFormat());

                    fieldMeta.setHeadHeight(excelField.headHeight());
                    fieldMeta.setHeadWidth(excelField.headWidth());
                    fieldMeta.setHeight(excelField.height());
                    fieldMeta.setWidth(excelField.width());

                    entityMetas.add(fieldMeta);
                }
            }
        }

        Collections.sort(entityMetas);
        poiEntityMeta.setEntityMetas(entityMetas);
        return poiEntityMeta;
    }
}
