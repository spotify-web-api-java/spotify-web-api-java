package com.wrapper.spotify.models;

import java.util.ArrayList;
import java.util.List;

public class NewReleases
{
    private List<SimpleAlbum> simpleAlbums = new ArrayList<SimpleAlbum>();
    private String href = "";
    private int offset = 0;
    private int limit = 0;
    private String next = "";
    private String previous = "";
    private int total = 0;

    public List<SimpleAlbum> getSimpleAlbums() {
        return simpleAlbums;
    }

    public void setSimpleAlbums(List<SimpleAlbum> simpleAlbums) {
        this.simpleAlbums = simpleAlbums;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}