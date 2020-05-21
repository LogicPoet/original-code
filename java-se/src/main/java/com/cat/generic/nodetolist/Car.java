package com.cat.generic.nodetolist;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LZ
 * @date 2020/4/29 14:43
 **/
public class Car implements INode {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 父节点ID
     */
    private Integer parentId;

    /**
     * 子孙节点
     */
    private List<INode> children;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 主键
     *
     * @return Integer
     */
    @Override
    public Integer getId() {
        return this.id;
    }

    /**
     * 父主键
     *
     * @return Integer
     */
    @Override
    public Integer getParentId() {
        return this.parentId;
    }

    /**
     * 子孙节点
     *
     * @return List
     */
    @Override
    public List<INode> getChildren() {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        return this.children;
    }



}
