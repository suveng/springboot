package my.suveng.server.common.response;

/**
 * @author suwenguang
 * email suveng@163.com
 * since 2019/2/26
 * description: 统一返回层 工厂类
 **/
public class ResponseBuilder {
    /**
     * 创建方法
     * @param responseEnums 错误枚举
     * @param data 返回数据
     */
    public static Response build(ResponseEnums responseEnums, Object data) {
        return new Response(responseEnums, data);
    }

    /**
     * 创建简单参数错误返回
     * @return Result 统一返回结果
     */
    public static Response buildSimpleIllegalArgumentError() {
        return new Response(ResponseEnums.ILLEGAL_ARGUMENT_ERROR, null);
    }

    /**
     * 创建简单成功result，ResultEnums.SIMPLE_SUCCESS
     *
     * @return Result 统一返回结果
     */
    public static Response buildSimpleSuccessResult() {
        return new Response(ResponseEnums.SIMPLE_SUCCESS, null);
    }

    /**
     * 创建简单失败返回result，ResultEnums.SIMPLE_ERROR
     * @return Result 统一返回结果
     */
    public static Response buildSimpleErrorResult() {
        return new Response(ResponseEnums.SIMPLE_ERROR, null);
    }

    /**
     * 创建未知原因失败返回result，ResultEnums.UNKNOWN_ERROR
     * @return Result 统一返回结果
     */
    public static Response buildUnknownResult() {
        return new Response(ResponseEnums.UNKNOWN_ERROR, null);
    }

}
