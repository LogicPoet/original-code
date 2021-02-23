package cn.catguid.dynamic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author liu.zhi
 * @date 2021/2/22 18:20
 */
@Data
@TableName("blade_user")
public class User1 {
    private static final long serialVersionUID = 1L;

    private String tenantId;

    /**
     * 创建人
     */
    private Integer createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private Integer updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 状态[1:正常]
     */
    private Integer status;

    /**
     * 状态[0:未删除,1:删除]
     */
    @TableLogic
    private Integer isDeleted;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
//	@JsonIgnore
    private String password;
    /**
     * 昵称
     */
    private String name;
    /**
     * 真名
     */
    private String realName;
    /**
     * 头像
     */
    private String avatar;

    //public String getAvatar() {
    //	ImageServerConfig imageServer = SpringUtil.getBean(ImageServerConfig.class);
    //	return imageServer.convertIP(avatar);
    //}
    //
    //public void setAvatar(String image) {
    //	ImageServerConfig imageServer = SpringUtil.getBean(ImageServerConfig.class);
    //	this.avatar  = imageServer.convertIP(image);
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
//	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    private Date birthday;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 部门id
     */
    private Integer deptId;
}
