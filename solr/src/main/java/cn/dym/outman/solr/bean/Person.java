package cn.dym.outman.solr.bean;

import org.apache.solr.client.solrj.beans.Field;

/**
 * Person bean
 * 
 * @author Lijie
 *
 */
public class Person {

    private String id;
    private String name;
    private int age;
    private String addr;

    public String getId() {
        return id;
    }

    @Field
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Field
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    @Field
    public void setAge(int age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    @Field
    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Person(String id, String name, int age, String addr) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.addr = addr;
    }

    public Person() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", age=" + age
                + ", addr=" + addr + "]";
    }

}