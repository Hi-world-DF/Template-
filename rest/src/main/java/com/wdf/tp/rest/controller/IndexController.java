package com.wdf.tp.rest.controller;

import com.wdf.tp.common.vo.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * index controller
 *
 * @author Defen Wang
 * @version 1.0, 2022/10/14 14:27
 * @since 1.0.0
 */
@RestController
@ApiIgnore
public class IndexController {
    @GetMapping("/")
    public ApiResult<String> index() {
        return ApiResult.success("Template Project Dashboard!");
    }

    @GetMapping("/ping")
    public ApiResult<String> ping() {
        return ApiResult.success();
    }
}
