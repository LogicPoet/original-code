package cn.catguild.design.basic;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 模拟审核服务
 *  1. auth          审核流程
 *  2. queryAuthInfo 查询审核信息(时间)
 *
 * @author liu.zhi
 * @date 2020/10/20 9:26
 */
public class AuthService {

    private static Map<String, Date> authMap = new ConcurrentHashMap<String, Date>();

    /**
     * 查询审核信息(时间)
     *
     * @param uId 用户id
     * @param orderId 单号id
     * @return
     */
    public static Date queryAuthInfo(String uId, String orderId) {
        return authMap.get(uId.concat(orderId));
    }

    /**
     * 操作审核流程状态
     *
     * @param uId 用户id
     * @param orderId 单号id
     */
    public static void auth(String uId, String orderId) {
        authMap.put(uId.concat(orderId), new Date());
    }

}
