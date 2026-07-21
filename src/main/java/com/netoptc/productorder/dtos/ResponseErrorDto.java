package com.netoptc.productorder.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseErrorDto {

    private Instant moment;
    private String path;
    private Integer status;
    private String message;
    private List<FieldErrorDto> fieldErrors = new ArrayList<>();

    public ResponseErrorDto(Instant moment, String path, Integer status, String message) {
        this.moment = moment;
        this.path = path;
        this.status = status;
        this.message = message;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FieldErrorDto> getFieldErrors() {
        return fieldErrors;
    }

    public void addFieldError(FieldErrorDto fieldErrorDto) {
        this.fieldErrors.add(fieldErrorDto);
    }

}
