
package my.suveng.server.common.exceptions;

/**
 * @description
 * @author <a href="linguozhi@52tt.com">soothing</a>
 * @since 2016-07-13
 * @version 1.0.0
 */
public class BusinessStateException extends IllegalStateException {

	/**
	 * 业务CODE码，详情见组件错误码协议文档。
	 */
	private int code;

	public BusinessStateException(String message) {
		super(message);
	}

	public BusinessStateException(int errorCode, String message) {
		super(message);
		this.code = errorCode;
	}

	public BusinessStateException(int errorCode, String message, Object... args) {
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
