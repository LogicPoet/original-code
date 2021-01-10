package cn.catguild.cfd.node;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liu.zhi
 * @date 2020/12/29 11:13
 */
@Data
public class BaseNode implements INode {

    /**
     * 主键ID
     */
    protected Integer id;

    /**
     * 父节点ID
     */
    protected Integer parentId;

    /**
     * 子孙节点
     */
    protected List<INode> children = new ArrayList<>();

}
