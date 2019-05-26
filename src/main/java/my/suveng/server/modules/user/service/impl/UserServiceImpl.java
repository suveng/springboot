package my.suveng.server.modules.user.service.impl;

import my.suveng.server.modules.user.entity.User;
import my.suveng.server.modules.user.mapper.UserMapper;
import my.suveng.server.modules.user.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author suveng
 * @since 2019-05-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
