package com.binus.thesis.fisheryapp.base.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonView(Views.Basic.class)
public class BaseResponse<T>{

    private static final long serialVersionUID = -5728598166275182978L;

    @JsonProperty("status")
    protected Status status;

    @JsonProperty("paging")
    protected Paging paging;

    @JsonProperty("result")
    protected T result;
}
