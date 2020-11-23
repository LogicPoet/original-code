package cn.catguild.design.demo01.impl;

import cn.catguild.design.basic.AuthInfo;
import cn.catguild.design.basic.AuthService;
import cn.catguild.design.demo01.AuthLink;

import java.text.ParseException;
import java.util.Date;

/**
 * 三级负责人
 *
 * @author liu.zhi
 * @date 2020/10/20 14:49
 */
public class Level3AuthLink extends AuthLink {

    private final Date beginDate = f.parse("2020-06-01 00:00:00");
    private final Date endDate = f.parse("2020-10-22 23:59:59");

    public Level3AuthLink(String levelUserId, String levelUserName) throws ParseException {
        super(levelUserId, levelUserName);
    }

    public AuthInfo doAuth(String uId, String orderId, Date authDate) {
        Date date = AuthService.queryAuthInfo(levelUserId, orderId);
        if (null == date) {
            return new AuthInfo("0001", "单号：", orderId, " 状态：待三级审批负责人 ", levelUserName);
        }
        AuthLink next = super.next();
        if (null == next) {
            return new AuthInfo("0000", "单号：", orderId, " 状态：三级审批负责人完成", " 时间：", f.format(date), " 审批人：", levelUserName);
        }

        if (authDate.before(beginDate) || authDate.after(endDate)) {
            return new AuthInfo("0000", "单号：", orderId, " 状态：三级审批负责人完成", " 时间：", f.format(date), " 审批人：", levelUserName);
        }

        return next.doAuth(uId, orderId, authDate);
    }

}
