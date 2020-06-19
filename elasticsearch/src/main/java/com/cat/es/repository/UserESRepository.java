package com.cat.es.repository;

import com.cat.es.model.UserES;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @author LZ
 * @date 2020/5/26 17:46
 **/
@Component
public interface UserESRepository extends ElasticsearchRepository<UserES,Integer> {

	/**
	 * 根据名称或租户id查询用户
	 * @param tenantId 租户id
	 * @param name 名称
	 * @return
	 */
	List<UserES> findByTenantIdAndNameLike(String tenantId, String name);
	List<UserES> findByTenantIdAndNameEquals(String tenantId, String name);

	/**
	 * 查询某租户下所有的用户
	 *
	 * @param tenantId 租户id
	 * @return
	 */
	List<UserES> findAllByTenantId(String tenantId);

	List<UserES> findByNameAndTenantId(String name,String tenantId);

	List<UserES> findByNameEquals(String name);

	@Query("{\"term\": {\"name\": {\"value\": \"?0\"}}}")
	List<UserES> selectByName(String name);

	@Query("{ \"size\":10000,\"term\":{\"name\":{\"value\": \"?0\"}}}")
	List<UserES> selectAllByName(String name);

	List<UserES> getByName(String tenantId);

	List<UserES> readByName(String name);

	List<UserES> queryByName(String name);


}
