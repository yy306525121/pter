package cn.codeyang.pter.module.user.domain;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author yangzy
 */
@Data
@ToString
public class User implements Serializable {
    private Long id;
    private String username;
    private String password;
}
