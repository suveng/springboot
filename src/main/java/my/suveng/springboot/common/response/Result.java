package my.suveng.springboot.common.response;

import lombok.Data;

/**
 * @author suwenguang
 * email suveng@163.com
 * since 2019/2/26
 * description: 统一返回层
 **/
@Data
public class Result {
    private Integer code;
    private String msg;
    private Object data;


    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result() {
    }
}
