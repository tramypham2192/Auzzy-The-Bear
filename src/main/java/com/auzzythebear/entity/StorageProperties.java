package com.auzzythebear.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("storage")
public class StorageProperties {
  /** Folder location for storing files */
  private String location =
      "/Users/mypham/Documents/Per_Scholas/Spring_Boot/Demo_Project/registration-login-springboot-security-thymeleaf/src/main/resources/upload-dir";

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }
}
