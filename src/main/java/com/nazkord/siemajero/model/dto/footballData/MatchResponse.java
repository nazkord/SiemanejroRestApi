package com.nazkord.siemajero.model.dto.footballData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchResponse {
    private Integer count;
    private ResponseFilters filters;
    private List<Match> matches;

    public ResponseFilters getFilters() {
        return filters;
    }

    public void setFilters(ResponseFilters filters) {
        this.filters = filters;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "MatchResponse{" +
                "count=" + count +
                ", filters=" + filters +
                ", matches=" + matches +
                '}';
    }
}
