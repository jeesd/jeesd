package com.jeesd.common.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TreeNode {

    protected Long id;
    protected Long parentId;
    protected List<TreeNode> children = new ArrayList<TreeNode>();

    public void add(TreeNode node) {
        children.add(node);
    }
}
