package com.corgi.postgres.entity;

import com.corgi.postgres.dialect.JsonbType;
import com.corgi.postgres.dialect.ListJsonConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @description:
 * @author: dengmiao
 * @create: 2019-05-05 11:56
 **/
@Entity
@Table(name = "person", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TypeDefs({
        @TypeDef(name = "JsonbTypeBody", typeClass = JsonbType.class, parameters = {
                @org.hibernate.annotations.Parameter(name = JsonbType.CLASS, value = "com.corgi.postgres.entity.Body")}),
        @TypeDef(name = "JsonbTypeName", typeClass = JsonbType.class, parameters = {
                @org.hibernate.annotations.Parameter(name = JsonbType.CLASS, value = "com.corgi.postgres.entity.Name")}),
        @TypeDef(name = "JsonbTypeMap", typeClass = JsonbType.class, parameters = {
                @org.hibernate.annotations.Parameter(name = JsonbType.CLASS, value = "java.util.HashMap")}),
})
public class Person implements Serializable {

    @Id
    @GeneratedValue
    protected long id;

    @Column(columnDefinition = "jsonb")
    @Type(type = "JsonbTypeName")
    private Name name;

    @Column(columnDefinition = "jsonb")
    @Type(type = "JsonbTypeBody")
    private Body body;

    @Column(columnDefinition = "jsonb")
    @Type(type = "JsonbTypeMap")
    private HashMap tags;

    @Convert(converter = ListJsonConverter.class)
    private List<String> wives;

}













