package com.droid.contactdata.common.enums;


import com.droid.contactdata.R;

/**
 * Enum to handle the common exceptions.
 */
public enum ExceptionEnum {

    ConnectException(R.string.err_connect_exception),
    UnknownHostException(R.string.err_unknown_host_exception),
    SSLException(R.string.err_ssl_exception),
    SocketTimeoutException(R.string.err_socket_timeout_exception),
    SSLHandshakeException(R.string.err_ssl_handshake_exception);

    private int mMsgId = 0;

    ExceptionEnum(int msgId) {
        this.mMsgId = msgId;
    }

    /**
     * @return the msgId
     */
    public int getMsgId() {
        return mMsgId;
    }
}
