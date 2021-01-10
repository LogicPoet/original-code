package cn.catguild.cfd.entity;

import cn.catguild.cfd.node.INode;

import java.util.List;

/**
 * @author liu.zhi
 * @date 2020/12/29 11:16
 */
public class DirContent implements INode {





    /**
     * 主键
     *
     * @return Integer
     */
    @Override
    public Integer getId() {
        return null;
    }

    /**
     * 父主键
     *
     * @return Integer
     */
    @Override
    public Integer getParentId() {
        return null;
    }

    /**
     * 子孙节点
     *
     * @return List
     */
    @Override
    public List<INode> getChildren() {
        return null;
    }
}
