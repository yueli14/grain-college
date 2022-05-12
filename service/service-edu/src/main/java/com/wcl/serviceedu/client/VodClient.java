package com.wcl.serviceedu.client;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.wcl.servicebase.handler.SentinelGlobalHandler;
import com.wcl.serviceedu.client.imp.VodClientImp;
import com.wcl.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(value = "service-vod", path = "/eduvod/video",fallback = VodClientImp.class)
public interface VodClient {
    @DeleteMapping("delete/{id}")
    R deleteVideo(@PathVariable(value = "id") String id);
    @DeleteMapping("delete/batch")
    R deleteVideoByBatch(@RequestParam(value = "videoId") List<String> videoId);
}
