package com.corgi.core.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.corgi.core.modules.sys.entity.SysDept;
import com.corgi.core.modules.sys.mapper.SysDeptMapper;
import com.corgi.core.modules.sys.model.DepartIdModel;
import com.corgi.core.modules.sys.model.SysDeptTreeModel;
import com.corgi.core.modules.sys.service.ISysDeptService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: dengmiao
 * @create: 2019-04-12 10:37
 **/
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {

    private static List<DepartIdModel> idList = new ArrayList<>(4);

    @Override
    public List<SysDeptTreeModel> queryTreeList() {
        LambdaQueryWrapper<SysDept> query = new LambdaQueryWrapper<SysDept>();
        query.eq(SysDept::getDelFlag, 0);
        query.orderByAsc(SysDept::getDepartOrder);
        List<SysDept> list = this.list(query);
        // 调用wrapTreeDataToTreeList方法生成树状数据
        List<SysDeptTreeModel> listResult = wrapTreeDataToTreeList(list);
        return listResult;
    }

    /**
     * queryTreeList的子方法 ====1=====
     * 该方法是s将SysDepart类型的list集合转换成SysDepartTreeModel类型的集合
     */
    public static List<SysDeptTreeModel> wrapTreeDataToTreeList(List<SysDept> recordList) {
        // 在该方法每请求一次,都要对全局list集合进行一次清理
        idList.clear();
        List<SysDeptTreeModel> records = new ArrayList<>();
        for (int i = 0; i < recordList.size(); i++) {
            SysDept dept = recordList.get(i);
            records.add(new SysDeptTreeModel(dept));
        }
        List<SysDeptTreeModel> tree = findChildren(records, idList);
        setEmptyChildrenAsNull(tree);
        return tree;
    }

    /**
     * queryTreeList的子方法 ====2=====
     * 该方法是找到并封装顶级父类的节点到TreeList集合
     */
    private static List<SysDeptTreeModel> findChildren(List<SysDeptTreeModel> recordList,
                                                         List<DepartIdModel> idList) {

        List<SysDeptTreeModel> treeList = new ArrayList<>();
        for (int i = 0; i < recordList.size(); i++) {
            SysDeptTreeModel branch = recordList.get(i);
            String parent = branch.getParentId();
            if (parent == null || "".equals(parent)) {
                treeList.add(branch);
                DepartIdModel departIdModel = new DepartIdModel().convert(branch);
                idList.add(departIdModel);
            }
        }
        getGrandChildren(treeList,recordList,idList);
        return treeList;
    }

    /**
     * queryTreeList的子方法====3====
     *该方法是找到顶级父类下的所有子节点集合并封装到TreeList集合
     */
    private static void getGrandChildren(List<SysDeptTreeModel> treeList,List<SysDeptTreeModel> recordList,List<DepartIdModel> idList) {

        for (int i = 0; i < treeList.size(); i++) {
            SysDeptTreeModel model = treeList.get(i);
            DepartIdModel idModel = idList.get(i);
            for (int i1 = 0; i1 < recordList.size(); i1++) {
                SysDeptTreeModel m = recordList.get(i1);
                if(m.getParentId() == null || "".equals(m.getParentId())) {
                    continue;
                }
                if (m.getParentId().equals(model.getId())) {
                    model.getChildren().add(m);
                    DepartIdModel dim = new DepartIdModel().convert(m);
                    idModel.getChildren().add(dim);
                }
            }
            getGrandChildren(treeList.get(i).getChildren(), recordList, idList.get(i).getChildren());
        }
    }

    /**
     * queryTreeList的子方法 ====4====
     * 该方法是将子节点为空的List集合设置为Null值
     */
    private static void setEmptyChildrenAsNull(List<SysDeptTreeModel> treeList) {

        for (int i = 0; i < treeList.size(); i++) {
            SysDeptTreeModel model = treeList.get(i);
            if (model.getChildren().size() == 0) {
                model.setChildren(null);
            }else{
                setEmptyChildrenAsNull(model.getChildren());
            }
        }
    }
}
