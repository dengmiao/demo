package com.corgi.core.config.mybatis;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.*;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.corgi.core.config.mybatis.DataScopeFilter.dataScopeFilter;

/**
 * @description:
 * @author: dengmiao
 * @create: 2019-04-11 13:49
 **/
@Slf4j
public class SelectVisitorImpl implements SelectVisitor {

    @Override
    public void visit(PlainSelect plainSelect) {
        //判断有没有关联关系，如果没有，说明为单表
        if (CollectionUtils.isEmpty(plainSelect.getJoins())) {
            //如果没有表别名
            if (StringUtils.isEmpty(plainSelect.getFromItem().getAlias())) {
                //拼接权限语句
                dataScopeFilter();
            }
            //如果具有表别名
            else {
                //别名
                String alias = plainSelect.getFromItem().getAlias().getName();
                //拼接权限语句
            }

        }
        //如果有关联关系
        else {

        }


        List<Join> joins = plainSelect.getJoins();
        //为空说明没有进行任何关联，拼接关联关系
        if (CollectionUtils.isEmpty(joins)) {
            joins = Lists.newArrayList();
            Join join = new Join();
            join.setLeft(true);
            join.setRightItem(new Table("sys_user a"));
            BinaryExpression onExpression = new EqualsTo();
            onExpression.setLeftExpression(new Column("a.id"));
            onExpression.setRightExpression(new Column(((Table) plainSelect.getFromItem()).getName() + ".create_by"));
            join.setOnExpression(onExpression);
            joins.add(join);
            plainSelect.setJoins(joins);
        }
        log.warn("plainSelect: {}", plainSelect);
    }

    @Override
    public void visit(SetOperationList setOperationList) {

    }

    @Override
    public void visit(WithItem withItem) {

    }
}
