package my.suveng.springboot.modules.user.entity;

import lombok.Data;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username="suveng";

    @Column(nullable = false)
    private String password= MD5Encoder.encode("1234".getBytes());

    @Column(nullable = false)
    private String email="suveng@163.com";

    @Column(nullable = false)
    private String phone= "0000";

    @CreatedDate
    @Column(nullable = false)
    private String creteTime;

    @LastModifiedDate
    @Column(nullable = false)
    private String modifiedTime;


}
