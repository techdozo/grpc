package dev.techdozo.order.context;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserInfo {
    private String userToken;
}
