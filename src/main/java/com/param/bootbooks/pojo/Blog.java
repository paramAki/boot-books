package com.param.bootbooks.pojo;

import lombok.Data;

/**
 * @author zhoujingyu（976944083@qq.com）
 * @date 2022/3/5 5:53 PM
 */
@Data
public class Blog {
    private Long id;
    private String title;
    private String author;
    private String content;
}
