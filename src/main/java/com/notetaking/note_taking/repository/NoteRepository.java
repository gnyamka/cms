package com.notetaking.note_taking.repository;

import com.notetaking.note_taking.model.core.MyFolder;
import com.notetaking.note_taking.model.core.MyNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<MyNote, Integer> {
    List<MyNote> findByUserId(Long userId);
    List<MyNote> findByFolderId(Long folderId);
}
