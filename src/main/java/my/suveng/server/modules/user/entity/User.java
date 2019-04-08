package my.suveng.server.modules.user.entity;

import lombok.Data;
import my.suveng.server.common.poi.annotation.ExcelEntity;
import my.suveng.server.common.poi.annotation.ExcelField;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author suwenguang
 * suveng@163.com
 * since 2019/3/19
 * description:
 **/
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@ExcelEntity(crosswalk = true, headRowNum = 2, headColumnNum = 1)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ExcelField(name = "id")
    private Long id;

    @Column(columnDefinition = "varchar(20) not null default ''")
    @ExcelField(name = "username")
    private String username;

    @Column(columnDefinition = "varchar(64) not null default ''")
    @ExcelField(name = "password")
    private String password;

    @Column(columnDefinition = "varchar(20) not null default ''")
    private String email;

    @Column(columnDefinition = "varchar(20)  default ''")
    private String phone;

    @CreatedDate
    @Column(columnDefinition = "TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP")
    private Date creteTime;

    @LastModifiedDate
    @Column(nullable = false,columnDefinition = "TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTime;


}
