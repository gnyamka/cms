package com.notetaking.note_taking.repository;

import com.notetaking.note_taking.model.core.MyFolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends JpaRepository<MyFolder, Integer> {
    List<MyFolder> findByUserId(Long userId);
}
