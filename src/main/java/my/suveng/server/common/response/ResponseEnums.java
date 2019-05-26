package my.suveng.server.common.response;

/**
 * @author suwenguang
 * email suveng@163.com
 * since 2019/2/26
 * description: 返回状态码
 **/
public enum ResponseEnums {
    /**
     * 请求成功
     */
    SIMPLE_SUCCESS(2000, "请求成功"),

    /**
     * 未知错误
     **/
    UNKNOWN_ERROR(-1, "未知错误"),

    /**
     * 请求失败
     **/
    SIMPLE_ERROR(500001, "请求失败"),
    /**
     * 参数错误
     */
    ILLEGAL_ARGUMENT_ERROR(500002, "参数错误"),
    /**
     * 返回结果有误
     */
    ILLEGAL_RESULT_ERROR(500003, "返回结果有误"),
    /**
     * 数据库错误
     */
    SQL_ERROR(500004, "数据库错误"),
    ;
    /**
     * 系统状态码
     */
    private Integer code;

    /**
     * 状态说明
     **/
    private String description;


    ResponseEnums() {
    }

    ResponseEnums(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
