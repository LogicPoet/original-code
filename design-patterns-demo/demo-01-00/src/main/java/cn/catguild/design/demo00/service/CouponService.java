package cn.catguild.design.demo00.service;

import cn.catguild.design.demo00.domain.CouponResult;

/**
 * 优惠券服务
 *
 * @author liu.zhi
 * @date 2020/10/19 12:02
 */
public interface CouponService {

    CouponResult sendCoupon(String uId, String couponNumber, String uuid);

}
