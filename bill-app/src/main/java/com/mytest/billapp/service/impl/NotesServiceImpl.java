package com.mytest.billapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mytest.billapp.model.Notes;
import com.mytest.billapp.repsitory.NotesRepository;
import com.mytest.billapp.service.NotesService;

@Service
public class NotesServiceImpl implements NotesService {
	
	@Autowired
	NotesRepository notesRepository; 
	
	public Notes save(Notes entity) {
		return notesRepository.save(entity);
	}
	
	public List<Notes> saveAll(List<Notes> entities) {
		return notesRepository.saveAll(entities);
	}
	
	public void flush() {
		notesRepository.flush();
	}
	
	public void deleteById(Long id) {
		notesRepository.deleteById(id);
	}
	
	public void delete(Notes entity) {
		notesRepository.delete(entity);
	}
	
	public void deleteAll(List<Notes> entities) {
		notesRepository.deleteAll(entities);
	}
	
	public void deleteInBatch(List<Notes> entities) {
		notesRepository.deleteInBatch(entities);
	}
	
	public void deleteAll() {
		notesRepository.deleteAll();
	}
	
	public Notes getOne(Long id) {
		return notesRepository.getOne(id);
	}
	public Optional<Notes> findById(Long id) {
		return notesRepository.findById(id);
	}
	
	public boolean existsById(Long id) {
		return notesRepository.existsById(id);
	}
	
	public List<Notes> findAll() {
		return notesRepository.findAll();
	}
	
	public List<Notes> findAll(Sort sort) {
		return notesRepository.findAll(sort);
	}
	
	public long count() {
		return notesRepository.count();
	}
	
	
	
	public Notes saveAndFlush(Notes entity) {
		return notesRepository.saveAndFlush(entity);
	}
	
}
