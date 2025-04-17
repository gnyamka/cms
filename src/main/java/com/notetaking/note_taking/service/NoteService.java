package com.notetaking.note_taking.service;

import com.notetaking.note_taking.model.core.MyNote;
import com.notetaking.note_taking.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public List<MyNote> getNotes() {
        return noteRepository.findAll();
    }
    public List<MyNote> findByUserId(Long userId){
        return noteRepository.findByUserId(userId);
    }
    public List<MyNote> findByFolderId(Long folderId){
        return noteRepository.findByFolderId(folderId);
    }

    public MyNote save(MyNote note) {
        return noteRepository.save(note);
    }



}
