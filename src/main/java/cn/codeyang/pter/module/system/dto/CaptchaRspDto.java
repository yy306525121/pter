package cn.codeyang.pter.module.system.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yangzy
 */
@Data
public class CaptchaRspDto implements Serializable {

    private static final long serialVersionUID = 2209701471088534726L;

    private boolean captchaEnabled;

    private String uuid;

    private String img;





}
