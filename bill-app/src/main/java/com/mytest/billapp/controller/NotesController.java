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
@RequestMapping(value="admin/notes")
public class NotesController {
	
	@Autowired
	NotesService notesService;
	
	@RequestMapping(value = "notesList", method = RequestMethod.POST)
	public String getAllNotes(Model model) {
		model.addAttribute("notesList", notesService.findAll());
		model.addAttribute("notes", new Notes());
		model.addAttribute("selectedId","");
	    return "notes";
	}
	
	@RequestMapping(value = "saveNotes", method = RequestMethod.POST)
	public String saveNotes(@ModelAttribute Notes notes, Model model) {
		try {
			notesService.save(notes);
			model.addAttribute("notes", new Notes());
			model.addAttribute("notesList", notesService.findAll());
			model.addAttribute("message", "Succesfully Saved.");
			model.addAttribute("selectedId","");
		} catch (Exception e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return "notes";
		}
	    return "notes";
	}
	
	
	@RequestMapping(value = "notes", method = RequestMethod.POST)
	public String addNotes(@ModelAttribute("selectedId") Long selectedId,  Model model) {
		if(selectedId == null || selectedId.intValue() == 0)
			model.addAttribute("notes", new Notes());
		try {
			model.addAttribute("notes", notesService.findById(selectedId));
			model.addAttribute("notesList", notesService.findAll());
			model.addAttribute("selectedId","");
			model.addAttribute("message", "");
		} catch (Exception e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return "notes";
		}
		return "notes";
	}
	
	
	@RequestMapping(value = "deleteNotes", method = RequestMethod.POST)
	public String deleteNotes(@ModelAttribute("selectedId") Long selectedId, Model model) {
	    try {
			Notes notes = notesService.findById(selectedId);
			       // .orElseThrow(() -> new ResourceNotFoundException("Note", "selectedId", selectedId));
			notesService.delete(notes);
			model.addAttribute("notesList", notesService.findAll());
		} catch (ResourceNotFoundException e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return getAllNotes(model);
		}
	    return addNotes(0l, model);
	}
}
