package com.polstat.perpustakaan.rpc;

import com.polstat.perpustakaan.dto.BookDto;

import java.util.List;

public class JsonRpcResponse {
    private String jsonrpc = "2.0";
    private Object result;
    private Object error;
    private String id;

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public JsonRpcResponse(List<BookDto> books, String id) {
        this.result = books;
        this.id = id;
    }
    public JsonRpcResponse(String jsonrpc, String id) {
        this.result = jsonrpc;
        this.id = id;
    }
}