package cn.catguild.design.demo01.store;

import java.util.Map;

/**
 * 发放奖品服务
 *
 * @author liu.zhi
 * @date 2020/10/19 15:32
 */
public interface ICommodity {

    /**
     * 发放奖品统一接口
     * 保证最终入参和出参的一致性
     *
     * @param uId 用户id
     * @param commodityId 奖品id
     * @param bizId 业务id
     * @param extMap 拓展参数
     * @throws Exception
     */
    void sendCommodity(String uId, String commodityId, String bizId,
                       Map<String, String> extMap) throws Exception;

}
