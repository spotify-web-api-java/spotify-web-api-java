package com.wrapper.spotify;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import java.net.URI;

public class HttpDeleteBody extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "DELETE";

    public HttpDeleteBody() {
    }

    public HttpDeleteBody(URI uri) {
        this.setURI(uri);
    }

    public HttpDeleteBody(String uri) {
        this.setURI(URI.create(uri));
    }

    public String getMethod() {
        return "DELETE";
    }
}

