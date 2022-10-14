package com.wdf.tp.rest.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TestVO
 *
 * @author Defen Wang
 * @version 1.0, 2022/10/14 15:31
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("测试VO")
public class TestVO {
    /** 测试名称 */
    @ApiModelProperty(value = "测试版本名称", example = "Test1")
    private String version;
}
