package my.suveng.springboot.custom.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import my.suveng.springboot.common.entity.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author suveng
 * @since 2019-02-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Custom extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * custom id 主键
     */
    @TableId(value = "custom_id", type = IdType.AUTO)
    private Integer customId;

    /**
     * tt账号
     */
    private String ttAccount;

    /**
     * 玩家名
     */
    private String name;

    /**
     * 登记QQ号
     */
    private String qq;

    /**
     * 登记微信号
     */
    private String wechat;

    /**
     * 登记手机号
     */
    private String phone;

    /**
     * 账号的最后登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 账号充值总额
     */
    private BigDecimal feeTotal;

    /**
     * 记录创建时间
     */
    private LocalDateTime createTime;

    /**
     * 记录更新时间
     */
    private LocalDateTime modifyTime;

    /**
     * 逻辑删除标记
     */
    private Integer deleted;


}
