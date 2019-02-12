package my.suveng.springboot.custom.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import my.suveng.springboot.custom.entity.Custom;
import my.suveng.springboot.custom.mapper.CustomMapper;
import my.suveng.springboot.custom.service.ICustomService;
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
public class CustomServiceImpl extends ServiceImpl<CustomMapper, Custom> implements ICustomService {

}
