package dev.techdozo.api.product.application.error;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import dev.techdozo.commons.error.ErrorCode;
import dev.techdozo.commons.error.ServiceException;
import dev.techdozo.product.Resources.ErrorDetail;
import io.grpc.StatusRuntimeException;

import java.util.Map;

/** Maps gRPC error to generic {@link ServiceException} */
public class ServiceExceptionMapper {

  public static ServiceException map(StatusRuntimeException error) {

    var status = io.grpc.protobuf.StatusProto.fromThrowable(error);

    ErrorDetail errorDetail = null;

    for (Any any : status.getDetailsList()) {
      if (!any.is(ErrorDetail.class)) {
        continue;
      }
      try {
        errorDetail = any.unpack(ErrorDetail.class);
      } catch (InvalidProtocolBufferException cause) {
        errorDetail =
            ErrorDetail.newBuilder()
                .setErrorCode(ErrorCode.INVALID_OPERATION.getMessage())
                .setMessage(cause.getMessage())
                .putAllMetadata(Map.of())
                .build();
      }
    }

    return new ServiceException(
        ErrorCode.errorCode(errorDetail.getErrorCode()),
        errorDetail.getMessage(),
        errorDetail.getMetadataMap());
  }
}
