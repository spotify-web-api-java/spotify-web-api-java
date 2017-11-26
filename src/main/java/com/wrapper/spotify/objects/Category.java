package com.wrapper.spotify.objects;

import java.util.List;

public class Category {

  private String href;
  private List<Image> icons;
  private String id;
  private String name;

  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public List<Image> getIcons() {
    return icons;
  }

  public void setIcons(List<Image> icons) {
    this.icons = icons;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
