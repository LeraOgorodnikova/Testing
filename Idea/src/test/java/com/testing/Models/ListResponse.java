package com.testing.Models;


import java.util.List;

public class ListResponse<T> {

    List<T> listResponse;

    public List<T> getListResponse() {
        return listResponse;
    }

    public void setListResponse(List<T> listResponse) {
        this.listResponse = listResponse;
    }
}
