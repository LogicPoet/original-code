///**
// * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
// * <p>
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// * <p>
// * http://www.apache.org/licenses/LICENSE-2.0
// * <p>
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package org.springblade.system.user.service.impl;
//
//
//import com.alibaba.druid.pool.DruidPooledPreparedStatement;
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.core.toolkit.Wrappers;
//import com.baomidou.mybatisplus.extension.exceptions.ApiException;
//import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
//import com.maiun.manage.entity.TenantManage;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springblade.common.config.ImageServerConfig;
//import org.springblade.common.constant.CommonConstant;
//import org.springblade.common.constant.GlobalConstant;
//import org.springblade.common.tool.BeanCopyUtils;
//import org.springblade.common.tool.CommonUtil;
//import org.springblade.core.log.exception.ServiceException;
//import org.springblade.core.mp.base.BaseServiceImpl;
//import org.springblade.core.secure.BladeUser;
//import org.springblade.core.tool.utils.*;
//import org.springblade.system.feign.IRoleClient;
//import org.springblade.system.user.config.PgsqlDataSourceConfig;
//import org.springblade.system.user.constant.UserESConstant;
//import org.springblade.system.user.entity.User;
//import org.springblade.system.user.entity.UserES;
//import org.springblade.system.user.entity.UserInfo;
//import org.springblade.system.user.mapper.UserMapper;
//import org.springblade.system.user.repository.UserESTool;
//import org.springblade.system.user.service.IUserService;
//import org.springblade.system.user.vo.RoleIdAndName;
//import org.springblade.system.user.vo.UserVO;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.CollectionUtils;
//
//import javax.sql.DataSource;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * 服务实现类
// *
// * @author Chill
// */
//@Service
//@AllArgsConstructor
//@Slf4j
//public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {
//	private PgsqlDataSourceConfig pgsqlDataSourceConfig;
//	private ImageServerConfig imageServerConfig;
//	//private IRoleClient roleClient;
//
//	@Override
//	@Transactional(rollbackFor = Exception.class)
//	public boolean submit(User user) {
//		// 修改图像存储路径
//		if (StringUtil.isNotBlank(user.getAvatar())) {
//			user.setAvatar(imageServerConfig.getFileName(user.getAvatar()));
//		}
//		// 确定是新增还是更新
//		boolean isAdd = (null == user.getId());
//
//		//修改时，只更新当前projectId下的角色，其他模块旧的角色保留下来
//		if (!isAdd && StringUtils.isNotBlank(user.getRoleId())) {
//			//新的role
//			String[] newRoleIds = user.getRoleId().split(",");
//			//有哪几个projectId
//			List<Integer> newRoleHaveProjectIds = baseMapper.haveProjectIds(newRoleIds);
//
//			if (!CollectionUtils.isEmpty(newRoleHaveProjectIds)) {
//				//当需要的修改的roleId只有一个模块时，只修改对应模块的role
//				if (newRoleHaveProjectIds.size() == 1) {
//					//原来的权限
//					User user1 = baseMapper.selectById(user.getId());
//					String[] oldRoleIds = user1.getRoleId().split(",");
//					//有哪几个projectId
//					List<Integer> oldRoleHaveProjectIds = baseMapper.haveProjectIds(oldRoleIds);
//					if (!CollectionUtils.isEmpty(oldRoleHaveProjectIds)) {
//						//相同的一个模块下的权限直接替换，非一个模块下的，进行拼接，不覆盖
//						if (oldRoleHaveProjectIds.size() == 1 && !oldRoleHaveProjectIds.get(0).equals(newRoleHaveProjectIds.get(0))) {
//							user.setRoleId(user1.getRoleId() + "," + user.getRoleId());
//						}
//						if (oldRoleHaveProjectIds.size() > 1) {
//							//projectId下的所有角色
//							List<Integer> newRoleOfAll = baseMapper.roleListByProjectId(newRoleHaveProjectIds.get(0));
//							//List<Integer>转List<String>
//							List<String> newRoleOfAllString = newRoleOfAll.stream().map(String::valueOf).collect(Collectors.toList());
//							//遍历旧的角色
//							for (String oldRoleId : oldRoleIds) {
//								//将非这个projectId下的角色转移添加到新的角色后面，保证其他模块的权限不被动
//								if (!newRoleOfAllString.contains(oldRoleId)) {
//									user.setRoleId(user.getRoleId() + "," + oldRoleId);
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//
//		if (Func.isNotEmpty(user.getPassword())) {
//			user.setPassword(DigestUtil.encrypt(user.getPassword()));
//		}
//		// 检测账号唯一性
//		boolean b = checkUserExist(user.getTenantId(), user.getId(), user.getAccount());
//		if (b) {
//			throw new ApiException("当前用户已存在!");
//		}
//
//		boolean result = saveOrUpdate(user);
//
//		if (result) {
//			// 当修改或新增成功是，更新elasticsearch中的内容
//			UserES userES = new UserES();
//			BeanCopyUtils.copy(user, userES, true);
//			boolean bES = isAdd ? UserESTool.add(userES) : UserESTool.update(userES);
//			if (!bES) {
//				throw new ApiException("ES更新用户失败!");
//			}
//		}
//		return result;
//	}
//
//	@Override
//	public IPage<User> selectUserPage(IPage<User> page, User user) {
//		return page.setRecords(baseMapper.selectUserPage(page, user));
//	}
//
//	@Override
//	public UserInfo userInfo(Long userId) {
//		UserInfo userInfo = new UserInfo();
//		User user = baseMapper.selectById(userId);
//		userInfo.setUser(user);
//		if (Func.isNotEmpty(user)) {
//			List<String> roleAlias = baseMapper.getRoleAlias(Func.toIntArray(user.getRoleId()));
//			userInfo.setRoles(roleAlias);
//		}
//		return userInfo;
//	}
//
//	/**
//	 * 用户信息
//	 *
//	 * @param account
//	 * @return
//	 */
//	@Override
//	public UserInfo userInfo(String account) {
//		UserInfo userInfo = new UserInfo();
//		List<User> users = baseMapper.selectList(Wrappers.<User>lambdaQuery().eq(User::getAccount, account));
//		if (CollectionUtil.isNotEmpty(users)) {
//			User user = users.get(0);
//			userInfo.setUser(user);
//			if (Func.isNotEmpty(user) && Func.isNotEmpty(user.getRoleId())) {
//				List<String> roleAlias = baseMapper.getRoleAlias(Func.toIntArray(user.getRoleId()));
//				userInfo.setRoles(roleAlias);
//			}
//		}
//		return userInfo;
//	}
//
//	@Override
//	public UserInfo userInfo(String account, String password) {
//		UserInfo userInfo = new UserInfo();
//		User user = baseMapper.getUser(account, password);
//		userInfo.setUser(user);
//		if (Func.isNotEmpty(user) && Func.isNotEmpty(user.getRoleId())) {
//			List<String> roleAlias = baseMapper.getRoleAlias(Func.toIntArray(user.getRoleId()));
//			userInfo.setRoles(roleAlias);
//			List<String> rolesName = baseMapper.getRoleName(Func.toIntArray(user.getRoleId()));
//			userInfo.setRolesName(rolesName);
//		}
//		return userInfo;
//	}
//
//	@Override
//	public boolean grant(String userIds, String roleIds) {
//		User user = new User();
//		user.setRoleId(roleIds);
//		List<Integer> toIntList = Func.toIntList(userIds);
//		boolean update = this.update(user, Wrappers.<User>update().lambda().in(User::getId, toIntList));
//
//		// 更新成功后批量更新es内的文档
//		if (update) {
//			List<UserES> userESList = new ArrayList<>();
//			toIntList.forEach(e -> {
//				// 查出最新文档
//				UserES userES = new UserES();
//				userES.setId(e);
//				userES.setRoleId(Func.toIntList(roleIds));
//				userESList.add(userES);
//			});
//			// 批量更新文档
//			UserESTool.updateAll(userESList);
//		}
//		return update;
//	}
//
//	@Override
//	public boolean resetPassword(String userIds) {
//		User user = new User();
//		user.setPassword(DigestUtil.encrypt(CommonConstant.DEFAULT_PASSWORD));
//		user.setUpdateTime(DateUtil.now());
//		List<Integer> integers = Func.toIntList(userIds);
//		boolean update = this.update(user, Wrappers.<User>update().lambda().in(User::getId, integers));
//
//		// 更新成功后批量更新es内的文档
//		if (update) {
//			List<UserES> userESList = new ArrayList<>();
//			integers.forEach(e -> {
//				UserES userES = new UserES();
//				BeanCopyUtils.copy(user, userES, true);
//				userES.setId(e);
//				userESList.add(userES);
//			});
//			UserESTool.updateAll(userESList);
//		}
//
//		return update;
//	}
//
//	@Override
//	public boolean updatePassword(Integer userId, String oldPassword, String newPassword, String newPassword1) {
//		User user = getById(userId);
//		if (!newPassword.equals(newPassword1)) {
//			throw new ServiceException("请输入正确的确认密码!");
//		}
//		if (!user.getPassword().equals(DigestUtil.encrypt(oldPassword))) {
//			throw new ServiceException("原密码不正确!");
//		}
//		boolean update = this.update(Wrappers.<User>update().lambda().set(User::getPassword, DigestUtil.encrypt(newPassword)).eq(User::getId, userId));
//
//		// 更新成功后更新es内的文档
//		if (update) {
//			UserES userES = new UserES();
//			userES.setId(userId);
//			userES.setPassword(DigestUtil.encrypt(newPassword));
//			UserESTool.update(userES);
//		}
//
//		return update;
//	}
//
//	@Override
//	public List<String> getRoleName(String roleIds) {
//		return baseMapper.getRoleName(Func.toIntArray(roleIds));
//	}
//
//	@Override
//	public List<String> getDeptName(String deptIds) {
//		return baseMapper.getDeptName(Func.toIntArray(deptIds));
//	}
//
//	@Override
//	public List<User> listAll(String tenantId, Integer roleId) {
//		List<User> list = baseMapper.listAll(tenantId, roleId);
////		List<User> list = baseMapper.listAll(tenantId);
////		for (User user : list) {
////			if (user.getIsDeleted() == 1) {
////				user.setName(user.getName() + "（已删除）");
////			}
////		}
//		return list;
//	}
//
//	/**
//	 * 检测用户名是否重复
//	 *
//	 * @param tenantId 租户ID
//	 * @param userId   用户id
//	 * @param account  账号
//	 * @return 重复返回true，否则返回false
//	 */
//	@Override
//	public boolean checkUserExist(String tenantId, Integer userId, String account) {
//		LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>query().lambda();
//		if (StringUtil.isNotBlank(tenantId)) {
//			queryWrapper.eq(User::getTenantId, tenantId);
//		}
//		queryWrapper.eq(User::getAccount, account);
//		List<User> list = baseMapper.selectList(queryWrapper);
//		if (list.isEmpty()) {
//			// 查询无果，则没有冲突
//			return false;
//		} else if (list.size() == 1) {
//			Integer id = list.get(0).getId();
//			// 只有一条结果时，是自己则无冲突，不是自己则冲突
//			return !id.equals(userId);
//		} else {
//			// 有多条结果，则冲突
//			return true;
//		}
//	}
//
//	/**
//	 * 检测用户名是否重复,全局账号检测
//	 *
//	 * @param userId  用户id[如果是修改，务必传该参数]
//	 * @param account 账号
//	 * @return 重复返回true，否则返回false
//	 */
//	@Override
//	public boolean checkUserExist(Integer userId, String account) {
//		return checkUserExist(null, userId, account);
//	}
//
//	/**
//	 * 批量删除用户
//	 *
//	 * @param userIds 用户id
//	 * @return
//	 */
//	@Override
//	public boolean removeUserByIds(String userIds) {
//		List<Integer> ids = Func.toIntList(userIds);
//		// 删除本地
//		int delete = baseMapper.deleteBatchIds(ids);
//		// 删除es
//		UserESTool.removeByIds(ids);
//		return SqlHelper.retBool(delete);
//	}
//
//	/**
//	 * 分页查询
//	 *
//	 * @param user
//	 * @param page
//	 * @param tenantId
//	 * @return
//	 */
//	@Override
//	public IPage<UserVO> getUserPage(User user, IPage<UserVO> page, String tenantId) {
//		List<UserVO> userVOList = jdbcSelectPageList(user, tenantId, Func.toStrList(user.getRoleId()), page);
//		//List<UserVO> userVOList = userDefinedMapper.selectPageList(user, tenantId,
//		//	Func.toStrList(user.getRoleId()), page);
//
//		if (CollectionUtil.isNotEmpty(userVOList)) {
//			// 填充其他信息
//			userVOList.forEach(u -> {
//				String roleId = u.getRoleId();
//				List<Integer> roleIds = Func.toIntList(roleId);
//				List<RoleIdAndName> idAndNames = baseMapper.selectRoleIdAndNameList(roleIds);
//				u.setRoleList(idAndNames);
//				List<String> roleName = getRoleName(u.getRoleId());
//				u.setRoleName(Func.join(roleName));
//			});
//		}
//
//		return page.setRecords(userVOList);
//	}
//
//	/**
//	 * 获取角色id与名字的对应关系
//	 *
//	 * @param roleIds
//	 * @return
//	 */
//	@Override
//	public List<RoleIdAndName> getRoleIdAndNameList(List<Integer> roleIds) {
//		return baseMapper.selectRoleIdAndNameList(roleIds);
//	}
//
//	@Override
//	public Integer freeze(String account, Integer projectId) {
//		User user = baseMapper.selectOne(Wrappers.<User>query().lambda().eq(User::getAccount, account));
//		if (user == null) {
//			//没有此账户
//			return 2;
//		}
//		List<TenantManage> tenantManages = baseMapper.checkFreeze(user.getTenantId(), projectId);
//		if (!CollectionUtils.isEmpty(tenantManages)) {
//			Date startTime = CommonUtil.LocalDateToDate(tenantManages.get(0).getStartTime());
//			Date endTime = CommonUtil.LocalDateToDate(tenantManages.get(0).getEndTime());
//			Date now = new Date();
//			if (now.before(startTime) || !now.before(endTime)) {
//				return 1;
//			} else {
//				return 0;
//			}
//		}
//		//对应租户没有添加租户冻结管理信息
//		return -1;
//	}
//
//	@Override
//	public List<String> getRoleNameByProjectId(String roleId, Integer projectId) {
//		//项目下所有roleId
//		List<Integer> rolesId = baseMapper.roleListByProjectId(projectId);
//		if (CollectionUtils.isEmpty(rolesId)) {
//			return null;
//		}
//		//该用户下所有roleId
//		Integer[] roleIn = Func.toIntArray(roleId);
//		if (roleIn == null || roleIn.length <= 0) {
//			return null;
//		}
//		//用户项目下roleId过滤
//		List<Integer> projectRoleIds = new ArrayList<>();
//		for (Integer i : roleIn) {
//			if (rolesId.contains(i)) {
//				projectRoleIds.add(i);
//			}
//		}
//		if (CollectionUtils.isEmpty(projectRoleIds)) {
//			return null;
//		}
//		//查询roleName
//		return baseMapper.getRoleNameList(projectRoleIds);
//	}
//
//	@Override
//	public User getByAccount(String account) {
//		List<User> users = baseMapper.selectList(Wrappers.<User>lambdaQuery().eq(User::getAccount, account));
//		if (CollectionUtil.isNotEmpty(users)) {
//			return users.get(0);
//		} else {
//			return null;
//		}
//	}
//
//	/**
//	 * roleId覆盖式修改user
//	 *
//	 * @param user
//	 * @return
//	 */
//	@Override
//	public User coverUser(User user) {
//		// 修改图像存储路径
//		if (StringUtil.isNotBlank(user.getAvatar())) {
//			user.setAvatar(imageServerConfig.getFileName(user.getAvatar()));
//		}
//		// 确定是新增还是更新
//		boolean addOrUpdate = null == user.getId();
//
//		if (Func.isNotEmpty(user.getPassword())) {
//			user.setPassword(DigestUtil.encrypt(user.getPassword()));
//		}
//		// 检测账号唯一性
//		boolean b = checkUserExist(user.getTenantId(), user.getId(), user.getAccount());
//		if (b) {
//			throw new ApiException("当前用户已存在!");
//		}
//
//		boolean result = saveOrUpdate(user);
//
//		if (result) {
//			// 当修改或新增成功是，更新elasticsearch中的内容
//			UserES userES = new UserES();
//			BeanCopyUtils.copy(user, userES, true);
//			userES.setRoleId(Func.toIntList(user.getRoleId()));
//			userES.setDeptId(Func.toIntList(user.getDeptId()));
//			boolean bES = addOrUpdate ? UserESTool.add(userES) : UserESTool.update(userES);
//			if (!bES) {
//				throw new ApiException("ES更新用户失败!");
//			}
//		}
//		return user;
//	}
//
//
//	/**
//	 * jdbc直连查询用户分页数据
//	 *
//	 * @param user
//	 * @param tenantId
//	 * @param roles
//	 * @param page
//	 * @return
//	 */
//	private List<UserVO> jdbcSelectPageList(User user, String tenantId, List<String> roles, IPage<UserVO> page) {
//		DataSource dataSource = pgsqlDataSourceConfig.pgDataSource();
//		Connection connection = null;
//		try {
//			connection = dataSource.getConnection();
//
//			// 获取查询分页查询
//			String sqlCount = getSql(false, user, tenantId, roles, page);
//			PreparedStatement countStatement = connection.prepareStatement(sqlCount);
//			settingParameters(false, user, tenantId, roles, page, countStatement);
//			log.debug("==>  Preparing: {}", ((DruidPooledPreparedStatement) countStatement).getRawPreparedStatement().toString());
//			ResultSet resultSetCount = countStatement.executeQuery();
//			while (resultSetCount.next()) {
//				page.setTotal(resultSetCount.getLong(1));
//			}
//
//			// 获取查询
//			String sqlQuery = getSql(true, user, tenantId, roles, page);
//			PreparedStatement queryStatement = connection.prepareStatement(sqlQuery);
//			settingParameters(true, user, tenantId, roles, page, queryStatement);
//			log.debug("==>  Preparing: {}", ((DruidPooledPreparedStatement) queryStatement).getRawPreparedStatement().toString());
//			ResultSet resultSet = queryStatement.executeQuery();
//
//			List<UserVO> userVOS = new ArrayList<>();
//			while (resultSet.next()) {
//				UserVO userVO = new UserVO();
//				// 填充User对象本身的值
//				//	private Integer id;
//				userVO.setId(resultSet.getInt("id"));
//				// private String tenantId;
//				userVO.setTenantId(resultSet.getString("tenant_id"));
//				//	private String account;
//				userVO.setAccount(resultSet.getString("account"));
//				//	private String password;
//
//				//	private String name;
//				userVO.setName(resultSet.getString("name"));
//				//	private String realName;
//				userVO.setRealName(resultSet.getString("real_name"));
//				//	private String avatar;
//				userVO.setAvatar(imageServerConfig.convertIP(resultSet.getString("avatar")));
//				//	private String email;
//				userVO.setEmail(resultSet.getString("email"));
//				//	private String phone;
//				userVO.setPhone(resultSet.getString("phone"));
//				//	private Date birthday;
//				userVO.setBirthday(resultSet.getDate("birthday"));
//				//	private Integer sex;
//				userVO.setSex(resultSet.getInt("sex"));
//				//	private String roleId;
//				userVO.setRoleId(resultSet.getString("role_id"));
//				//	private String deptId;
//				userVO.setDeptId(resultSet.getString("dept_id"));
//				//	private Integer createUser;
//				userVO.setCreateUser(resultSet.getInt("create_user"));
//				//	private Date createTime;
//				userVO.setCreateTime(resultSet.getDate("create_time"));
//				//	private Integer updateUser;
//				userVO.setUpdateUser(resultSet.getInt("update_user"));
//				//	private Date updateTime;
//				userVO.setUpdateTime(resultSet.getDate("update_time"));
//				//	private Integer status;
//				userVO.setStatus(resultSet.getInt("status"));
//				//	private Integer isDeleted;
//				userVO.setIsDeleted(resultSet.getInt("is_deleted"));
//				// 填充UserVO对象本身的值
//				userVO.setSexName(resultSet.getString("dict_value"));
//
//				userVOS.add(userVO);
//			}
//			log.debug("<==      Total: {}", userVOS.size());
//			return userVOS;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (null != connection) {
//				try {
//					connection.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return null;
//	}
//
//	/**
//	 * 设置占位符参数
//	 *
//	 * @param b                 true是分页查询,false是查询条数
//	 * @param user
//	 * @param tenantId
//	 * @param roles
//	 * @param page
//	 * @param preparedStatement
//	 */
//	private void settingParameters(boolean b, User user, String tenantId, List<String> roles, IPage<UserVO> page, PreparedStatement preparedStatement) throws SQLException {
//		// ?占位符位置
//		int i = 0;
//		if (StringUtil.isNotBlank(tenantId)) {
//			i++;
//			preparedStatement.setString(i, tenantId);
//		}
//		if (StringUtil.isNotBlank(user.getName())) {
//			i++;
//			preparedStatement.setString(i, user.getName());
//		}
//		if (StringUtil.isNotBlank(user.getRealName())) {
//			i++;
//			preparedStatement.setString(i, user.getRealName());
//		}
//		if (StringUtil.isNotBlank(user.getAccount())) {
//			i++;
//			preparedStatement.setString(i, user.getAccount());
//		}
//		if (null != roles) {
//			for (String role : roles) {
//				i++;
//				preparedStatement.setString(i, role);
//			}
//		}
//		if (b) {
//			i++;
//			preparedStatement.setInt(i, (int) page.getSize());
//			i++;
//			preparedStatement.setLong(i, page.getCurrent() > 0 ? (page.getCurrent() - 1) * page.getSize() : 0);
//		}
//	}
//
//	/**
//	 * 创建查询语句
//	 *
//	 * @param b true是分页查询,false是查询条数
//	 * @return
//	 */
//	private String getSql(boolean b, User user, String tenantId, List<String> roles, IPage<UserVO> page) {
//		StringBuilder sb = new StringBuilder();
//		if (b) {
//			sb.append("select t1.*,t4.dict_value from blade_user t1");
//		} else {
//			sb.append("select count(1) from blade_user t1");
//		}
//		sb.append(" ");
//		sb.append("left join blade_dict t4 on t4.code ='sex' and t1.sex = t4.dict_key");
//		sb.append(" ");
//		sb.append("where t1.is_deleted = 0");
//		if (StringUtil.isNotBlank(tenantId)) {
//			sb.append(" ");
//			sb.append("and t1.tenant_id = ?");
//		}
//		if (StringUtil.isNotBlank(user.getName())) {
//			sb.append(" ");
//			sb.append("and  t1.name like CONCAT('%', ?, '%')");
//		}
//		if (StringUtil.isNotBlank(user.getRealName())) {
//			sb.append(" ");
//			sb.append("and  t1.real_name like CONCAT('%', ?, '%')");
//		}
//		if (StringUtil.isNotBlank(user.getAccount())) {
//			sb.append(" ");
//			sb.append("and  t1.account like CONCAT('%', ?, '%')");
//		}
//
//		if (null != roles) {
//			sb.append(" ");
//			sb.append("and string_to_array(role_id,',') && array[");
//			for (int i = 0; i < roles.size(); i++) {
//				sb.append(" cast( ? as text ) ,");
//				//sb.append(" ? ,");
//			}
//			sb.deleteCharAt(sb.length() - 1);
//			sb.append("]");
//		}
//		if (b) {
//			sb.append(" ");
//			sb.append("order by t1.id");
//			sb.append(" ");
//			sb.append("limit ? offset ? ");
//		}
//
//		return sb.toString();
//	}
//}
/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.catguid.dynamic.service.db1.impl;

import cn.catguid.dynamic.service.db1.IUserService1;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author Chill
 */
@Service
public class UserService1Impl implements IUserService1 {

}
