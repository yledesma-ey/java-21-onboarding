package com.cursojava.mspersonas.client;

import com.cursojava.mspersonas.client.response.WorldSysResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "worldsys", url = "${base.service.url}")
public interface WorldsysClient {

    @GetMapping("/service/worldsys")
    WorldSysResponse isTerrotist(@RequestParam("dni")String dni);
}
