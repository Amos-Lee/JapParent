package com.jos.jap.core.security.permission;

import org.springframework.http.HttpMethod;

import java.util.Objects;

public class PublicPermission {
    public final String path;
    public final HttpMethod method;

    public PublicPermission(String path, HttpMethod method) {
        this.path = path;
        this.method = method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            PublicPermission that = (PublicPermission) o;
            return Objects.equals(this.path, that.path) && this.method == that.method;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(new Object[]{this.path, this.method});
    }
}
