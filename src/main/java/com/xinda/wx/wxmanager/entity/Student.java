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
 * 学员表
 * </p>
 *
 * @author Lios123
 * @since 2019-07-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("wx_student")
public class Student extends Model<Student> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "wx_sid", type = IdType.UUID)
    private String wxSid;

    private String wxSname;

    private String wxSex;

    @TableField(fill = FieldFill.INSERT)
    private Date tmCreatetime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date tmUpdatetime;
    @TableField(fill = FieldFill.INSERT)
    private String tmEffectflag;


    public static final String WX_SID = "wx_sid";

    public static final String WX_SNAME = "wx_sname";

    public static final String WX_SEX = "wx_sex";

    public static final String TM_CREATETIME = "tm_createtime";

    public static final String TM_UPDATETIME = "tm_updatetime";

    public static final String TM_EFFECTFLAG = "tm_effectflag";

    @Override
    protected Serializable pkVal() {
        return this.wxSid;
    }

}
