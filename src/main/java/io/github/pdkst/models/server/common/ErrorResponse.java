package io.github.pdkst.models.server.common;

import io.github.pdkst.models.openai.api.common.Error;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pdkst
 * @since 2024/04/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    /**
     * 报错信息
     */
    private Error error;

    public static ErrorResponse of(ErrorCode errorCode) {
        final Error error = new Error();
        error.setCode(errorCode.name());
        error.setType(errorCode.getType());
        error.setMessage(errorCode.getMessage());
        error.setParam(null);
        return of(error);
    }

    public static ErrorResponse of(String code, String message) {
        final Error error = new Error();
        error.setCode(code);
        error.setMessage(message);
        return of(error);
    }

    public static ErrorResponse of(Error error) {
        return new ErrorResponse(error);
    }
}
