package com.daniel.employeemanager.repo;

import com.daniel.employeemanager.model.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRepo extends JpaRepository<Title, Long> {
}
