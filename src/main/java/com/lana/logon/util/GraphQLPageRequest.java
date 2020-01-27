package com.lana.logon.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GraphQLPageRequest extends PageRequest {

    @JsonCreator
    public GraphQLPageRequest(int page, int size, List<String> sorts) {
        super(page, size, GraphQLSort.of(sorts));
    }
}
