package io.github.pdkst.models.server.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author pdkst
 * @since 2024/04/20
 */
@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    insufficient_quota("insufficient_quota",
            "You exceeded your current quota, please check your plan and billing details."),
    ;
    private final String type;
    private final String message;
}
