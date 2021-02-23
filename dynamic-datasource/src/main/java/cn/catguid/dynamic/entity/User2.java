package cn.catguid.dynamic.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author liu.zhi
 * @date 2021/2/22 18:21
 */
@Data
@TableName("blade_user")
public class User2 {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 真名
     */
    private String realName;
    /**
     * 头像
     */
    private String avatar;
    //
    //public String getAvatar() {
    //    ImageServerConfig imageServer = SpringUtil.getBean(ImageServerConfig.class);
    //    return imageServer.convertIP(avatar);
    //}
    //
    //public void setAvatar(String image) {
    //    ImageServerConfig imageServer = SpringUtil.getBean(ImageServerConfig.class);
    //    this.avatar  = imageServer.convertIP(image);
    //}

    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机
     */
    private String phone;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 性别
     */
    private Integer gender;


    private String jobNumber;

    private String idCard;

    private String position;

    private String jobTitle;

    private Integer statusType;

    private String labelType;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 状态[0:未删除,1:删除]
     */
    @TableLogic
    private Integer isDeleted;
}
