package com.wcl.acl.helper;

import com.wcl.acl.entity.PermissionEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 根据权限数据构建菜单数据
 * </p>
 *

 */
public class PermissionHelper {

    /**
     * 使用递归方法建菜单
     * @param treeNodes
     * @return
     */
    public static List<PermissionEntity> bulid(List<PermissionEntity> treeNodes) {
        List<PermissionEntity> trees = new ArrayList<>();
        for (PermissionEntity treeNode : treeNodes) {
            if ("0".equals(treeNode.getPid())) {
                treeNode.setLevel(1);
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    public static PermissionEntity findChildren(PermissionEntity treeNode,List<PermissionEntity> treeNodes) {
        treeNode.setChildren(new ArrayList<PermissionEntity>());

        for (PermissionEntity it : treeNodes) {
            if(treeNode.getId().equals(it.getPid())) {
                int level = treeNode.getLevel() + 1;
                it.setLevel(level);
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return treeNode;
    }
}
