package com.example.calculator.controller;

import com.example.calculator.rpc.JsonRpcRequest;
import com.example.calculator.rpc.JsonRpcResponse;
import com.example.calculator.service.CalculatorService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class JsonRpcController {
    @Autowired
    private CalculatorService calculatorService;
    @PostMapping("/jsonrpc")
    public ResponseEntity<Object> handleJsonRpcRequest(@RequestBody JsonRpcRequest request) {
        try {
            String method = request.getMethod();
            JsonNode params = request.getParams();
            double a = params.get("a").asDouble();
            double b = params.get("b").asDouble();
            double result;
            JsonRpcResponse response = new JsonRpcResponse();
            response.setId(request.getId());

            switch (method) {
                case "add":
                    result = calculatorService.add(a, b);
                    response.setResult(result);
                    return ResponseEntity.ok(response);
                case "subtract":
                    result = calculatorService.subtract(a, b);
                    response.setResult(result);
                    return ResponseEntity.ok(response);
                case "multiply":
                    result = calculatorService.multiply(a, b);
                    response.setResult(result);
                    return ResponseEntity.ok(response);
                case "divide":
                    result = calculatorService.divide(a, b);
                    response.setResult(result);
                    return ResponseEntity.ok(response);
                default:
                    response.setError("Method not found");
                    return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            JsonRpcResponse errorResponse = new JsonRpcResponse();
            errorResponse.setJsonrpc("2.0");
            errorResponse.setId(request.getId());
            errorResponse.setError("Invalid request");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}