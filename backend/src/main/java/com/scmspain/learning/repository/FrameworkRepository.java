package com.scmspain.learning.repository;

import com.scmspain.learning.entities.Framework;
import com.scmspain.learning.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FrameworkRepository extends CrudRepository<Framework, Long> {
    public List<Framework> findByOwner(User owner);
}
