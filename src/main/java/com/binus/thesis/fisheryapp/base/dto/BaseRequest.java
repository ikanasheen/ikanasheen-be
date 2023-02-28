package com.binus.thesis.fisheryapp.base.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseRequest<T> implements BaseInterface {

    @JsonProperty("paging")
    protected Paging paging;

    @JsonProperty("parameter")
    protected T parameter;
}
