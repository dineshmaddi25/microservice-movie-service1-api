package com.dineshmaddi.movie_service.repo;

import com.dineshmaddi.movie_service.model.movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface movierepo extends JpaRepository<movie, Long>
{
}
