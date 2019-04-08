package my.suveng.server.common.poi.annotation;


import my.suveng.server.common.poi.core.POIConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description 标记某个类是可以导出Excel
 * @author <a href="linguozhi@52tt.com">soothing</a>
 * @since 2016-07-13
 * @version 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelEntity {

	/**
	 * 模板路径
	 *
	 * @return String
	 */
	public String templetPath() default POIConstant.DEFAULT_TEMPLET_PATH;

	/**
	 * 是否生成表头( 默认生成, 如果使用自定义表头, 需将此项改为false )
	 *
	 * @return boolean
	 */
	public boolean generateTitle() default true;

	/**
	 * 起始行号
	 *
	 * @return int
	 */
	public int firstRow() default 1;

	/**
	 * 使用xxSheet页面来写数据
	 *
	 * @return int
	 */
	public int sheet() default 0;

	/**
	 * 描述
	 *
	 * @return
	 */
	public String detail() default "";

	/**
	 * 数据行是否显示斑马线
	 *
	 * @return boolean
	 */
	public boolean crosswalk() default false;

	/**
	 * 是否支持自动分页 false: 不支持 true: 支持（1个sheet页最大支持32768条数据, 超过这个量之后,
	 * 会写到另一个sheet页中）
	 *
	 * @return boolean
	 */
	public boolean paging() default false;

	/**
	 * 样式行、列组合
	 *
	 * @return int
	 */
	public int headRowNum() default -1;

	public int headColumnNum() default -1;
}
