package com.notetaking.note_taking.service;

import com.notetaking.note_taking.model.core.MyFolder;
import com.notetaking.note_taking.model.core.User;
import com.notetaking.note_taking.repository.FolderRepository;
import com.notetaking.note_taking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderService {

    @Autowired
    private FolderRepository folderRepository;

    public List<MyFolder> getFolders() {
        return folderRepository.findAll();
    }
    public List<MyFolder> findByUserId(Long userId){
        return folderRepository.findByUserId(userId);
    }

    public MyFolder findByFolderId(Long folderId){
        return folderRepository.findById(Math.toIntExact(folderId)).orElse(null);
    }

    public MyFolder save(MyFolder folder) {
        return folderRepository.save(folder);
    }



}
