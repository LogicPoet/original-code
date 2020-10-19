package cn.catguild.design.demo01.store.impl;

import cn.catguild.design.demo00.domain.DeliverReq;
import cn.catguild.design.demo00.service.GoodsService;
import cn.catguild.design.demo00.service.impl.GoodsServiceImpl;
import cn.catguild.design.demo01.store.ICommodity;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 实物商品服务
 *
 * @author liu.zhi
 * @date 2020/10/19 15:35
 */
public class GoodsCommodityServiceImpl implements ICommodity {

    private final Logger logger = LoggerFactory.getLogger(GoodsCommodityServiceImpl.class);

    private final GoodsService goodsService = new GoodsServiceImpl();

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
        DeliverReq deliverReq = new DeliverReq();
        deliverReq.setUserName(queryUserName(uId));
        deliverReq.setUserPhone(queryUserPhoneNumber(uId));
        deliverReq.setSku(commodityId);
        deliverReq.setOrderId(bizId);
        deliverReq.setConsigneeUserName(extMap.get("consigneeUserName"));
        deliverReq.setConsigneeUserPhone(extMap.get("consigneeUserPhone"));
        deliverReq.setConsigneeUserAddress(extMap.get("consigneeUserAddress"));

        Boolean isSuccess = goodsService.deliverGoods(deliverReq);

        logger.info("请求参数[优惠券] => uId：{} commodityId：{} bizId：{} extMap：{}"
                , uId, commodityId, bizId, JSON.toJSON(extMap));
        logger.info("测试结果[优惠券]：{}", isSuccess);

        if (!isSuccess) throw new RuntimeException("实物商品发放失败");
    }

    private String queryUserName(String uId) {
        return "花花";
    }

    private String queryUserPhoneNumber(String uId) {
        return "15200101232";
    }

}
