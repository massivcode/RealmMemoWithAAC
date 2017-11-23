package com.massivcode.realmwithandroidarchitecturecomponents.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import java.util.Date;

/**
 * Created by massivcode@gmail.com on 2017. 11. 22. 13:46
 */

public class Memo extends RealmObject {
  @PrimaryKey
  private long id;

  private String title;
  private String contents;
  private Date createdAt;
  private Date updatedAt;

  public Memo() {
  }

  public Memo(String title, String contents, Date createdAt, Date updatedAt) {
    this.title = title;
    this.contents = contents;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Memo{");
    sb.append("id=").append(id);
    sb.append(", title='").append(title).append('\'');
    sb.append(", contents='").append(contents).append('\'');
    sb.append(", createdAt=").append(createdAt);
    sb.append(", updatedAt=").append(updatedAt);
    sb.append('}');
    return sb.toString();
  }
}
