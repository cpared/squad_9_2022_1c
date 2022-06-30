package com.support.aninfosupportmodule.repository;

import com.support.aninfosupportmodule.entity.Version;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "versions", collectionResourceRel = "versions")
public interface VersionRepository extends CrudRepository<Version, Integer> {

}
