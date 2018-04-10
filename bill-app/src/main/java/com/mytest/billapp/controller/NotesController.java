package com.mytest.billapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mytest.billapp.exceptions.ResourceNotFoundException;
import com.mytest.billapp.model.Notes;
import com.mytest.billapp.service.NoteService;

@Controller
public class NotesController {
	
	@Autowired
	NoteService noteService;
	
	@GetMapping("/notesList")
	public String getAllNotes(Model model) {
		model.addAttribute("notesList", noteService.findAll());
	    return "notesList";
	}
	
	//@PostMapping("/saveNotes")
	@RequestMapping(value = "saveNotes", method = RequestMethod.POST)
	public String saveNotes(@ModelAttribute Notes notes, Model model) {
		System.out.println("DFDFD");
		model.addAttribute("notes", noteService.save(notes));
	    return "notes";
	}
	
	@GetMapping("/notes/{id}")
	public String getNoteById(@PathVariable(value = "id") Long noteId, Model model) {
		if(noteId == null || noteId.intValue() == 0)
			model.addAttribute("notes", new Notes());
		model.addAttribute("notes", noteService.findById(noteId));
	    /*return noteService.findById(noteId)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));*/
		return "notes";
	}
	
	@PutMapping("/notes/update/{id}")
	public String updateNote(@PathVariable(value = "id") Long noteId,  @Valid @ModelAttribute Notes noteDetails, Model model) {

	    Notes note = noteService.findById(noteId)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
	    note.setTitle(noteDetails.getTitle());
	    note.setContent(noteDetails.getContent());
	    model.addAttribute("notes",noteService.save(note));
	    return "notes";
	}
	
	@GetMapping("/notes/delete/{id}")
	public String deleteNote(@PathVariable(value = "id") Long noteId, Model model) {
	    Notes note = noteService.findById(noteId)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
	    noteService.delete(note);
	    return getAllNotes(model);
	}
}
