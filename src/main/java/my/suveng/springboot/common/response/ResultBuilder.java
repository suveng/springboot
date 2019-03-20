package my.suveng.springboot.common.response;

/**
 * @author suwenguang
 * email suveng@163.com
 * since 2019/2/26
 * description: 统一返回层 工厂类
 **/
public class ResultBuilder {
    /**
     * 创建方法
     * @param resultEnums 错误枚举
     * @param data 返回数据
     */
    public static Result build(ResultEnums resultEnums,Object data){
        return new Result(resultEnums,data);
    }

    /**
     * 创建简单参数错误返回
     * @return Result 统一返回结果
     */
    public static Result buildSimpleIllegalArgumentError(){
        return new Result(ResultEnums.ILLEGAL_ARGUMENT_ERROR,null);
    }

    /**
     * 创建简单成功result，ResultEnums.SIMPLE_SUCCESS
     *
     * @return Result 统一返回结果
     */
    public static Result buildSimpleSuccessResult() {
        return new Result(ResultEnums.SIMPLE_SUCCESS, null);
    }

    /**
     * 创建简单失败返回result，ResultEnums.SIMPLE_ERROR
     * @return Result 统一返回结果
     */
    public static Result buildSimpleErrorResult(){
        return new Result(ResultEnums.SIMPLE_ERROR,null);
    }

    /**
     * 创建未知原因失败返回result，ResultEnums.UNKNOWN_ERROR
     * @return Result 统一返回结果
     */
    public static Result buildUnknownResult(){
        return new Result(ResultEnums.UNKNOWN_ERROR,null);
    }

}
