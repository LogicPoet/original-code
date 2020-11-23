package cn.catguild.design.demo00.service.impl;

import cn.catguild.design.demo00.service.IQiYiCardService;

/**
 * 视频vip会员卡 服务
 *
 * @author liu.zhi
 * @date 2020/10/19 14:45
 */
public class IQiYiCardServiceImpl implements IQiYiCardService {


    @Override
    public void grantToken(String bindMobileNumber, String cardId) {
        System.out.println("模拟发放视频vip会员卡一张：" + bindMobileNumber + "，" + cardId);
    }


}
