package com.niit.niitphone.api.topPhone.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 章卜
 * @date 2021/1/9 5:31 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneName implements Serializable {

    private String phoneName;
    private int number;
}
