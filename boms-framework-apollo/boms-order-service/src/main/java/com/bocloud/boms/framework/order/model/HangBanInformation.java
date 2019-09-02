package com.bocloud.boms.framework.order.model;


import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "hang_ban_information")
public class HangBanInformation {

  @Column(name = "book_tickets_time")
  private String bookTicketsTime;

  @Column(name = "hang_ban_capa")
  private long hangBanCapa;

  @Column(name = "hang_ban_id")
  private long hangBanId;

  @Column(name = "fight_id")
  private long fightId;

  private long id;

  /**
  用于测试并发的版本号
   */
  private  int version;


  public String getBookTicketsTime() {
    return bookTicketsTime;
  }

  public void setBookTicketsTime(String bookTicketsTime) {
    this.bookTicketsTime = bookTicketsTime;
  }


  public long getHangBanCapa() {
    return hangBanCapa;
  }

  public void setHangBanCapa(long hangBanCapa) {
    this.hangBanCapa = hangBanCapa;
  }


  public long getHangBanId() {
    return hangBanId;
  }

  public void setHangBanId(long hangBanId) {
    this.hangBanId = hangBanId;
  }


  public long getFightId() {
    return fightId;
  }

  public void setFightId(long fightId) {
    this.fightId = fightId;
  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }
}
