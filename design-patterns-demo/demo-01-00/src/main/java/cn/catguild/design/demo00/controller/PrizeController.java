package cn.catguild.design.demo00.controller;

import cn.catguild.design.demo00.domain.AwardReq;
import cn.catguild.design.demo00.domain.AwardRes;
import cn.catguild.design.demo00.domain.CouponResult;
import cn.catguild.design.demo00.domain.DeliverReq;
import cn.catguild.design.demo00.service.CouponService;
import cn.catguild.design.demo00.service.GoodsService;
import cn.catguild.design.demo00.service.IQiYiCardService;
import cn.catguild.design.demo00.service.impl.CouponServiceImpl;
import cn.catguild.design.demo00.service.impl.GoodsServiceImpl;
import cn.catguild.design.demo00.service.impl.IQiYiCardServiceImpl;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 发放奖品的接口
 *
 * @author liu.zhi
 * @date 2020/10/19 11:53
 */
public class PrizeController {

    private final Logger logger = LoggerFactory.getLogger(PrizeController.class);

    /**
     * 发放奖品接口
     *
     * @param req 入参
     * @return 出参
     */
    public AwardRes awardToUser(AwardReq req){
        String reqJson = JSON.toJSONString(req);
        AwardRes awardRes = null;
        try {
            logger.info("奖品发放开始{}。req:{}", req.getuId(), reqJson);
            // 按照不同类型方法商品[1优惠券、2实物商品、3第三方兑换卡(爱奇艺)]
            if (req.getAwardType() == 1) {
                CouponService couponService = new CouponServiceImpl();
                CouponResult couponResult = couponService.sendCoupon(req.getuId(), req.getAwardNumber(), req.getBizId());
                if ("0000".equals(couponResult.getCode())) {
                    awardRes = new AwardRes("0000", "发放成功");
                } else {
                    awardRes = new AwardRes("0001", couponResult.getInfo());
                }
            } else if (req.getAwardType() == 2) {
                GoodsService goodsService = new GoodsServiceImpl();
                DeliverReq deliverReq = new DeliverReq();
                deliverReq.setUserName(queryUserName(req.getuId()));
                deliverReq.setUserPhone(queryUserPhoneNumber(req.getuId()));
                deliverReq.setSku(req.getAwardNumber());
                deliverReq.setOrderId(req.getBizId());
                deliverReq.setConsigneeUserName(req.getExtMap().get("consigneeUserName"));
                deliverReq.setConsigneeUserPhone(req.getExtMap().get("consigneeUserPhone"));
                deliverReq.setConsigneeUserAddress(req.getExtMap().get("consigneeUserAddress"));
                Boolean isSuccess = goodsService.deliverGoods(deliverReq);
                if (isSuccess) {
                    awardRes = new AwardRes("0000", "发放成功");
                } else {
                    awardRes = new AwardRes("0001", "发放失败");
                }
            } else if (req.getAwardType() == 3) {
                String bindMobileNumber = queryUserPhoneNumber(req.getuId());
                IQiYiCardService vipCardService = new IQiYiCardServiceImpl();
                vipCardService.grantToken(bindMobileNumber, req.getAwardNumber());
                awardRes = new AwardRes("0000", "发放成功");
            }
            logger.info("奖品发放完成{}。", req.getuId());
        } catch (Exception e) {
            logger.error("奖品发放失败{}。req:{}", req.getuId(), reqJson, e);
            awardRes = new AwardRes("0001", e.getMessage());
        }

        return awardRes;

    }


    private String queryUserName(String uId) {
        return "花花";
    }

    private String queryUserPhoneNumber(String uId) {
        return "15200101232";
    }

}
