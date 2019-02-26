package my.suveng.springboot.common.response;

import my.suveng.springboot.common.enums.ResCodeEnums;

import java.net.HttpURLConnection;

/**
 * @author suwenguang
 * email suveng@163.com
 * since 2019/2/26
 * description: 统一返回层 工厂类
 **/
public class ResultBuilder {
    /**
     * 创建简单成功result，ResCodeEnums.SIMPLE_SUCCESS
     *
     * @return Result 统一返回结果
     */
    public static Result buildSimpleSuccessResult() {
        return new Result(ResCodeEnums.SIMPLE_SUCCESS.getCode(), ResCodeEnums.SIMPLE_SUCCESS.getDescription(), null);
    }

    /**
     * 创建简单失败返回result，ResCodeEnums.SIMPLE_ERROR
     * @return Result 统一返回结果
     */
    public static Result buildSimpleErrorResult(){
        return new Result(ResCodeEnums.SIMPLE_ERROR.getCode(),ResCodeEnums.SIMPLE_ERROR.getDescription(),null);
    }

    /**
     * 创建未知原因失败返回result，ResCodeEnums.UNKNOWN_ERROR
     * @return Result 统一返回结果
     */
    public static Result buildUnknownResult(){
        return new Result(ResCodeEnums.UNKNOWN_ERROR.getCode(),ResCodeEnums.UNKNOWN_ERROR.getDescription(),null);
    }

}
