/**
 * @Classname SentinelGlobalHandler
 * @Description 服务熔断
 * @Date 2022/4/18 16:19
 * @Created by 28327
 */

package com.wcl.servicebase.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wcl.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SentinelGlobalHandler {
    public static R handE(BlockException ex) {
        log.warn(ex.getMessage());
        return R.error().message("sentinel进行中,blockhandler");
    }
    public static R handET(BlockException ex) {
        log.warn(ex.getMessage());
        return R.error().message("sentinel进行中,fallhandler");
    }
}