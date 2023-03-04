package com.binus.thesis.fisheryapp.base.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseParameter<T> implements BaseInterface {

    @JsonProperty("sort")
    protected Map<String, String> sort;

    @JsonProperty("criteria")
    protected Map<String, String> criteria;

    @JsonProperty("filter")
    protected Map<String, String> filter;

    @JsonProperty("data")
    protected T data;
}
