package com.wdf.tp.rest.controller;

import com.wdf.tp.common.vo.ApiResult;
import com.wdf.tp.rest.controller.vo.TestVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;

/**
 * 测试Controller
 *
 * @author Defen Wang
 * @version 1.0, 2022/10/14 15:30
 * @since 1.0.0
 */
@Api(value = "测试管理", tags = "测试管理")
@RestController
@RequestMapping
@RequiredArgsConstructor
public class TestController {
    @Lazy
    @Resource(name = "servletBizExecutor")
    private Executor executor;

    @GetMapping("/tests")
    @ApiOperation("查询所有效果对比")
    public CompletionStage<ApiResult<List<TestVO>>> query() {
        return CompletableFuture.supplyAsync(this::getList, executor)
            .thenApply(ApiResult::success);
    }

    private List<TestVO> getList() {
        return Arrays.asList(TestVO.builder().version("Test1").build(),
            TestVO.builder().version("Test2").build());
    }
}
