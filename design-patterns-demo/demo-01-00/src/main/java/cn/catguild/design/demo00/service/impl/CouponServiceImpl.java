package cn.catguild.design.demo00.service.impl;

import cn.catguild.design.demo00.domain.CouponResult;
import cn.catguild.design.demo00.service.CouponService;

/**
 * 优惠券服务
 *
 * @author liu.zhi
 * @date 2020/10/19 14:44
 */
public class CouponServiceImpl implements CouponService {

    /**
     *
     * @param uId
     * @param couponNumber
     * @param uuid
     * @return
     */
    @Override
    public CouponResult sendCoupon(String uId, String couponNumber, String uuid) {
        System.out.println("模拟发放优惠券一张：" + uId + "," + couponNumber + "," + uuid);
        return new CouponResult("0000", "发放成功");
    }

}
