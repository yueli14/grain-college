/**
 * @Classname RegisterVoInfo
 * @Description TODO
 * @Date 2022/4/23 14:19
 * @Created by 28327
 */

package com.wcl.ucenter.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RegisterVoInfo {
    private String password;
    private String mobile;
    private String nickName;
    private String code;
}