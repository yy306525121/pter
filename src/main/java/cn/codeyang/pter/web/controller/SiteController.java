package cn.codeyang.pter.web.controller;

import cn.codeyang.pter.common.core.util.R;
import cn.codeyang.pter.module.site.dto.SiteAddReqDto;
import cn.codeyang.pter.module.site.entity.Site;
import cn.codeyang.pter.module.site.service.SiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangzy
 */
@RestController
@RequestMapping("/site")
@RequiredArgsConstructor
public class SiteController {
    private final SiteService siteService;


    @PostMapping("/save")
    public R<Boolean> save(@RequestBody SiteAddReqDto reqDto) {

        Site site = new Site();
        site.setName(reqDto.getName());
        site.setCookie(reqDto.getCookie());
        site.setCron(reqDto.getCron());
        site.setEnable(reqDto.getEnable());
        site.setPriority(reqDto.getPriority());
        siteService.save(site);
        return R.ok();
    }
}
