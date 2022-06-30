package com.support.aninfosupportmodule.service;

import com.support.aninfosupportmodule.entity.Ticket;
import com.support.aninfosupportmodule.entity.Version;
import com.support.aninfosupportmodule.repository.TicketRepository;
import com.support.aninfosupportmodule.repository.VersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class VersionService {

    private final VersionRepository versionRepository;

    public List<Version> getVersions() {
        List<Version> versions = (List<Version>) versionRepository.findAll();
        return versions;
    }
}
