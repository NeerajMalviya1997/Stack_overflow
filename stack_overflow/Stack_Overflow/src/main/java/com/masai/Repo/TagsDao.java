package com.masai.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Tags;
import com.masai.model.User;
@Repository
public interface TagsDao extends JpaRepository<Tags, Integer> {
	public Optional<Tags> findByTagName(String name);
}
