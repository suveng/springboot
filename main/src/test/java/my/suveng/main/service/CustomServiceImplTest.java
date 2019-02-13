package my.suveng.main.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import my.suveng.SpringbootApplicationTests;
import my.suveng.custom.entity.Custom;
import my.suveng.custom.service.impl.CustomServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.CipherSpi;
import java.util.*;
import java.util.function.Function;

/**
 * @author 苏文广 created at 2019/2/12
 */
public class CustomServiceImplTest extends SpringbootApplicationTests {

    @Autowired
    CustomServiceImpl customService;

    /**
     * boolean save(T entity);
     **/
    @Test
    public void testSave() {
        Custom save = new Custom();
        save.setTtAccount(UUID.randomUUID().toString());
        save.setWechat(UUID.randomUUID().toString());

        boolean res = customService.save(save);
        System.out.println(res);

    }


    /**
     * boolean saveBatch(T entity);
     **/
    @Test
    public void testSaveBatch() {
        ArrayList<Custom> customs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Custom insert = new Custom();
            insert.setTtAccount(UUID.randomUUID().toString());
            customs.add(insert);
        }
        boolean res = customService.saveBatch(customs);
        System.out.println(res);
    }


    /**
     * boolean saveOrUpdateBatch(Collection<T> entityList); 可以是个list可以单个
     **/
    @Test
    public void testSaveOrUpdateBatch() {

        Custom custom = new Custom();
        custom.setTtAccount(UUID.randomUUID().toString());
        custom.setCustomId(2);

        boolean b = customService.saveOrUpdate(custom);
        System.out.println(b);
    }


    /**
     * boolean removeById(Serializable id);
     **/
    @Test
    public void testRemoveById() {
        System.out.println(customService.getById(23));

        boolean b = customService.removeById(23);
        System.out.println(b);

        System.out.println(customService.getById(23));

    }


    /**
     * boolean removeByMap(Map<String, Object> columnMap);
     **/
    @Test
    public void testRemoveByMap() {
        QueryWrapper<Custom> condition = new QueryWrapper<Custom>().eq("name", "suveng");
        System.out.println(customService.list(condition));

        HashMap<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("name", "suveng");

        boolean b = customService.removeByMap(conditionMap);
        System.out.println(b);

        System.out.println(customService.list(condition));

    }


    /**
     * boolean remove(Wrapper<T> queryWrapper);
     **/
    @Test
    public void testRemove(){
        QueryWrapper<Custom> condition = new QueryWrapper<Custom>().eq("custom_id", 12);
        System.out.println(customService.list(condition));

        boolean remove = customService.remove(condition);
        System.out.println(remove);

        System.out.println(customService.list(condition));
    }


    /**
     * boolean removeByIds(Collection<? extends Serializable> idList);
     **/
    @Test
    public void testRemoveByIds(){
        List<Integer> idList = Arrays.asList(12, 32, 21, 42, 54);

        boolean b = customService.removeByIds(idList);
        System.out.println(b);
    }


    /**
     * boolean updateById(T entity);
     **/
    @Test
    public void testUpdateById(){
        Custom custom = customService.getById(44);
        System.out.println(custom);

        custom.setName("suveng");
        
        customService.updateById(custom);
        System.out.println(customService.getById(44));
    }


    /**
     * boolean update(T entity, Wrapper<T> updateWrapper);
     **/
    @Test
    public void testUpdate(){
        QueryWrapper<Custom> condition = new QueryWrapper<Custom>().eq("name", "suveng");

        System.out.println(customService.list(condition));

        Custom update = new Custom();
        update.setName("hello");
        update.setWechat("wechat");

        customService.update(update,condition);

        System.out.println(customService.list(condition));
    }


    /**
     * boolean updateBatchById(Collection<T> entityList, int batchSize);
     **/
    @Test
    public void testUpdateBatchById(){
        ArrayList<Custom> customs = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Custom custom = new Custom();
            custom.setCustomId(i);
            custom.setName("suveng");
            custom.setTtAccount(UUID.randomUUID().toString());
            customs.add(custom);
        }

        boolean b = customService.updateBatchById(customs, 5);
        System.out.println(b);

    }


    /**
     * boolean saveOrUpdate(T entity);
     **/
    @Test
    public void testSaveOrUpdate(){
        System.out.println(customService.getById(23));

        customService.removeById(23);

        Custom custom = new Custom();
        custom.setTtAccount(UUID.randomUUID().toString());
//        custom.setCustomId(23);
        customService.saveOrUpdate(custom);
    }


    /**
     * T getById(Serializable id);
     **/
    @Test
    public void testGetById(){
        System.out.println(customService.getById(23));
    }


    /**
     * Collection<T> listByIds(Collection<? extends Serializable> idList);
     **/
    @Test
    public void  testListByIds(){
        List<Integer> ids = Arrays.asList(123, 213, 231, 324);

        System.out.println(customService.listByIds(ids));
    }


    /**
     * Collection<T> listByMap(Map<String, Object> columnMap);
     **/
    @Test
    public void testListByMap(){
        HashMap<String, Object> condition = new HashMap<>();
        condition.put("name", "suveng");

        Collection<Custom> customs = customService.listByMap(condition);
        System.out.println(customs);
    }
    
    /**
     * T getOne(Wrapper<T> queryWrapper, boolean throwEx);  有多个 result 是否抛出异常
     **/
    @Test
    public void testGetOne(){
        QueryWrapper<Custom> condition = new QueryWrapper<Custom>().eq("name", "suveng");

        System.out.println(customService.getOne(condition));
    }


    /**
     * Map<String, Object> getMap(Wrapper<T> queryWrapper); 取一条
     **/
    @Test
    public void testGetMap(){
        QueryWrapper<Custom> eq = new QueryWrapper<Custom>().eq("name", "suveng");
        Map<String, Object> map = customService.getMap(eq);

        System.out.println(map);
    }


    /**
     * Object getObj(Wrapper<T> queryWrapper); 取一条 返回第一个字段值
     **/
    @Test
    public void testGetObj(){
        QueryWrapper<Custom> eq = new QueryWrapper<Custom>().eq("name", "suveng");
        Object obj = customService.getObj(eq, Function.identity());
        System.out.println(obj);
    }

    
    /**
     * int count(Wrapper<T> queryWrapper);
     **/
    @Test
    public void testCount(){
        int count = customService.count();
        System.out.println(count);

        QueryWrapper<Custom> eq = new QueryWrapper<Custom>().eq("name", "suveng");
        count=customService.count(eq);
        System.out.println(count);
    }


    /**
     * List<T> list(Wrapper<T> queryWrapper);
     **/
    @Test
    public void testList(){
        List<Custom> list = customService.list();
        System.out.println(list);

        QueryWrapper<Custom> eq = new QueryWrapper<Custom>().eq("name", "suveng");
        System.out.println(customService.list(eq));
    }


    /**
     * IPage<T> page(IPage<T> page, Wrapper<T> queryWrapper);
     **/
    @Test
    public void testPage(){
        System.out.println(customService.page(new Page<Custom>(1, 100)).getRecords());

        System.out.println(customService.page(new Page<>(1, 100), new QueryWrapper<Custom>().eq("name", "suveng")).getRecords());
    }


    /**
     * List<Map<String, Object>> listMaps(Wrapper<T> queryWrapper);
     **/
    @Test
    public void testListMaps(){
        List<Map<String, Object>> maps = customService.listMaps();
        System.out.println(maps);

        QueryWrapper<Custom> eq = new QueryWrapper<Custom>().eq("name", "suveng");
        maps=customService.listMaps(eq);
        System.out.println(maps);
    }


    /**
     * List<Object> listObjs(Wrapper<T> queryWrapper); object 需要强转
     **/
    @Test
    public void testListObjs(){
        List<Object> objects = customService.listObjs();
        System.out.println(objects);//object 需要强转
    }


    /**
     * IPage<Map<String, Object>> pageMaps(IPage<T> page, Wrapper<T> queryWrapper);
     **/
    @Test
    public void testPageMaps(){
        System.out.println(customService.pageMaps(new Page<>(1, 100), new QueryWrapper<Custom>().eq("name", "suveng")).getRecords());
    }
}
