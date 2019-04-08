package my.suveng.server.common.poi.annotation;

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
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelField {

	/**
	 * 列名称
	 *
	 * @return String
	 */
	public String name();

	/**
	 * 缺省值
	 * @return String
	 */
	public String defaultValue() default "";

	/**
	 * 列索引
	 *
	 * @return int
	 */
	public int index() default 0;

	/**
	 * 描述
	 *
	 * @return
	 */
	public String detail() default "";

	/**
	 * 日期格式
	 *
	 * @return String
	 */
	public String dateFormat() default "yyyy-MM-dd HH:mm:ss";

	/**
	 * 数字格式, 默认: 保持两位小数
	 *
	 * @return String
	 */
	public String doubleFormat() default "###.00";

	/**************************************** Excel选项 ****************************************/
	/**
	 * 表头单元格高度
	 *
	 * @return int
	 */
	public int headHeight() default 32;

	/**
	 * 表头单元格宽度
	 *
	 * @return int
	 */
	public int headWidth() default 50;

	/**
	 * 单元格高度
	 *
	 * @return int
	 */
	public int height() default -1;

	/**
	 * 单元格宽度
	 *
	 * @return int
	 */
	public int width() default 20;
}
