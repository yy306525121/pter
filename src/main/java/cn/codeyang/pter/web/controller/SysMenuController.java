package cn.codeyang.pter.web.controller;

import cn.codeyang.pter.common.core.util.R;
import cn.codeyang.pter.module.menu.dto.SysMenuAddReqDto;
import cn.codeyang.pter.module.menu.service.SysMenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangzy
 */
@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class SysMenuController {
    private final SysMenuService sysMenuService;

    @PostMapping("/add")
    public R<Boolean> add(@RequestBody @Valid SysMenuAddReqDto reqDto) {
        System.out.println(reqDto);
        return R.ok(true);
    }
}
