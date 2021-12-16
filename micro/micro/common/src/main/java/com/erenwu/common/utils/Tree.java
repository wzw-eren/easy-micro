package com.erenwu.common.utils;

import java.util.List;

/**
 * Tree接口
 *
 * @param <T> 树节点
 */
public interface Tree<T extends Tree<T>> {

    Object getId();

    Object getParentId();

    List<T> getChildren();

    void setChildren(List<T> children);
}