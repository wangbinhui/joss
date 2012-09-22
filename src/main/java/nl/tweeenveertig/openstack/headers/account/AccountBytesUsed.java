package nl.tweeenveertig.openstack.headers.account;

import nl.tweeenveertig.openstack.headers.SimpleHeader;
import org.apache.http.HttpResponse;

public class AccountBytesUsed extends SimpleHeader {

    public static final String X_ACCOUNT_BYTES_USED       = "X-Account-Bytes-Used";

    public AccountBytesUsed(String value) {
        super(value);
    }

    @Override
    public String getHeaderName() {
        return X_ACCOUNT_BYTES_USED;
    }

    public static AccountBytesUsed fromResponse(HttpResponse response) {
        return new AccountBytesUsed(convertResponseHeader(response, X_ACCOUNT_BYTES_USED));
    }
}
