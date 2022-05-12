/**
 * @Classname MsmServiceImp
 * @Description TODO
 * @Date 2022/4/22 19:44
 * @Created by 28327
 */

package com.wcl.msm.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.google.gson.Gson;
import com.wcl.msm.service.MsmService;
import com.wcl.msm.utils.InitClient;
import com.wcl.msm.utils.VodUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;


@Service
public class MsmServiceImp implements MsmService {
    @Override
    public Boolean send(String code, String phone) {
        IAcsClient iAcsClient = null;
        SendSmsResponse response = null;
        try {
            iAcsClient = InitClient.initVodClient(VodUtil.KEYID, VodUtil.KEYSECRET);
        } catch (ClientException e) {
            e.printStackTrace();
        }

        SendSmsRequest request = new SendSmsRequest();
        request.setSignName("阿里云短信测试");
        request.setTemplateCode("SMS_154950909");
        request.setPhoneNumbers(phone);
        request.setTemplateParam(code);

        try {
            response = iAcsClient.getAcsResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
        System.out.println(response.getBizId() + "|" + response.getRequestId() + "|" + response.getCode() + "|" +
                response.getMessage());
        if (ObjectUtils.isEmpty(response)) {
            return false;
        }
        return true;
    }
}