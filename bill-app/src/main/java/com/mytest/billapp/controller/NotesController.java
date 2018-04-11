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
import com.mytest.billapp.service.NotesService;

@Controller
public class NotesController {
	
	@Autowired
	NotesService notesService;
	
	@GetMapping("/notesList")
	public String getAllNotes(Model model) {
		model.addAttribute("notesList", notesService.findAll());
	    return "notesList";
	}
	
	//@PostMapping("/saveNotes")
	@RequestMapping(value = "saveNotes", method = RequestMethod.POST)
	public String saveNotes(@ModelAttribute Notes notes, Model model) {
		System.out.println("DFDFD");
		model.addAttribute("notes", notesService.save(notes));
	    return "notes";
	}
	
	@GetMapping("/notes/{id}")
	public String getNotesById(@PathVariable(value = "id") Long id, Model model) {
		if(id == null || id.intValue() == 0)
			model.addAttribute("notes", new Notes());
		model.addAttribute("notes", notesService.findById(id));
	    /*return noteService.findById(noteId)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));*/
		return "notes";
	}
	
	@PutMapping("/notes/update/{id}")
	public String updateNotes(@PathVariable(value = "id") Long id,  @Valid @ModelAttribute Notes notes, Model model) {

	    Notes notes1 = notesService.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
	    notes1.setTitle(notes.getTitle());
	    notes1.setContent(notes.getContent());
	    model.addAttribute("notes",notesService.save(notes1));
	    return "notes";
	}
	
	@GetMapping("/notes/delete/{id}")
	public String deleteNotes(@PathVariable(value = "id") Long id, Model model) {
	    Notes notes = notesService.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
	    notesService.delete(notes);
	    return getAllNotes(model);
	}
}
