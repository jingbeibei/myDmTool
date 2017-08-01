package com.dm.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: 景贝贝
 * Date: 2017/7/29
 * Time: 18:34
 */
@Entity
@Table( name = "T_TABLE")
public class DMTable {
    @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @Column(length = 32)
//    private Integer id;

    @Column(length = 32)
    private String PoClassName;

    @Column(length = 32)
    private String MyTableName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name="PoClassName", length=32)
    public String getPoClassName() {
        return PoClassName;
    }

    public void setPoClassName(String POClassName) {
        this.PoClassName = POClassName;
    }
    @Column(name="MyTableName", length=32)
    public String getMyTableName() {
        return MyTableName;
    }

    public void setMyTableName(String tableName) {
        MyTableName = tableName;
    }
}
