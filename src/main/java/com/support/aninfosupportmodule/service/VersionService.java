package com.support.aninfosupportmodule.service;

import com.support.aninfosupportmodule.repository.VersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class VersionService {

    private final VersionRepository versionRepository;

    public List<String> getVersionsByProductId(Long productId) {
        return versionRepository.getVersionsByProductId(productId);
    }
}
