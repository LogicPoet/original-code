package cn.catguild.design.demo00.service;

/**
 * 视频vip兑换卡 服务
 *
 * @author liu.zhi
 * @date 2020/10/19 14:10
 */
public interface IQiYiCardService {

    void grantToken(String bindMobileNumber, String cardId);

}
