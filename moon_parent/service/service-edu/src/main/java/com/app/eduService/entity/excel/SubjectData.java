package com.app.eduService.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class SubjectData {

    @ExcelProperty(index = 0)
    private String ontSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
