/**
 * @Classname FIleServiceImp
 * @Description TODO
 * @Date 2022/4/8 15:42
 * @Created by 28327
 */

package com.wcl.oss.service.imp;

import cn.hutool.core.date.DateUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import com.aliyun.oss.OSSException;
import com.aliyuncs.exceptions.ClientException;
import com.wcl.oss.service.FileService;
import com.wcl.oss.utils.CUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class FIleServiceImp implements FileService {
    /**
     * 实现文件上传，返回字符串url
     *
     * @param file
     * @return
     */
    @Override
    public String uploadFile(MultipartFile file) {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = CUtil.ENDPOINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = CUtil.KEYID;
        String accessKeySecret = CUtil.KEYSECRET;
        // 填写Bucket名称，例如examplebucket。
        String bucketName = CUtil.BUCKETNAME;
        log.info(accessKeyId);
//        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
//        String objectName = "exampledir/exampleobject.txt";
//        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
//        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
//        String filePath = "D:\\localpath\\examplefile.txt";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replace("-", "");

            String format = DateUtil.format(new Date(), "yyyy/MM/dd");
            String objectName = format + "/" + uuid + originalFilename;
            // 创建PutObject请求。
            ossClient.putObject(bucketName, objectName, inputStream);
            //拼接url
//            https://yueli14gulixueyuan.oss-cn-chengdu.aliyuncs.com/w.png
            String url = "https://" + bucketName + "." + endpoint + "/" + objectName;
            return url;

        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return null;
    }

}