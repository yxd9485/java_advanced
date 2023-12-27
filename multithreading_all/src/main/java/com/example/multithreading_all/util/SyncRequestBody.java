package com.example.multithreading_all.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * module: 应用模块名称<br/>
 * <p>
 * description: 描述<br/>
 *
 * @author XiaoDong.Yang
 * @date: 2023/12/11 18:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SyncRequestBody {

    String bottleInnerCode;
    String openId;
    String provinceCode;
    String provinceName;
    String cityCode;
    String cityName;
    String districtCode;
    String districtName;
    String longitude;
    String latitude;
}
