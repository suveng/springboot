package my.suveng.main.service;

import my.suveng.SpringbootApplicationTests;
import my.suveng.custom.entity.Custom;
import my.suveng.custom.service.impl.CustomServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.CipherSpi;
import java.util.ArrayList;
import java.util.UUID;

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

}
