package cn.catguild.design.demo00;

import cn.catguild.design.basic.AuthInfo;
import cn.catguild.design.basic.AuthService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 一梭子实现审批流程
 *
 * @author liu.zhi
 * @date 2020/10/20 9:28
 */
public class AuthController {

    private SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 时间格式化

    /**
     * 查询当前审批节点接口
     *
     * @param uId 用户id
     * @param orderId 单号id
     * @param authDate 审批时间
     * @return 审批结果
     * @throws ParseException
     */
    public AuthInfo doAuth(String uId, String orderId, Date authDate) throws ParseException {

        // 三级审批
        Date date = AuthService.queryAuthInfo("1000013", orderId);
        if (null == date) return new AuthInfo("0001", "单号：", orderId, " 状态：待三级审批负责人 ", "王工");

        // 二级审批
        if (authDate.after(f.parse("2020-06-01 00:00:00")) && authDate.before(f.parse("2020-10-22 23:59:59"))) {
            date = AuthService.queryAuthInfo("1000012", orderId);
            if (null == date) return new AuthInfo("0001", "单号：", orderId, " 状态：待二级审批负责人 ", "张经理");
        }

        // 一级审批
        if (authDate.after(f.parse("2020-06-11 00:00:00")) && authDate.before(f.parse("2020-10-22 23:59:59"))) {
            date = AuthService.queryAuthInfo("1000011", orderId);
            if (null == date) return new AuthInfo("0001", "单号：", orderId, " 状态：待一级审批负责人 ", "段总");
        }

        return new AuthInfo("0001", "单号：", orderId, " 状态：审批完成");
    }

}
