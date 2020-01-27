package com.lana.logon.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GraphQLPageRequest extends PageRequest {

    @JsonCreator
    public GraphQLPageRequest(Integer page, Integer size, List<String> sorts) {
        super(Optional.ofNullable(page).orElse(0),
              Optional.ofNullable(size).orElse(20),
              GraphQLSort.of(sorts));
    }
}
