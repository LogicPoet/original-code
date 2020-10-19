package cn.catguild.design.demo01.store.impl;

import cn.catguild.design.demo00.service.IQiYiCardService;
import cn.catguild.design.demo00.service.impl.IQiYiCardServiceImpl;
import cn.catguild.design.demo01.store.ICommodity;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 会员卡服务
 *
 * @author liu.zhi
 * @date 2020/10/19 15:36
 */
public class CardCommodityServiceImpl implements ICommodity {

    private Logger logger = LoggerFactory.getLogger(CardCommodityServiceImpl.class);

    // 模拟注入
    private IQiYiCardService iQiYiCardService = new IQiYiCardServiceImpl();

    /**
     * 发放奖品统一接口
     * 保证最终入参和出参的一致性
     *
     * @param uId         用户id
     * @param commodityId 奖品id
     * @param bizId       业务id
     * @param extMap      拓展参数
     * @throws Exception
     */
    @Override
    public void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) throws Exception {
        String mobile = queryUserMobile(uId);
        iQiYiCardService.grantToken(mobile, bizId);
        logger.info("请求参数[爱奇艺兑换卡] => uId：{} commodityId：{} bizId：{} extMap：{}"
                , uId, commodityId, bizId, JSON.toJSON(extMap));
        logger.info("测试结果[爱奇艺兑换卡]：success");
    }

    private String queryUserMobile(String uId) {
        return "15200101232";
    }
}
