package com.cursojava.mspersonas.client.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RenaperData {
    @JsonProperty("dni")
    private int dni;
    @JsonProperty("isAuthorize")
    private Boolean isAuthorize;

    @Override
    public String toString() {
        return "RenaperResponse{" +
                "dni=" + dni +
                ", isAuthorize=" + isAuthorize +
                '}';
    }
}
