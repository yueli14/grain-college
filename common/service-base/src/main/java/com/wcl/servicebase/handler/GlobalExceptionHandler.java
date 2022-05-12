/**
 * @Classname GlobalExceptionHandler
 * @Description TODO
 * @Date 2022/4/3 22:13
 * @Created by 28327
 */

package com.wcl.servicebase.handler;

import com.wcl.utils.R;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//全局异常处理
@ControllerAdvice

@Slf4j
public class GlobalExceptionHandler {
    //出现什么异常时执行
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R errorHandler(Exception e){
        e.printStackTrace();
        //打印异常信息到日志文件
        log.error(e.getMessage());
        return R.error().message("程序出现了异常");
    }
//    //特点异常处理
//    @ExceptionHandler(ArithmeticException.class)
//    @ResponseBody
//    public R error(ArithmeticException e){
//        e.printStackTrace();
//        return R.error().message("执行了自定义异常");
//    }
    //自定义异常
    /*
        @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class GuliException extends RuntimeException {
        @ApiModelProperty(value = "状态码")
        private Integer code;
        private String msg;

    }
    // 出现异常时，抛出类
    try {
        int a = 10/0;
    }catch(Exception e) {
        throw new GuliException(20001,"出现自定义异常");
    }
//    自定义异常处理器
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R error(GuliException e){
        e.printStackTrace();
        return R.error().message(e.getMsg()).code(e.getCode());
    }
     */
}