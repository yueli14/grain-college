/**
 * @Classname InitClient
 * @Description TODO
 * @Date 2022/4/16 14:07
 * @Created by 28327
 */

package com.wcl.msm.utils;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;

public class InitClient {

    public static IAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
}