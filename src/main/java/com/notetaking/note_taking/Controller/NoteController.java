package com.notetaking.note_taking.Controller;

import com.notetaking.note_taking.model.core.*;
import com.notetaking.note_taking.service.FolderService;
import com.notetaking.note_taking.service.NoteService;
import com.notetaking.note_taking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;
    @Autowired
    private FolderService folderService;


    @GetMapping
    public List<MyNote> listAll() {
        return noteService.getNotes();
    }

    // 📋 LIST BY USER
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MyNote>> listByUser(@PathVariable Long userId) {

        List<MyNote> notes = noteService.findByUserId(userId);
        return ResponseEntity.ok(notes);
    }

    // 📋 LIST BY FOLDER
    @GetMapping("/folder/{folderId}")
    public ResponseEntity<List<MyNote>> listByFolder(@PathVariable Long folderId) {

        List<MyNote> notes = noteService.findByFolderId(folderId);
        return ResponseEntity.ok(notes);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createNote(@RequestBody CreateNoteRequest request) {
        Optional<User> userOpt = userService.findById(request.getUserId());

        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found.");
        }
        Optional<MyFolder> folderOpt = Optional.ofNullable(folderService.findByFolderId(request.getFolderId()));

        if (folderOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Folder not found.");
        }

        MyNote note = new MyNote();
        note.setUser(userOpt.get());
        note.setFolder(folderOpt.get());
        note.setContent(request.getContent());
        note.setFavorite(request.isFavorite());
        note.setTitle(request.getTitle());
        note.setUpdatedAt(new Date());
        note.setCreatedAt(new Date());

        noteService.save(note);
        return ResponseEntity.ok("Note created.");
    }
}
