package com.mytest.billapp.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytest.billapp.exceptions.ResourceNotFoundException;
import com.mytest.billapp.model.Notes;
import com.mytest.billapp.model.Product;
import com.mytest.billapp.model.Vendor;
import com.mytest.billapp.repsitory.NotesRepository;
import com.mytest.billapp.service.ProductService;
import com.mytest.billapp.service.VendorService;

@RestController
@RequestMapping("/ajax")
public class NoteRestController {
	
	@Autowired
	NotesRepository noteRepository;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	VendorService vendorService;
	
	@GetMapping("/notes")
	public List<Notes> getAllNotes() {
	    return noteRepository.findAll();
	}
	
	@PostMapping("/notes")
	public Notes createNote(@Valid @RequestBody Notes note) {
	    return noteRepository.save(note);
	}
	
	@GetMapping("/vendor/{id}")
	public Vendor getVendorById(@PathVariable(value = "id") Long noteId) {
	    try {
			return vendorService.findById(noteId).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/product/{id}")
	public Map<Integer, String> getProductSizeListById(@PathVariable(value = "id") Long productId) {
	    try {
			return productService.getProductSizeList(productId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@GetMapping("/notes/{id}")
	public Notes getNoteById(@PathVariable(value = "id") Long noteId) {
	    return noteRepository.findById(noteId)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
	}
	
	@PutMapping("/notes/{id}")
	public Notes updateNote(@PathVariable(value = "id") Long noteId,  @Valid @RequestBody Notes noteDetails) {

	    Notes note = noteRepository.findById(noteId)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
	    note.setTitle(noteDetails.getTitle());
	    note.setContent(noteDetails.getContent());
	    Notes updatedNote = noteRepository.save(note);
	    return updatedNote;
	}
	
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
	    Notes note = noteRepository.findById(noteId)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

	    noteRepository.delete(note);

	    return ResponseEntity.ok().build();
	}

}
