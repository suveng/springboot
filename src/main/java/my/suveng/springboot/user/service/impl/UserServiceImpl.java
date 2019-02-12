package my.suveng.springboot.user.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import my.suveng.springboot.user.entity.User;
import my.suveng.springboot.user.mapper.UserMapper;
import my.suveng.springboot.user.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author suveng
 * @since 2019-02-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
