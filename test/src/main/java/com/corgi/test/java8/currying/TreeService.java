package com.corgi.test.java8.currying;

import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @title: TreeService
 * @description:
 * @author: dengmiao
 * @create: 2019-05-10 10:45
 **/
public class TreeService {

    /**
     * 模拟数据
     */
    static List<Tree> data = new ArrayList<Tree>(){
        {
            add(new Tree().setId(1).setName("一级菜单1").setPId(0));
            add(new Tree().setId(2).setName("一级菜单2").setPId(0));
            add(new Tree().setId(3).setName("一级菜单3").setPId(0));
            add(new Tree().setId(4).setName("二级菜单1-4").setPId(1));
            add(new Tree().setId(5).setName("二级菜单1-5").setPId(1));
            add(new Tree().setId(6).setName("三级菜单1-5-6").setPId(5));
            add(new Tree().setId(7).setName("二级菜单2-7").setPId(2));
            add(new Tree().setId(8).setName("二级菜单1-5-6-8").setPId(6));
        }
    };

    /**
     * 构建层次结构的树
     * @return
     */
    public List<Tree> buildTree() {
        // 过滤根节点
        List<Tree> root = findByPId(0);
        iterate(root);
        return root;
    }

    public List<Tree> findByPId(final Integer pId) {
        List<Tree> children = data.stream().filter((tree) -> pId.equals(tree.getPId())).collect(Collectors.toList());
        return children;
    }

    public TailCall<Tree> iterate(List<Tree> data) {
        for(Tree tree : data) {
            final List<Tree> children = findByPId(tree.getId());
            tree.setChildren(children);
            if(children != null && children.size() > 0) {
                TailCalls.call(() -> iterate(children)).invoke();
            }
        }
        return TailCalls.done(null);
    }

    public static void main(String[] args) {
        TreeService service = new TreeService();
        System.out.println(JSONUtil.formatJsonStr(JSONUtil.toJsonStr(service.buildTree())));
    }
}
