package com.corgi.core.config.mybatis;

import com.baomidou.mybatisplus.core.parser.AbstractJsqlParser;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.update.Update;

/**
 * @description: 数据权限解析
 * @author: dengmiao
 * @create: 2019-04-11 15:49
 **/
@Slf4j
public class DataPermissionSqlParser extends AbstractJsqlParser {

    @Override
    public void processInsert(Insert insert) {

    }

    @Override
    public void processDelete(Delete delete) {

    }

    @Override
    public void processUpdate(Update update) {

    }

    @Override
    public void processSelectBody(SelectBody selectBody) {
        selectBody.accept(new SelectVisitor() {
            /**
             * 正常的select
             * @param plainSelect
             */
            @Override
            public void visit(PlainSelect plainSelect) {
                // 访问 select
                if (plainSelect.getSelectItems() != null) {
                    plainSelect.getSelectItems().forEach(u -> {
                        // log.error("select {}", u.toString());
                    });
                }
                // 访问from
                FromItem fromItem = plainSelect.getFromItem();
                // log.error("form {}", fromItem.toString());

                // 访问where
                if (plainSelect.getWhere() != null) {
                    // log.error("where {}", plainSelect.getWhere());
                }
                // 访问join
                if (plainSelect.getJoins() != null) {
                    for (Join join : plainSelect.getJoins()) {
                        // log.error("join {}", join);
                    }
                }

                // 访问 order by
                if (plainSelect.getOrderByElements() != null) {
                    for (OrderByElement orderByElement : plainSelect.getOrderByElements()) {
                        // log.error("order by {}", orderByElement);
                    }
                }

                // 访问group by having
                if (plainSelect.getHaving() != null) {
                    // log.error("having {}", plainSelect.getHaving());
                }

            }

            @Override
            public void visit(SetOperationList setOpList) {

            }

            @Override
            public void visit(WithItem withItem) {

            }

        });
    }
}
