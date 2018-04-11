package com.mytest.billapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mytest.billapp.exceptions.ResourceNotFoundException;
import com.mytest.billapp.model.Notes;
import com.mytest.billapp.service.NotesService;

@Controller
public class NotesController {
	
	@Autowired
	NotesService notesService;
	
	@RequestMapping(value = "notesList", method = RequestMethod.POST)
	public String getAllNotes(Model model) {
		model.addAttribute("notesList", notesService.findAll());
		model.addAttribute("id","");
	    return "notesList";
	}
	
	@RequestMapping(value = "saveNotes", method = RequestMethod.POST)
	public String saveNotes(@ModelAttribute Notes notes, Model model) {
		try {
			model.addAttribute("notes", notesService.save(notes));
			model.addAttribute("message", "Succesfully Saved.");
		} catch (Exception e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return "notes";
		}
	    return "notes";
	}
	
	
	@RequestMapping(value = "notes", method = RequestMethod.POST)
	public String addNotes(@ModelAttribute("id") Long id,  Model model) {
		if(id == null || id.intValue() == 0)
			model.addAttribute("notes", new Notes());
		try {
			model.addAttribute("notes", notesService.findById(id));
			model.addAttribute("message", "");
		} catch (Exception e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return "notes";
		}
		return "notes";
	}
	
	
	@RequestMapping(value = "deleteNotes", method = RequestMethod.POST)
	public String deleteNotes(@ModelAttribute("id") Long id, Model model) {
	    try {
			Notes notes = notesService.findById(id)
			        .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
			notesService.delete(notes);
		} catch (ResourceNotFoundException e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return getAllNotes(model);
		}
	    return getAllNotes(model);
	}
}
