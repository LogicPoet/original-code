package cn.catguild.cfd.node;

import java.util.List;

/**
 * @author liu.zhi
 * @date 2020/12/29 11:13
 */
public interface INode {

    /**
     * 主键
     *
     * @return Integer
     */
    Integer getId();

    /**
     * 父主键
     *
     * @return Integer
     */
    Integer getParentId();

    /**
     * 子孙节点
     *
     * @return List
     */
    List<INode> getChildren();

}
