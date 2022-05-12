package com.wcl.serviceedu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wcl.serviceedu.entity.EduSubjectEntity;
import com.wcl.serviceedu.entity.excel.SubjectExcel;
import com.wcl.serviceedu.entity.vo.OneReclassify;
import com.wcl.serviceedu.mapper.EduSubjectMapper;
import com.wcl.serviceedu.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author wcl
 * @since 2022-04-08
 */
@Service
public class EduSubjectServiceImp extends ServiceImpl<EduSubjectMapper, EduSubjectEntity> implements EduSubjectService {

    @Override
    public void importExcel(MultipartFile file, EduSubjectService subjectService) {
        try {
            //实体类集合,批量插入
            ArrayList<EduSubjectEntity> list = new ArrayList<>();
            InputStream inputStream = file.getInputStream();
            //监听类不能被spring管理，所以传参service写入数据库
            EasyExcel.read(inputStream, SubjectExcel.class, new AnalysisEventListener<SubjectExcel>() {
                //一行一行去读取excel内容，每一行都是excel实体类
                @Override
                public void invoke(SubjectExcel subjectExcel, AnalysisContext analysisContext) {
                    if (subjectExcel == null) {
                        return;
                    }
                    //一级分类
                    EduSubjectEntity exitOneClassName = this.exitOneClassName(subjectExcel.getOneClassName());
                    //判断,与数据库比对之后，如有没有相同的，则返回值一定为空，那么这一行，一定可以存入
                    if (exitOneClassName == null) {
                        EduSubjectEntity E = new EduSubjectEntity();
                        E.setParentId("0");
                        E.setTitle(subjectExcel.getOneClassName());
                        list.add(E);
                    }
                    //二级分类
                    //一级分类匹配上的对象就是二级分类的pid
                    String pid = exitOneClassName.getId();
                    EduSubjectEntity exitTwoClassName = this.exitTwoClassName(subjectExcel.getTwoClassName(), pid);
                    //判断二级分类是否存在
                    if (exitTwoClassName == null) {
                        EduSubjectEntity E = new EduSubjectEntity();
                        E.setParentId("0");
                        E.setTitle(subjectExcel.getTwoClassName());
                        E.setParentId(pid);
                        list.add(E);
                    }
                    //插入数据库
                    subjectService.saveBatch(list);
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext analysisContext) {

                }

                //判断一级分类是否重复
                private EduSubjectEntity exitOneClassName(String title) {
                    QueryWrapper<EduSubjectEntity> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("title", title);
                    queryWrapper.eq("parent_id", 0);
                    return subjectService.getOne(queryWrapper);

                }

                //判断二级分类是否重复
                private EduSubjectEntity exitTwoClassName(String title, String pid) {
                    QueryWrapper<EduSubjectEntity> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("title", title);
                    queryWrapper.eq("parent_id", pid);
                    return subjectService.getOne(queryWrapper);

                }
            }).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List getAll() {
        ArrayList<OneReclassify> ls = new ArrayList<>();
        //查询一级分类
        QueryWrapper<EduSubjectEntity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("parent_id", 0);
        List<EduSubjectEntity> list1 = baseMapper.selectList(queryWrapper1);
        //查询二级分类
        QueryWrapper<EduSubjectEntity> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.ne("parent_id", 0);
        List<EduSubjectEntity> list2 = baseMapper.selectList(queryWrapper2);
        //父类注入的

        for (EduSubjectEntity eduSubjectEntity : list1) {
            OneReclassify reclassify1 = new OneReclassify();
            BeanUtils.copyProperties(eduSubjectEntity, reclassify1);
            for (EduSubjectEntity eduSubjectEntity2 : list2) {
                //判断是否属于当前分类下
                if (reclassify1.getId().equals(eduSubjectEntity2.getParentId())) {
                    OneReclassify reclassify2 = new OneReclassify();
                    BeanUtils.copyProperties(eduSubjectEntity2, reclassify2);
                    reclassify1.getChildren().add(reclassify2);
                }
            }
            ls.add(reclassify1);
        }
        return ls;
    }
}
