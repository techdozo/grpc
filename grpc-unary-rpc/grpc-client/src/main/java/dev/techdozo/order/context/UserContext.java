package dev.techdozo.order.context;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class UserContext {

  private UserContext() {}

  private static InheritableThreadLocal<UserInfo> currentUserContext =
      new InheritableThreadLocal<>();

  public static void setUserContext(String userId) {
    log.info("Setting userId to {} ", userId);
    currentUserContext.set(UserInfo.builder().userToken(userId).build());
  }

  public static UserInfo getUserContext() {
    return currentUserContext.get();
  }

  public static void clear() {
    log.info("Removing user context, current context {}", currentUserContext.get());
    currentUserContext.remove();
  }
}
