package my.suveng.springboot.modules.user.entity;

import lombok.Data;


import javax.persistence.*;
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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String regTime;
    @Column(nullable = false)
    private String username;

}
