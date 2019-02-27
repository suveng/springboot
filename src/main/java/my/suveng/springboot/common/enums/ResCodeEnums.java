package my.suveng.springboot.common.enums;

/**
 * @author suwenguang
 * email suveng@163.com
 * since 2019/2/26
 * description: 返回状态码
 **/
public enum ResCodeEnums {
    /**
     * 请求成功
     */
    SIMPLE_SUCCESS(200,"请求成功"),

    /**
     * 未知错误
     **/
    UNKNOWN_ERROR(100000,"未知错误"),

    /**
     * 请求失败
     **/
    SIMPLE_ERROR(100001,"请求失败")
    ;
    /**
     * 系统状态码
     */
    private Integer code;

    /**
     * 状态说明
     **/
    private String description;


    ResCodeEnums() {
    }

    ResCodeEnums(Integer code, String description) {
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
    }}
