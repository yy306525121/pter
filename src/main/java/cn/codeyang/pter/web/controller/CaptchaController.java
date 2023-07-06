package cn.codeyang.pter.web.controller;

import cn.codeyang.pter.common.core.constant.CacheConstants;
import cn.codeyang.pter.common.core.constant.CommonConstants;
import cn.codeyang.pter.common.core.util.R;
import cn.codeyang.pter.module.system.service.SysConfigService;
import cn.codeyang.pter.module.system.dto.CaptchaRspDto;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import com.google.code.kaptcha.Producer;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author yangzy
 */
@RestController
@RequiredArgsConstructor
public class CaptchaController {
    private final RedisTemplate<String, Object> redisTemplate;
    private final SysConfigService configService;
    private final Producer captchaProducerMath;
    private Producer captchaProducer;

    // 验证码类型
    @Value("${pter.captchaType}")
    private String captchaType;
    public static final String IMG_PREFIX = "data:image/gif;base64,";


    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public R<CaptchaRspDto> getCode(HttpServletResponse response) throws IOException {
        CaptchaRspDto rsp = new CaptchaRspDto();

        boolean captchaEnabled = configService.selectCaptchaEnabled();
        rsp.setCaptchaEnabled(captchaEnabled);
        if (!captchaEnabled) {
            return R.ok(rsp);
        }

        // 保存验证码信息
        String uuid = IdUtil.simpleUUID();
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;

        String capStr = null, code = null;
        BufferedImage image = null;

        // 生成验证码
        if ("math".equals(captchaType)) {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        } else if ("char".equals(captchaType)) {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }

        redisTemplate.opsForValue().set(verifyKey, code, CommonConstants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            return R.failed("验证码创建失败");
        }
        rsp.setUuid(uuid);
        rsp.setImg(IMG_PREFIX + Base64.encode(os.toByteArray()));
        return R.ok(rsp);
    }
}
