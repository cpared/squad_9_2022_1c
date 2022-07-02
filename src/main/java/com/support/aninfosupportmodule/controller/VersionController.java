package com.support.aninfosupportmodule.controller;

import com.support.aninfosupportmodule.service.VersionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/soporte/version")
public class VersionController {

    private final VersionService versionService;

    @GetMapping("{productId}")
    public List<String> getVersionsByProductId(@PathVariable Long productId) {
        return versionService.getVersionsByProductId(productId);
    }
}
