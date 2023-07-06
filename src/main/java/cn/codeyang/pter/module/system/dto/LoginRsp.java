package cn.codeyang.pter.module.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yangzy
 */
@Data
@AllArgsConstructor
public class LoginRsp implements Serializable {
    private String token;
}
