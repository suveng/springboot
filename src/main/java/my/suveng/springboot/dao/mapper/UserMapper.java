package my.suveng.springboot.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import my.suveng.springboot.model.po.User;

/**
 * <p>
 * MP 支持不需要 UserMapper.xml 这个模块演示内置 CRUD 咱们就不要 XML 部分了
 * </p>
 *
 * @author hubin
 * @since 2018-08-11
 */
public interface UserMapper extends BaseMapper<User> {

}
