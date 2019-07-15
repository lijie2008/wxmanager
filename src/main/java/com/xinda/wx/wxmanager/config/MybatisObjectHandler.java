/**
 * Copyright (C), 2019, 兆尹
 * FileName: MybatisObjectHandler
 * Author:   lijie
 * Date:     2019/7/12 19:16
 * Description: 公共字段自动填充
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xinda.wx.wxmanager.config;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈公共字段自动填充〉
 *
 * @author lijie
 * @create 2019/7/12
 * @since 1.0.0
 */
@Component
public class MybatisObjectHandler extends MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        //新增时填充的字段
        setFieldValByName("tmCreatetime", new Date(), metaObject);
        setFieldValByName("tmUpdatetime", new Date(), metaObject);
        setFieldValByName("tmEffectflag", "E", metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //更新时 需要填充字段
        setFieldValByName("tmUpdatetime", new Date(), metaObject);
    }
}
