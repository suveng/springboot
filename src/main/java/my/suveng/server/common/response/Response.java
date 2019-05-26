package my.suveng.server.common.response;

import lombok.Data;

/**
 * @author suwenguang
 * email suveng@163.com
 * since 2019/2/26
 * description: 统一返回层
 **/
@Data
public class Response {
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误描述
     **/
    private String msg;

    /**
     * 返回数据
     **/
    private Object data;

    /**
     * 唯一入口
     *
     * @param responseEnums 系统内部错误枚举类
     * @param data        返回数据
     */
    Response(ResponseEnums responseEnums, Object data) {
        this.code = responseEnums.getCode();
        this.msg = responseEnums.getDescription();
        this.data = data;
    }

    public Response(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public Response() {
    }
}
