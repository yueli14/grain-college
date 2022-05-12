/**
 * @Classname MPHandler
 * @Description TODO
 * @Date 2022/4/3 21:20
 * @Created by 28327
 */

package com.wcl.servicebase.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Component

public class MPHandler implements MetaObjectHandler {
    /**
     * 插入元对象字段填充（用于插入时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill");
        this.strictInsertFill(metaObject, "gmtCreate",
                Date.class, new Date()); // 起始版本 3.3.3(推荐)
        this.strictInsertFill(metaObject, "gmtModified",
                Date.class, new Date()); // 起始版本 3.3.3(推荐)
    }

    /**
     * 更新元对象字段填充（用于更新时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        //有坑，当开启乐观锁的时候，将不进行自动填充
//        this.fillStrategy(metaObject,"updateTime",new Date());

        this.strictUpdateFill(metaObject, "gmtModified", Date.class, new Date());
        //有坑，与属性字段类型不相同
//        this.strictUpdateFill(metaObject, "gmtModified",
//                () -> LocalDateTime.now(), LocalDateTime.class); // 起始版本 3.3.3(推荐)
    }
}