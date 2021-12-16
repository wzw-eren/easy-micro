package com.erenwu.common.utils;



import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 树工具类
 */
public class TreeUtil {
    /**
     * 将具有逻辑树形结构的列表转化为物理结构的森林。
     *
     * @param list 平铺的列表，每个元素必须包含能形成属性结构的关键属性，如parentId、id
     */
    public static <T extends Tree<T>> List<T> convertToForest(List<T> list) {
        // 找出第一层节点
        List<T> topTreeBeans = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            boolean isTop = true;
            for (T treeBaseBean : list) {
                if (Objects.equals(list.get(i).getParentId(), treeBaseBean.getId())) {
                    isTop = false;
                    break;
                }
            }
            if (isTop) {
                topTreeBeans.add(list.get(i));
            }
        }
        for (T top : topTreeBeans) {
            List<T> returnList = new ArrayList<>();
            for (T res : list) {
                //判断对象是否为根节点
                if (top.getId().equals(res.getParentId())) {
                    //该节点为根节点,开始递归
                    //通过递归为节点设置childList
                    recursionFn(list, res);
                    returnList.add(res);
                }
            }
            if (top.getChildren() == null) {
                top.setChildren(new ArrayList<>());
            }
            top.getChildren().addAll(returnList);
        }
        return topTreeBeans;
    }

    /**
     * 递归列表
     * 通过递归,给指定t节点设置childList
     */
    private static <T extends Tree<T>> void recursionFn(List<T> list, T t) {
        //只能获取当前t节点的子节点集,并不是所有子节点集
        List<T> childList = getChildList(list, t);
        //设置他的子集对象集
        t.setChildren(childList);
        //迭代子集对象集
        //遍历完,则退出递归
        for (T nextChild : childList) {
            //判断子集对象是否还有子节点
            if (!CollectionUtil.isEmpty(childList)) {
                //有下一个子节点,继续递归
                recursionFn(list, nextChild);
            }
        }
    }

    /**
     * 获得指定节点下的所有子节点
     */
    private static <T extends Tree<T>> List<T> getChildList(List<T> list, T t) {
        List<T> childList = new ArrayList<>();
        //遍历集合元素,如果元素的父id==指定元素的id,则说明是该元素的子节点
        for (T t1 : list) {
            if (t.getId().equals(t1.getParentId())) {
                childList.add(t1);
            }
        }
        return childList;
    }

    /**
     * 递归展开树为平铺的列表
     *
     * @param sourceTree 树列表，可将多棵树一起展开
     * @param filter     过滤器，展开过程中可以过滤不需要的节点，可传null不做过滤
     * @param <T>        树节点类型
     * @return 展开的节点列表
     */
    public static <T extends Tree<T>> List<T> recursiveExpandTree(List<T> sourceTree, Predicate<? super T> filter) {
        if (CollUtil.isEmpty(sourceTree)) {
            return CollUtil.newArrayList();
        }
        List<T> result = new ArrayList<>();
        for (T tree : sourceTree) {
            if (tree != null && (filter == null || filter.test(tree))) {
                result.add(tree);
                if (CollUtil.isNotEmpty(tree.getChildren())) {
                    result.addAll(recursiveExpandTree(tree.getChildren(), filter));
                }
            }
        }
        return result.stream().distinct().collect(Collectors.toList());
    }
}