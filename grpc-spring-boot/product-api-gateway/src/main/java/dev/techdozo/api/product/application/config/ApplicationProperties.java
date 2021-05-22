package dev.techdozo.api.product.application.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("product")
@Setter
@Getter
public class ApplicationProperties {
    private String host;
    private int port;
}
