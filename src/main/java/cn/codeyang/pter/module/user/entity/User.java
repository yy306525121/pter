package cn.codeyang.pter.module.user.entity;


import lombok.Data;
import lombok.ToString;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author yangzy
 */
@Data
@ToString
@Mapper
public class User implements Serializable {
    private Long id;
    private String username;
    private String password;
}
