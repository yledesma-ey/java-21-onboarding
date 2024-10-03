package com.cursojava.mspersonas.client;

import com.cursojava.mspersonas.client.response.RenaperResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "renaper", url = "${base.service.url}")
public interface RenaperClient {

    @GetMapping("/service/renaper")
    RenaperResponse isAuthorize(@RequestParam("dni")String dni);
}
