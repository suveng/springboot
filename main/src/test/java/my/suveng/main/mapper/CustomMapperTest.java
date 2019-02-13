package my.suveng.main.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import my.suveng.custom.entity.Custom;
import my.suveng.custom.mapper.CustomMapper;
import my.suveng.SpringbootApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @author 苏文广 created at 2019/2/12
 */

public class CustomMapperTest extends SpringbootApplicationTests {
    @Autowired
    private CustomMapper customMapper;


    /**
     * int insert(T entity);
     */
//    @Test
//    public void testInsert() {
//        Custom entity = new Custom();
//        entity.setCustomId(20000);//因为id 是自增的所以设置了主键也不生效
//        entity.setName("suveng");
//        entity.setPhone("22222222");
//        entity.setTtAccount(new UUID(32L, 16L).toString());//这个字段有唯一索引
//        int insert = customMapper.insert(entity);
//        System.out.println(insert);
//    }

    /**
     * int deleteById(Serializable id);
     **/
    @Test
    public void testDeleteById() {
        int i = customMapper.deleteById(10001);
        System.out.println(i);
    }


    /**
     * int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
     **/
    @Test
    public void testDeleteByMap() {
        //删除条件 string 为表字段，object为表字段的值
        HashMap<String, Object> condition = new HashMap<>();
        condition.put("custom_id", 10000);

        //打印要删除的记录
        System.out.println(customMapper.selectOne(new QueryWrapper<Custom>().eq("custom_id", 10000)));

        //执行删除
        int i = customMapper.deleteByMap(condition);
        //打印删除结果
        System.out.println(i);

        //查询已删除的记录
        System.out.println(customMapper.selectOne(new QueryWrapper<Custom>().eq("custom_id", 10000)));
    }


    /**
     * int delete(@Param(Constants.WRAPPER) Wrapper<T> wrapper);
     **/
    @Test
    public void testDelete() {
        //打印符合删除条件记录
        QueryWrapper<Custom> eq = new QueryWrapper<Custom>().eq("name", "颜涛");
        System.out.println(customMapper.selectList(eq));

        //执行删除
        int delete = customMapper.delete(eq);
        System.out.println(delete);

        //再次查询，验证
        System.out.println(customMapper.selectList(eq));
    }


    /**
     * int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
     **/
    @Test
    public void testDeleteBatchIds() {
        //需要删除的主键集合
        List<Long> ids = Arrays.asList(1L, 2L, 3L, 4L, 5L);
        System.out.println(customMapper.selectBatchIds(ids));

        //执行删除
        int i = customMapper.deleteBatchIds(ids);
        System.out.println(i);

        //再次打印验证
        System.out.println(customMapper.selectBatchIds(ids));
    }


    /**
     * int updateById(@Param(Constants.ENTITY) T entity);
     **/
    @Test
    public void testUpdateById() {
        //先查询
        Custom x = customMapper.selectById(111);
        System.out.println(x);
        //改变
        x.setName("suveng");
        //更新
        int update = customMapper.updateById(x);
        System.out.println(update);
        //查询验证
        System.out.println(customMapper.selectById(111));
    }


    /**
     * int update(@Param(Constants.ENTITY) T entity, @Param(Constants.WRAPPER) Wrapper<T> updateWrapper);
     **/
    @Test
    public void testUpdate() {
        //更新的custom
        Custom update = new Custom();
        update.setName("suveng");
        //查询条件
        QueryWrapper<Custom> eq = new QueryWrapper<Custom>().eq("name", "suwenguang");
        //查询符合更新条件的记录
        List<Custom> customs = customMapper.selectList(eq);
        System.out.println(customs);
        //执行更新
        int res = customMapper.update(update, eq);
        System.out.println(res);
        //查询验证
        System.out.println(customMapper.selectList(eq));
    }


    /**
     * T selectById(Serializable id);
     **/
    @Test
    public void testSelectById() {
        Custom custom = customMapper.selectById(12);
        System.out.println(custom);
    }


    /**
     * List<T> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
     **/
    @Test
    public void testSelectBatchIds() {
        //batchIds 批量主键
        List<Integer> integers = Arrays.asList(112, 221, 31, 23);
        //查询
        List<Custom> customs = customMapper.selectBatchIds(integers);
        System.out.println(customs);

    }


    /**
     * List<T> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
     **/
    @Test
    public void testSelectByMap() {
        //map 为<字段名,字段值>
        HashMap<String, Object> condition = new HashMap<>();
        condition.put("name", "suveng");

        List<Custom> customs = customMapper.selectByMap(condition);
        System.out.println(customs);
    }


    /**
     * T selectOne(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper); 只能查询一条，如果记录大于两条则抛异常
     **/
    @Test
    public void testSelectOne() {
        QueryWrapper<Custom> queryWrapper = new QueryWrapper<Custom>().eq("name", "123");
        Custom custom = customMapper.selectOne(queryWrapper);
        System.out.println(custom);
    }


    /**
     * Integer selectCount(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
     **/
    @Test
    public void testSelectCount() {
        Integer total = customMapper.selectCount(null);
        System.out.println(total);

        QueryWrapper<Custom> queryWrapper = new QueryWrapper<Custom>().eq("name", "suveng");
        Integer eq = customMapper.selectCount(queryWrapper);
        System.out.println(eq);
    }


    /**
     * List<T> selectList(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
     **/
    @Test
    public void testSelectList() {
        List<Custom> customAll = customMapper.selectList(null);
        System.out.println(customAll);

        QueryWrapper<Custom> eq = new QueryWrapper<Custom>().eq("name", "suveng");
        List<Custom> customs = customMapper.selectList(eq);
        System.out.println(customs);
    }


    /**
     * List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
     **/
    @Test
    public void testSelectMaps() {
        List<Map<String, Object>> maps = customMapper.selectMaps(null);
        System.out.println(maps);
    }


    /**
     * List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper); 只返回第一个字段的值
     **/
    @Test
    public void testSelectObjs() {
        QueryWrapper<Custom> eq = new QueryWrapper<Custom>().eq("name", "suveng");
        List<Object> objects = customMapper.selectObjs(eq);
        System.out.println(objects);
    }


    /**
     * IPage<T> selectPage(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
     **/
    @Test
    public void testSelectPage() {
        Page<Custom> customPage = new Page<Custom>(2, 1000);
        IPage<Custom> customIPage = customMapper.selectPage(customPage, null);
        System.out.println(customIPage);
    }


    /**
     * IPage<Map<String, Object>> selectMapsPage(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
     **/
    @Test
    public void testSelectMapsPage() {
        Page<Custom> page = new Page<>(1, 100);
        System.out.println(customMapper.selectMapsPage(page, null));

        QueryWrapper<Custom> condition = new QueryWrapper<Custom>().eq("name", "suveng");
        Page<Custom> page1 = new Page<>(1, 100);
        System.out.println(customMapper.selectMapsPage(page1, condition));
    }
}