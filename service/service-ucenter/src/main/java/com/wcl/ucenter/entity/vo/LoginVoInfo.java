/**
 * @Classname LoginVoInfo
 * @Description TODO
 * @Date 2022/4/23 13:54
 * @Created by 28327
 */

package com.wcl.ucenter.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginVoInfo {
    private String password;
    private String mobile;
}