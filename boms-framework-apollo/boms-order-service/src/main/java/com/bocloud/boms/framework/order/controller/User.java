package com.bocloud.boms.framework.order.controller;

import java.util.Date;

/**
 * @author gs
 * 用户类
 * @create 2019/7/30
 */
public class User {

    private int id;
    private String username;
    private int age;
    private Date ctm;

    public User() {
    }

    public int getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public int getAge() {
        return this.age;
    }

    public Date getCtm() {
        return this.ctm;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public void setCtm(final Date ctm) {
        this.ctm = ctm;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof User)) {
            return false;
        } else {
            User other = (User)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getId() != other.getId()) {
                return false;
            } else {
                label41: {
                    Object this$username = this.getUsername();
                    Object other$username = other.getUsername();
                    if (this$username == null) {
                        if (other$username == null) {
                            break label41;
                        }
                    } else if (this$username.equals(other$username)) {
                        break label41;
                    }

                    return false;
                }

                if (this.getAge() != other.getAge()) {
                    return false;
                } else {
                    Object this$ctm = this.getCtm();
                    Object other$ctm = other.getCtm();
                    if (this$ctm == null) {
                        if (other$ctm != null) {
                            return false;
                        }
                    } else if (!this$ctm.equals(other$ctm)) {
                        return false;
                    }

                    return true;
                }
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof User;
    }

    @Override
    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        result = result * 59 + this.getId();
        Object $username = this.getUsername();
        result = result * 59 + ($username == null ? 43 : $username.hashCode());
        result = result * 59 + this.getAge();
        Object $ctm = this.getCtm();
        result = result * 59 + ($ctm == null ? 43 : $ctm.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "User(id=" + this.getId() + ", username=" + this.getUsername() + ", age=" + this.getAge() + ", ctm=" + this.getCtm() + ")";
    }

}
