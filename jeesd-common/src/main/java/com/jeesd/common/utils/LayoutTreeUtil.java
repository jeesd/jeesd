package com.jeesd.common.utils;

import com.jeesd.common.dto.TreeNode;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class LayoutTreeUtil {

    /**
     * 循环构建树
     * @param treeNodes
     * @param root
     * @param <T>
     * @return
     */
    public<T extends TreeNode> List<T> buildByLoop(List<T> treeNodes, Object root) {

        List<T> trees = new ArrayList<>();
        for(T treeNode : treeNodes) {
            if(root.equals(treeNode.getParentId())) {
                trees.add(treeNode);
            }
            for(T tp : treeNodes) {
                if(tp.getParentId().equals(treeNode.getId())) {
                    if(treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<>());
                    }
                    treeNode.add(tp);
                }
            }
        }
        return trees;
    }

    /**
     * 递归构建树
     * @param treeNodes
     * @param root
     * @param <T>
     * @return
     */
    public <T extends TreeNode> List<T> buildByrecursion(List<T> treeNodes, Object root) {
        List<T> trees = new ArrayList<T>();
        for (T treeNode : treeNodes) {
            if (root.equals(treeNode.getParentId())) {
                trees.add(findChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }
    /**
     * 递归子节点
     * @param treeNode
     * @param treeNodes
     * @param <T>
     * @return
     */
    public <T extends TreeNode> T findChildren(T treeNode, List<T> treeNodes) {

        for(T tp : treeNodes) {
            if(treeNode.getId().equals(tp.getParentId())) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.add(findChildren(tp, treeNodes));
            }
        }
        return treeNode;
    }
}
