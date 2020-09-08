package com.cat.es.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * 注解@Document是ES的注解，将此实体类标注成一个文档
 * indexName： 索引名称，相当于数据库;
 * type： 类型, 相当于表
 * shards： 分片数量;
 * replicas: 副本数量
 *
 * @author LZ
 * @date 2020/5/26 17:27
 **/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "maiun_test",type = "blade_user", shards = 1,replicas = 0)
public class UserES {

	/**
	 * 注解@Id 作用在成员变量，标记一个字段作为id主键
	 */
	@Id
	private Integer id;
	/**
	 * 租户ID
	 */
	@Field(type = FieldType.Keyword)
	private String tenantId;
	/**
	 * 注解@Field 作用在成员变量，标记为文档的字段，并指定字段映射属性：
	 * type：字段类型，是枚举
	 * FieldType，可以是text、long、short、date、integer、object等
	 * analyzer: 分词器名称，这里的ik_max_word即使用ik分词器
	 * Keyword: 短文本类型，并且默认不分词
	 *
	 * 账号
	 */
	@Field(type = FieldType.Keyword)
	private String account;
	/**
	 * 密码
	 */
	@Field(type = FieldType.Keyword)
	private String password;
	/**
	 * 昵称
	 */
	@Field(type = FieldType.Keyword)
	private String name;
	/**
	 * 真名
	 */
	@Field(type = FieldType.Keyword)
	private String realName;
	/**
	 * 头像
	 */
	@Field(type = FieldType.Keyword)
	private String avatar;
	/**
	 * 邮箱
	 */
	@Field(type = FieldType.Keyword)
	private String email;
	/**
	 * 手机
	 */
	@Field(type = FieldType.Keyword)
	private String phone;
	/**
	 * 生日
	 */
	@Field(type = FieldType.Keyword)
	private Date birthday;
	/**
	 * 性别
	 */
	@Field(type = FieldType.Integer)
	private Integer sex;
	/**
	 * 角色id
	 */
	@Field(type = FieldType.Keyword)
	private String roleId;
	/**
	 * 部门id
	 */
	@Field(type = FieldType.Keyword)
	private String deptId;

	/**
	 * 创建人
	 */
	@Field(type = FieldType.Integer)
	private Integer createUser;

	/**
	 * 创建时间
	 */
	@Field(type = FieldType.Date)
	private Date createTime;

	/**
	 * 更新人
	 */
	@Field(type = FieldType.Integer)
	private Integer updateUser;

	/**
	 * 更新时间
	 */
	@Field(type = FieldType.Date)
	private Date updateTime;

	/**
	 * 状态[1:正常]
	 */
	@Field(type = FieldType.Integer)
	private Integer status;

	/**
	 * 状态[0:未删除,1:删除]
	 */
	@Field(type = FieldType.Integer)
	private Integer isDeleted;
}
