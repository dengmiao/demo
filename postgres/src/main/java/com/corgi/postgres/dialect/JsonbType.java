package com.corgi.postgres.dialect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.HibernateException;
import org.hibernate.boot.registry.classloading.internal.ClassLoaderServiceImpl;
import org.hibernate.boot.registry.classloading.spi.ClassLoaderService;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.SerializationException;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;
import org.postgresql.util.PGobject;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 自定义jsonb数据类型
 * 实现了Map映射PGObject(postgres对象类型),通过ObjectMapper来实现两个数据类型的转换
 * @description:
 * @author: dengmiao
 * @create: 2019-05-05 11:56
 **/
public class JsonbType implements UserType, ParameterizedType {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final ClassLoaderService classLoaderService = new ClassLoaderServiceImpl();

    public static final String CLASS = "CLASS";

    public static final String JSONB_TYPE = "jsonb";

    private Class<?> jsonClassType;

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] names, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException, SQLException {
        try {
            final String json = resultSet.getString(names[0]);
            return json == null ? null : objectMapper.readValue(json, jsonClassType);
        } catch (IOException e) {
            throw new HibernateException(e);
        }
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {
        try {
            final String json = value == null ? null : objectMapper.writeValueAsString(value);
            PGobject pgo = new PGobject();
            pgo.setType(JSONB_TYPE);
            pgo.setValue(json);
            preparedStatement.setObject(index, pgo);
        } catch (JsonProcessingException e) {
            throw new HibernateException(e);
        }
    }

    @Override
    public Object deepCopy(Object originalValue) throws HibernateException {
        if (!(originalValue instanceof Collection)) {
            return originalValue;
        }

        Collection<?> collection = (Collection) originalValue;
        Collection collectionClone = CollectionFactory.newInstance(collection.getClass());

        collectionClone.addAll(collection.stream().map(this::deepCopy).collect(Collectors.toList()));

        return collectionClone;
    }

    static final class CollectionFactory {
        @SuppressWarnings("unchecked")
        static <E, T extends Collection<E>> T newInstance(Class<T> collectionClass) {
            if (List.class.isAssignableFrom(collectionClass)) {
                return (T) new ArrayList<E>();
            } else if (Set.class.isAssignableFrom(collectionClass)) {
                return (T) new HashSet<E>();
            } else {
                throw new IllegalArgumentException("Unsupported collection type : " + collectionClass);
            }
        }
    }


    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        Object copy = deepCopy(value);
        if (copy instanceof Serializable) {
            return (Serializable) copy;
        }
        throw new SerializationException(
                String.format("Cannot serialize '%s', %s is not Serializable.", value, value.getClass()), null);
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return deepCopy(cached);
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return deepCopy(original);
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        assert (x != null);

        return x.hashCode();
    }


    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return ObjectUtils.nullSafeEquals(x, y);
    }

    @Override
    public Class<?> returnedClass() {
        return Map.class;
    }

    @Override
    public int[] sqlTypes() {
        return new int[]{Types.JAVA_OBJECT};
    }

    @Override
    public void setParameterValues(Properties parameters) {
        final String clazz = (String) parameters.get(CLASS);
        jsonClassType = classLoaderService.classForName(clazz);

    }

}