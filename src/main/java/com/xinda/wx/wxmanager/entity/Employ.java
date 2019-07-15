package com.xinda.wx.wxmanager.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 微信员工信息表
 * </p>
 *
 * @author Lios123
 * @since 2019-07-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("wx_employ")
public class Employ extends Model<Employ> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "wx_openid", type = IdType.INPUT)
    private String wxOpenid;

    private String wxName;
    private String wxSex;
    private String wxEmployid;

    private String wxEmployname;
    private String wxIdcard;
    @TableField(fill = FieldFill.INSERT)
    private Date tmCreatetime;
    @TableField(fill = FieldFill.INSERT)
    private Date tmUpdatetime;
    @TableField(fill = FieldFill.INSERT)
    private String tmEffectflag;


    public static final String WX_OPENID = "wx_openid";

    public static final String WX_NAME = "wx_name";

    public static final String WX_SEX = "wx_sex";

    public static final String WX_EMPLOYID = "wx_employid";

    public static final String WX_EMPLOYNAME = "wx_employname";

    public static final String WX_IDCARD = "wx_idcard";

    public static final String TM_CREATETIME = "tm_createtime";

    public static final String TM_UPDATETIME = "tm_updatetime";

    public static final String TM_EFFECTFLAG = "tm_effectflag";

    @Override
    protected Serializable pkVal() {
        return this.wxOpenid;
    }

}
