package my.suveng.springboot.modules.user.entity;

import lombok.Data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author suwenguang
 * suveng@163.com
 * since 2019/3/19
 * description:
 **/
@Data
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String nickName;
    @Column(nullable = false)
    private String passWord;
    @Column(nullable = false)
    private String regTime;
    @Column(nullable = false)
    private String userName;

}
