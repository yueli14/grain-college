/**
 * @Classname VodClientImp
 * @Description TODO
 * @Date 2022/4/18 18:16
 * @Created by 28327
 */

package com.wcl.serviceedu.client.imp;

import com.wcl.serviceedu.client.VodClient;
import com.wcl.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class VodClientImp implements VodClient {
    @Override
    public R deleteVideo(String id) {
        return R.error().message("降级进行中");
    }
    @Override
    public R deleteVideoByBatch(List<String> videoId) {
        return R.error().message("降级进行中");
    }
}