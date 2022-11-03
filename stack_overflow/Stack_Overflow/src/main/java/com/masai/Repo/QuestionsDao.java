package com.masai.Repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Questions;
@Repository
public interface QuestionsDao extends JpaRepository<Questions, Integer> {

}
