package com.louis.springbootmybatis.entity;

import java.io.Serializable;

public class People implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column people.id
     *
     * @mbg.generated Wed Aug 02 09:12:18 UTC 2023
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column people.name
     *
     * @mbg.generated Wed Aug 02 09:12:18 UTC 2023
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column people.age
     *
     * @mbg.generated Wed Aug 02 09:12:18 UTC 2023
     */
    private Integer age;


    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column people.del_flag
     *
     * @mbg.generated Wed Aug 02 09:12:18 UTC 2023
     */


    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table people
     *
     * @mbg.generated Wed Aug 02 09:12:18 UTC 2023
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column people.id
     *
     * @return the value of people.id
     * @mbg.generated Wed Aug 02 09:12:18 UTC 2023
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column people.id
     *
     * @param id the value for people.id
     * @mbg.generated Wed Aug 02 09:12:18 UTC 2023
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column people.name
     *
     * @return the value of people.name
     * @mbg.generated Wed Aug 02 09:12:18 UTC 2023
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column people.name
     *
     * @param name the value for people.name
     * @mbg.generated Wed Aug 02 09:12:18 UTC 2023
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column people.age
     *
     * @return the value of people.age
     * @mbg.generated Wed Aug 02 09:12:18 UTC 2023
     */
    public Integer getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column people.age
     *
     * @param age the value for people.age
     * @mbg.generated Wed Aug 02 09:12:18 UTC 2023
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table people
     *
     * @mbg.generated Wed Aug 02 09:12:18 UTC 2023
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", age=").append(age);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table people
     *
     * @mbg.generated Wed Aug 02 09:12:18 UTC 2023
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        People other = (People) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getAge() == null ? other.getAge() == null : this.getAge().equals(other.getAge()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table people
     *
     * @mbg.generated Wed Aug 02 09:12:18 UTC 2023
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getAge() == null) ? 0 : getAge().hashCode());
        return result;
    }
}