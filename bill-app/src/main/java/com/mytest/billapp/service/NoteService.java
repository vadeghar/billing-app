package com.mytest.billapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import com.mytest.billapp.model.Notes;

public interface NoteService {
	
	public Notes save(Notes entity);
	public List<Notes> saveAll(List<Notes> entities);
	public Notes saveAndFlush(Notes entity);
	public void flush();
	public void deleteById(Long id);
	public void delete(Notes entity);
	public void deleteAll(List<Notes> entities);
	public void deleteInBatch(List<Notes> entities);
	public void deleteAll();
	public Notes getOne(Long id);
	public Optional<Notes> findById(Long id);
	public boolean existsById(Long id);
	public List<Notes> findAll();
	public List<Notes> findAll(Sort sort);
	public long count();

}
