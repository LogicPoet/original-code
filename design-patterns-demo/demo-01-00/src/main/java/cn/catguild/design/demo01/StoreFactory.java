package cn.catguild.design.demo01;

import cn.catguild.design.demo01.store.ICommodity;
import cn.catguild.design.demo01.store.impl.CardCommodityServiceImpl;
import cn.catguild.design.demo01.store.impl.CouponCommodityServiceImpl;
import cn.catguild.design.demo01.store.impl.GoodsCommodityServiceImpl;

/**
 * 发放奖品简单工厂
 * 后续添加的奖品种类在此处添加即可
 *
 * @author liu.zhi
 * @date 2020/10/19 15:43
 */
public class StoreFactory {

    public ICommodity getCommodityService(Integer commodityType) {
        if (null == commodityType) return null;
        if (1 == commodityType) return new CouponCommodityServiceImpl();
        if (2 == commodityType) return new GoodsCommodityServiceImpl();
        if (3 == commodityType) return new CardCommodityServiceImpl();
        throw new RuntimeException("不存在的商品服务类型");
    }

}
