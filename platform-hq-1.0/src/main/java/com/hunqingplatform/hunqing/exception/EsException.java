package com.hunqingplatform.hunqing.exception;

/**
 * @author Created by jgw136 on 2018/03/14.
 */
public class EsException extends CodePlatformException {

    public EsException() {
    }

    public EsException(String message) {
        super(message);
    }

    public EsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EsException(Throwable cause) {
        super(cause);
    }

    public EsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
