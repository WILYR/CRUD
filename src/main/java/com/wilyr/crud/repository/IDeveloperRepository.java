package com.wilyr.crud.repository;

import com.wilyr.crud.model.Developer;
import com.wilyr.crud.model.Skill;

import java.util.List;

public interface IDeveloperRepository extends GenericRepository {
    Developer save(Developer developer);

    void delete(Developer developer);

    Developer get(String login);

    Developer update(Developer developer, List<Skill> newSkills);
}
