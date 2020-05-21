package com.cat.generic.nodetolist;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 树变list
 *
 * @author LZ
 * @date 2020/4/28 9:33
 **/
public class NodeToList {

    /**
     * 树变list列表
     *
     * @param list
     * @return
     */
    public static List<Student> treeToList(List<Student> list){
        List<Student> resultList = new ArrayList<>(8);
        resultList.addAll(list);
        convert(resultList.get(0),resultList);
        // 移除顶层
        resultList.remove(0);
        return resultList;
    }

    /**
     * 树变list列表
     *
     * @param rootNode 根节点
     * @param list
     */
    private static void convert(Student rootNode, List<Student> list) {
        //树为空时返回
        if (rootNode == null) {
            return;
        }
        //树的子节点为空时，将最后一个节点放入
        if (rootNode.getChildren() == null || rootNode.getChildren().size() == 0) {
            list.add(rootNode);
            return;
        }

        Student studentClone = new Student();
        // 拷贝
        BeanUtils.copyProperties(rootNode,studentClone);
        studentClone.setChildren(null);
        list.add(studentClone);

        //继续遍历子节点
        rootNode.getChildren().forEach(e -> {
            Student d = new Student();
            d.setChildren(e.getChildren());
            d.setId(e.getId());
            e.getId();
            d.setParentId(e.getParentId());
            convert(d, list);
        });
    }

    public static void main(String[] args) {
        List<Student> studentList=new ArrayList<>();
        addData(studentList);
        // list变树
        List<Student> tree = ForestNodeMerger.merge(studentList);
        // 树变list
        List<Student> list = NodeToList.treeToList(tree);
    }

    public static void addData(List<Student> studentList){
        Student student1=new Student();
        Student student2=new Student();
        Student student3=new Student();
        Student student4=new Student();
        student1.setParentId(0);
        student1.setId(1);

        student2.setParentId(1);
        student2.setId(2);
        student3.setParentId(1);
        student3.setId(3);

        student4.setParentId(2);
        student4.setId(4);

        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);
    }



}
