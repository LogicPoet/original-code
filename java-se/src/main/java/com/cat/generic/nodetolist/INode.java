package com.cat.generic.nodetolist;

import java.util.List;

/**
 * @author LZ
 * @date 2020/4/28 9:36
 **/
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
