package cn.codeyang.pter.module.user.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author yangzy
 */
@Data
@ToString
@Mapper
public class SysUser implements Serializable {
    private Long id;
    private String nickName;
    private String username;
    @JsonIgnore
    private String password;
    private String loginIp;

    private LocalDate loginDate;

    private String avatar;


}
