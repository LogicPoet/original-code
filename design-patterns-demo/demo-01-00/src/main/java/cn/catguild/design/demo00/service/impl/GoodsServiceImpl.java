package cn.catguild.design.demo00.service.impl;

import cn.catguild.design.demo00.domain.DeliverReq;
import cn.catguild.design.demo00.service.GoodsService;
import com.alibaba.fastjson.JSON;

/**
 * 实物商品服务
 *
 *
 * @author liu.zhi
 * @date 2020/10/19 14:45
 */
public class GoodsServiceImpl implements GoodsService {

    /**
     *
     * @param req
     * @return
     */
    @Override
    public Boolean deliverGoods(DeliverReq req) {
        System.out.println("模拟发货实物商品一个：" + JSON.toJSONString(req));
        return true;
    }

}
