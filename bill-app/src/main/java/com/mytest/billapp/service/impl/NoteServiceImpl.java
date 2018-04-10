package com.mytest.billapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mytest.billapp.model.Notes;
import com.mytest.billapp.repsitory.NoteRepository;
import com.mytest.billapp.service.NoteService;

@Service
public class NoteServiceImpl implements NoteService {
	
	@Autowired
	NoteRepository noteRepository; 
	
	public Notes save(Notes entity) {
		return noteRepository.save(entity);
	}
	
	public List<Notes> saveAll(List<Notes> entities) {
		return noteRepository.saveAll(entities);
	}
	
	public void flush() {
		noteRepository.flush();
	}
	
	public void deleteById(Long id) {
		noteRepository.deleteById(id);
	}
	
	public void delete(Notes entity) {
		noteRepository.delete(entity);
	}
	
	public void deleteAll(List<Notes> entities) {
		noteRepository.deleteAll(entities);
	}
	
	public void deleteInBatch(List<Notes> entities) {
		noteRepository.deleteInBatch(entities);
	}
	
	public void deleteAll() {
		noteRepository.deleteAll();
	}
	
	public Notes getOne(Long id) {
		return noteRepository.getOne(id);
	}
	public Optional<Notes> findById(Long id) {
		return noteRepository.findById(id);
	}
	
	public boolean existsById(Long id) {
		return noteRepository.existsById(id);
	}
	
	public List<Notes> findAll() {
		return noteRepository.findAll();
	}
	
	public List<Notes> findAll(Sort sort) {
		return noteRepository.findAll(sort);
	}
	
	public long count() {
		return noteRepository.count();
	}
	
	
	
	public Notes saveAndFlush(Notes entity) {
		return noteRepository.saveAndFlush(entity);
	}
	
}
