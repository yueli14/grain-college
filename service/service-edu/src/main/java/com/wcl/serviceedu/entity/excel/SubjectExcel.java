/**
 * @Classname SubjectExcel
 * @Description TODO
 * @Date 2022/4/8 23:01
 * @Created by 28327
 */

package com.wcl.serviceedu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class SubjectExcel {
    @ExcelProperty(index = 0)
    private String oneClassName;
    @ExcelProperty(index = 1)
    private String twoClassName;
}