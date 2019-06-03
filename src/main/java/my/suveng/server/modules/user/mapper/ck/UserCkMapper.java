package my.suveng.server.modules.user.mapper.ck;

import my.suveng.server.modules.user.entity.UserCk;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * description:
 * @author suwenguang@52tt.com
 * @date 2019/5/27
 * @version 1.0.0
 **/
public interface UserCkMapper {
    int selectTotal(@Param("record")UserCk userCk);

    List<UserCk> selectList();
}
