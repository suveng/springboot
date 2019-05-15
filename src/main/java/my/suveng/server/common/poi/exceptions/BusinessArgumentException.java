package my.suveng.server.common.poi.exceptions;

/**
 * @description 业务参数异常异常封装
 * @author <a href="linguozhi@52tt.com">soothing</a>
 * @since 2016-07-13
 * @version 1.0.0
 */
public class BusinessArgumentException extends IllegalArgumentException {

	/**
	 * 业务CODE码，详情见组件错误码协议文档。
	 */
	private int code;

	public BusinessArgumentException(String message) {
		super(message);
	}

	public BusinessArgumentException(int errorCode, String message) {
		super(message);
		this.code = errorCode;
	}

	public BusinessArgumentException(int errorCode, String message, Object... args) {
		super(String.format(message, args));
		this.code = errorCode;
	}

	public int getErrorCode() {
		return code;
	}

	public void setErrorCode(int errorCode) {
		this.code = errorCode;
	}

}
