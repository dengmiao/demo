package com.corgi.postgres.dialect;

import org.hibernate.dialect.PostgreSQL95Dialect;
import org.hibernate.type.StringType;

import java.sql.Types;

/**
 * 自定义方言
 * @description:
 * @author: dengmiao
 * @create: 2019-05-05 11:56
 **/
public class JsonbPostgresDialect extends PostgreSQL95Dialect {

	public JsonbPostgresDialect() {
		super();
        registerColumnType(Types.JAVA_OBJECT, "jsonb");
        registerHibernateType( Types.ARRAY, StringType.class.getName());
	}
}