package com.cursojava.mspersonas.client.response;

import com.cursojava.mspersonas.client.data.RenaperData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RenaperResponse {
    @JsonProperty("response")
    private List<RenaperData> response;

    @Override
    public String toString() {
        return "RenaperWrapperResponse{" +
                "response=" + response +
                '}';
    }
}
