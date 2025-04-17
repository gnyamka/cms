package com.notetaking.note_taking.Controller;

import com.notetaking.note_taking.model.core.CreateFolderRequest;
import com.notetaking.note_taking.model.core.MyFolder;
import com.notetaking.note_taking.model.core.User;
import com.notetaking.note_taking.service.FolderService;
import com.notetaking.note_taking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/folder")
public class FolderController {

    private FolderService folderService;


    @Autowired
    private UserService userService;


    @GetMapping
    public List<MyFolder> listAll() {
        return folderService.getFolders();
    }

    // 📋 LIST BY USER
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MyFolder>> listByUser(@PathVariable Long userId) {

        List<MyFolder> folders = folderService.findByUserId(userId);
        return ResponseEntity.ok(folders);
    }
    @PostMapping(value = "/create")
    public ResponseEntity<?> createFolder(@RequestBody CreateFolderRequest request) {
        Optional<User> userOpt = userService.findById(request.getUserId());

        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found.");
        }

        MyFolder folder = new MyFolder();
        folder.setUser(userOpt.get());
        folder.setName(request.getName());
        folder.setFavorite(request.isFavorite());
        folder.setCreatedAt(new Date());

        folderService.save(folder);
        return ResponseEntity.ok("Folder created.");
    }
}
