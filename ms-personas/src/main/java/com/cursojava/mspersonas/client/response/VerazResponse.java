package com.cursojava.mspersonas.client.response;

import com.cursojava.mspersonas.client.data.VerazData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class VerazResponse {
    @JsonProperty("response")
    private List<VerazData> response;

    @Override
    public String toString() {
        return "VerazResponse{" +
                "response=" + response +
                '}';
    }
}
