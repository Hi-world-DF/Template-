package com.wdf.tp.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Locale;

/**
 * UserFlagDto
 *
 * @author Defen Wang
 * @version 1.0, 2022/10/14 11:54
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserFlagDto {
    private Long userId;
    private RequestInfoDto requestInfo;
    private Locale locale;
}
