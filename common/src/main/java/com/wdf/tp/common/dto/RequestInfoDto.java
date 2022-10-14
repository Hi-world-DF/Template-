package com.wdf.tp.common.dto;

import com.wdf.tp.common.pojo.DeviceEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 请求身份数据
 *
 * @author Defen Wang
 * @version 1.0, 2022/10/14 11:53
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RequestInfoDto {
    private String ip;
    private String userAgent;
    private String uuid;
    private DeviceEnum device;
}
