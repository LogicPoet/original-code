package cn.catguild.design.demo00.service;

import cn.catguild.design.demo00.domain.DeliverReq;

/**
 * 实体商品服务
 *
 * @author liu.zhi
 * @date 2020/10/19 14:09
 */
public interface GoodsService {

    Boolean deliverGoods(DeliverReq req);

}
