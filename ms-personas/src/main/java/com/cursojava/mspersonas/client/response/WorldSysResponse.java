package com.cursojava.mspersonas.client.response;

import com.cursojava.mspersonas.client.data.WorldSysData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorldSysResponse {
    @JsonProperty("response")
    private List<WorldSysData> response;

    @Override
    public String toString() {
        return "WorldSysResponse{" +
                "response=" + response +
                '}';
    }
}
