
package my.suveng.server.common.poi.core;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="linguozhi@52tt.com">soothing</a>
 * @version 1.0.0
 * @description 实体类元数据
 * @since 2016-07-13
 */
public class POIEntityMeta {

    /**
     * 模板路径
     */
    private String templetPath;

    /**
     * 描述
     */
    private String detail;

    /**
     * 生成表头
     */
    private boolean generateTitle;

    /**
     * 数据启始行号
     */
    private int firstRow;

    /**
     * Sheet页面
     */
    private int sheet;

    /**
     * 引用样式[ x行, x列 ], 这两个属性必须成对出现
     *
     * @return String
     */
    private int headRowNum;
    private int headColumnNum;

    /**
     * 数据行是否显示斑马线
     */
    private boolean crosswalk;

    /**
     * 是否支持分页
     */
    private boolean paging;

    /**
     * 实体字段元数据s
     */
    private List<POIEntityFieldMeta> entityMetas = new ArrayList<POIEntityFieldMeta>();

    /**
     * 实体字段元数据
     */
    public static class POIEntityFieldMeta implements Comparable<POIEntityFieldMeta> {
        /**
         * 缓存JDK的field字段
         */
        private Field field;

        /**
         * 列名称
         *
         * @return String
         */
        private String name;

        /**
         * 缺省值
         */
        private String defaultValue;

        /**
         * 列索引
         *
         * @return int
         */
        private int index;

        /**
         * 描述
         *
         * @return
         */
        private String detail;

        /**
         * 日期格式
         */
        private String dateFormat;

        /**
         * 日期格式
         */
        private SimpleDateFormat simpleDateFormat;

        /**
         * 数字格式
         */
        private DecimalFormat decimalFormat;

        /**
         * 数字格式
         */
        private String doubleFormat;

        /**
         * 表头高度
         */
        private int headHeight;

        /**
         * 表头宽度
         */
        private int headWidth;

        /**
         * 数据行高度
         */
        private int height;

        /**
         * 数据行宽度
         */
        private int width;

        public String getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeadHeight() {
            return headHeight;
        }

        public void setHeadHeight(int headHeight) {
            this.headHeight = headHeight;
        }

        public int getHeadWidth() {
            return headWidth;
        }

        public void setHeadWidth(int headWidth) {
            this.headWidth = headWidth;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public Field getField() {
            return field;
        }

        public void setField(Field field) {
            this.field = field;
        }

        public String getDateFormat() {
            return dateFormat;
        }

        public void setDateFormat(String dateFormat) {
            this.dateFormat = dateFormat;
            this.simpleDateFormat = new SimpleDateFormat(dateFormat);
        }

        public SimpleDateFormat getSimpleDateFormat() {
            return simpleDateFormat;
        }

        public void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
            this.simpleDateFormat = simpleDateFormat;
        }

        public DecimalFormat getDecimalFormat() {
            return decimalFormat;
        }

        public void setDecimalFormat(DecimalFormat decimalFormat) {
            this.decimalFormat = decimalFormat;
        }

        public String getDoubleFormat() {
            return doubleFormat;
        }

        public void setDoubleFormat(String doubleFormat) {
            this.doubleFormat = doubleFormat;
            this.decimalFormat = new DecimalFormat(doubleFormat);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        @Override
        public int compareTo(POIEntityFieldMeta o) {
            if (this.index > o.index) {
                return -1;
            } else if (this.index < o.index) {
                return 1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
        }
    }

    public int getHeadRowNum() {
        return headRowNum;
    }

    public void setHeadRowNum(int headRowNum) {
        this.headRowNum = headRowNum;
    }

    public int getHeadColumnNum() {
        return headColumnNum;
    }

    public void setHeadColumnNum(int headColumnNum) {
        this.headColumnNum = headColumnNum;
    }

    public int getSheet() {
        return sheet;
    }

    public void setSheet(int sheet) {
        this.sheet = sheet;
    }

    public boolean isGenerateTitle() {
        return generateTitle;
    }

    public void setGenerateTitle(boolean generateTitle) {
        this.generateTitle = generateTitle;
    }

    public String getTempletPath() {
        return templetPath;
    }

    public void setTempletPath(String templetPath) {
        this.templetPath = templetPath;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public List<POIEntityFieldMeta> getEntityMetas() {
        return entityMetas;
    }

    public void setEntityMetas(List<POIEntityFieldMeta> entityMetas) {
        this.entityMetas = entityMetas;
    }

    public int getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(int firstRow) {
        this.firstRow = firstRow;
    }

    public boolean isCrosswalk() {
        return crosswalk;
    }

    public void setCrosswalk(boolean crosswalk) {
        this.crosswalk = crosswalk;
    }

    public boolean isPaging() {
        return paging;
    }

    public void setPaging(boolean paging) {
        this.paging = paging;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
