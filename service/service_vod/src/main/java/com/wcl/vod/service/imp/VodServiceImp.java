/**
 * @Classname VodServiceImp
 * @Description TODO
 * @Date 2022/4/16 12:17
 * @Created by 28327
 */

package com.wcl.vod.service.imp;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.wcl.vod.service.VodService;
import com.wcl.vod.utils.InitClient;
import com.wcl.vod.utils.VodUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class VodServiceImp implements VodService {
    @Override
    public String uploadVideo(MultipartFile file) {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String originalFilename = file.getOriginalFilename();
        String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        UploadStreamRequest request =
                new UploadStreamRequest(VodUtil.KEYID, VodUtil.KEYSECRET, title, originalFilename, inputStream);
        /* 是否使用默认水印(可选)，指定模板组ID时，根据模板组配置确定是否使用默认水印*/
        //request.setShowWaterMark(true);
        /* 自定义消息回调设置 */
        //request.setUserData(""{\"Extend\":{\"test\":\"www\",\"localId\":\"xxxx\"},\"MessageCallback\":{\"CallbackURL\":\"http://demo.example.com\"}}"");
        /* 视频分类ID(可选) */
        //request.setCateId(0);
        /* 视频标签,多个用逗号分隔(可选) */
        //request.setTags("标签1,标签2");
        /* 视频描述(可选) */
        //request.setDescription("视频描述");
        /* 封面图片(可选) */
        //request.setCoverURL("http://cover.example.com/image_01.jpg");
        /* 模板组ID(可选) */
        //request.setTemplateGroupId("8c4792cbc8694e7084fd5330e56****");
        /* 工作流ID(可选) */
        //request.setWorkflowId("d4430d07361f0*be1339577859b0****");
        /* 存储区域(可选) */
        //request.setStorageLocation("in-201703232118266-5sejd****.oss-cn-shanghai.aliyuncs.com");
        /* 开启默认上传进度回调 */
        // request.setPrintProgress(true);
        /* 设置自定义上传进度回调 (必须继承 VoDProgressListener) */
        /*默认关闭。如果开启了这个功能，上传过程中服务端会在日志中返回上传详情。如果不需要接收此消息，需关闭此功能*/
        // request.setProgressListener(new PutObjectProgressListener());
        /* 设置应用ID*/
        //request.setAppId("app-100****");
        /* 点播服务接入点 */
        //request.setApiRegionId("cn-shanghai");
        /* ECS部署区域*/
        // request.setEcsRegionId("cn-shanghai");
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);

//        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
        if (!response.isSuccess()) {
            log.warn("上传错误" + response.getMessage());
            return "";
        } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            return response.getVideoId();
        }

    }

    @Override
    public Boolean removeVideo(String id) {
        DefaultAcsClient defaultAcsClient = null;
        try {
            defaultAcsClient = InitClient.initVodClient(VodUtil.KEYID, VodUtil.KEYSECRET);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        DeleteVideoRequest request = new DeleteVideoRequest();
        request.setVideoIds(id);
        DeleteVideoResponse acsResponse = null;
        try {
            acsResponse = defaultAcsClient.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if (ObjectUtils.isEmpty(acsResponse)) {
            return false;
        } else return true;
    }

    /**
     * 批量删除
     *
     * @param videoIdList
     * @return
     */
    @Override
    public Boolean removeVideoList(List<String> videoIdList) {
        DefaultAcsClient defaultAcsClient = null;
        try {
            defaultAcsClient = InitClient.initVodClient(VodUtil.KEYID, VodUtil.KEYSECRET);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        DeleteVideoRequest request = new DeleteVideoRequest();
        //1,2,3 转为这样
        String collect = videoIdList.stream().collect(Collectors.joining(","));
        request.setVideoIds(collect);
        DeleteVideoResponse acsResponse = null;
        try {
            acsResponse = defaultAcsClient.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if (ObjectUtils.isEmpty(acsResponse)) {
            return false;
        } else return true;
    }

    /**
     * 根据视频id获取视频凭证
     */
    @Override
    public String getAuthVideo(String id) {
        DefaultAcsClient defaultAcsClient = null;
        try {
            defaultAcsClient = InitClient.initVodClient(VodUtil.KEYID, VodUtil.KEYSECRET);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(id);
        GetVideoPlayAuthResponse response = null;
        try {
            response = defaultAcsClient.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if (response==null){
            return null;
        }
        return response.getPlayAuth();
    }
}