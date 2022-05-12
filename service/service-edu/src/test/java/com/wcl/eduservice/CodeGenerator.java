/**
 * @Classname CodeGenerator
 * @Description TODO
 * @Date 2022/4/1 15:15
 * @Created by 28327
 */

package com.wcl.eduservice;


import com.baomidou.mybatisplus.annotation.IdType;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.Entity;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import org.junit.Test;

import java.util.Collections;
import java.util.function.Consumer;

public class CodeGenerator {
    @Test
    public void test() {

        String projectPath = System.getProperty("user.dir");
        DataSourceConfig.Builder dataSourceConfig = new DataSourceConfig.Builder
                ("jdbc:mysql://127.0.0.1:3306/gulixueyuan?serverTimezone=GMT%2B8", "root", "123456")
                .typeConvert(new MySqlTypeConvert())
                .keyWordsHandler(new MySqlKeyWordsHandler());
        FastAutoGenerator fastAutoGenerator = FastAutoGenerator.create(dataSourceConfig);
        fastAutoGenerator.globalConfig(builder -> builder.fileOverride()
                .outputDir(projectPath + "/src/main/java")
                .author("wcl")
                .fileOverride()
//                .enableSwagger()
                .dateType(DateType.ONLY_DATE)
                .commentDate("yyyy-MM-dd"));
        fastAutoGenerator.packageConfig(builder -> builder.parent("com.wcl")
                .moduleName("serviceedu")
                .entity("entity")
                .service("service")
                .serviceImpl("service.impl")
                .mapper("mapper")
                .xml("mapper.xml")
                .controller("controller")
                .other("other")
        );
        fastAutoGenerator.strategyConfig(builder -> builder.enableCapitalMode()
                        .enableSkipView()
                        .disableSqlFilter()
                        .addInclude("edu_comment") //表名
//                .likeTable(new LikeTable("edu_teacher"))
                        .addTablePrefix("t_", "c_")
                        .addFieldSuffix("_flag")
                        .entityBuilder()
                        .disableSerialVersionUID()
                        .enableChainModel()
                        .enableLombok()
                        .enableRemoveIsPrefix()
                        .enableTableFieldAnnotation()
                        .enableActiveRecord()
                        .versionColumnName("version")
                        .versionPropertyName("version")
                        .logicDeleteColumnName("deleted")
                        .logicDeletePropertyName("deleteFlag")
                        .columnNaming(NamingStrategy.underline_to_camel)
                        .idType(IdType.ASSIGN_ID)
                        .formatFileName("%sEntity")
                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImp")
                        .controllerBuilder()
                        .enableRestStyle()
                        .mapperBuilder()
                        .enableMapperAnnotation()
        );
        fastAutoGenerator.templateConfig(builder -> builder
                .entity("/templates/entity.java")
                .service("/templates/service.java")
                .serviceImpl("/templates/serviceImpl.java")
                .mapper("/templates/mapper.java")
                .mapperXml("/templates/mapper.xml")
                .controller("/templates/controller.java")
        );
//        fastAutoGenerator.templateEngine(new FreemarkerTemplateEngine());
        fastAutoGenerator.execute();

    }
}