package com.cursojava.mspersonas.client;

import com.cursojava.mspersonas.client.response.VerazResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "veraz", url = "${base.service.url}")
public interface VerazClient {
    @GetMapping("/service/veraz")
    VerazResponse getScore(@RequestParam("dni")String dni);
}
