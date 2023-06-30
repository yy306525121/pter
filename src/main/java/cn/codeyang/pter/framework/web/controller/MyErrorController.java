package cn.codeyang.pter.framework.web.controller;

import cn.codeyang.pter.common.core.util.R;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangzy
 */
@RestController
public class MyErrorController implements ErrorController {
    @RequestMapping(value = "/error")
    public ResponseEntity<R> error() {
        R<Object> r = R.failed(null, 404, "页面未找到");
        return new ResponseEntity<R>(r, HttpStatus.NOT_FOUND);
    }
}
