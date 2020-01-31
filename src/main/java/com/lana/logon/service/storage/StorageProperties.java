package com.lana.logon.service.storage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "storage.source")
public class StorageProperties {
    private String path;
}