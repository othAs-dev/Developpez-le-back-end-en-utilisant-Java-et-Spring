package org.openclassrooms.chatop.exceptions;

import org.springframework.stereotype.Service;

public class ApiException extends RuntimeException {
  public ApiException(String message) {
    super(message);
  }

  public static class NotFoundException extends ApiException {
    public NotFoundException(String message) {
      super(message);
    }
  }

  public static class BadRequestException extends ApiException {
    public BadRequestException(String message) {
      super(message);
    }
  }
}
