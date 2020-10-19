package cn.catguild.design.demo01.store.impl;

import cn.catguild.design.demo00.domain.CouponResult;
import cn.catguild.design.demo00.service.CouponService;
import cn.catguild.design.demo00.service.impl.CouponServiceImpl;
import cn.catguild.design.demo01.store.ICommodity;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 优惠券服务
 *
 * @author liu.zhi
 * @date 2020/10/19 15:34
 */
public class CouponCommodityServiceImpl implements ICommodity {

    private final Logger logger = LoggerFactory.getLogger(CouponCommodityServiceImpl.class);

    private final CouponService couponService = new CouponServiceImpl();

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
        CouponResult couponResult = couponService.sendCoupon(uId, commodityId, bizId);
        logger.info("请求参数[优惠券] => uId：{} commodityId：{} bizId：{} extMap：{}"
                , uId, commodityId, bizId, JSON.toJSON(extMap));
        logger.info("测试结果[优惠券]：{}", JSON.toJSON(couponResult));
        if (!"0000".equals(couponResult.getCode())) throw new RuntimeException(couponResult.getInfo());
    }

}
