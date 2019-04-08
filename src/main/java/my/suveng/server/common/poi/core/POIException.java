
package my.suveng.server.common.poi.core;


import my.suveng.server.common.exceptions.NestedRuntimeException;

/**
 * @description POI异常类
 * @author <a href="linguozhi@52tt.com">soothing</a>
 * @since 2016-07-13
 * @version 1.0.0
 */
public class POIException extends NestedRuntimeException {

	public POIException(String msg) {
		super(msg);
	}

}
