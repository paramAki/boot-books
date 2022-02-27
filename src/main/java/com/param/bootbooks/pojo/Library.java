package com.param.bootbooks.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhoujingyu（976944083@qq.com）
 * @date 2022/2/26 11:30 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Library {
    @TableId
    @TableField("LibraryID")
    private Integer libraryID;
    @TableField("LibraryName")
    private String libraryName;
    @TableField("LibraryAddress")
    private String libraryAddress;
    @TableField("LibraryGrade")
    private Integer libraryGrade;
}
