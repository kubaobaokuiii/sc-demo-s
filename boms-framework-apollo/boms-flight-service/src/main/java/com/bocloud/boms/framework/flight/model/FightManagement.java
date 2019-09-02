package com.bocloud.boms.framework.flight.model;


import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name ="fight_management" )
public class FightManagement implements Serializable {

  private String time;
  private String endtime;
  private String starttime;
  private String number;

  @Column(name = "fight_id")
  private long fightId;
  private String remark;
  private String people;
  private long id;
  private String capacity;


  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }


  public String getEndtime() {
    return endtime;
  }

  public void setEndtime(String endtime) {
    this.endtime = endtime;
  }


  public String getStarttime() {
    return starttime;
  }

  public void setStarttime(String starttime) {
    this.starttime = starttime;
  }


  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }


  public long getFightId() {
    return fightId;
  }

  public void setFightId(long fightId) {
    this.fightId = fightId;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


  public String getPeople() {
    return people;
  }

  public void setPeople(String people) {
    this.people = people;
  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getCapacity() {
    return capacity;
  }

  public void setCapacity(String capacity) {
    this.capacity = capacity;
  }

}
