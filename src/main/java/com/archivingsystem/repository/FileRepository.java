package com.archivingsystem.repository;

import com.archivingsystem.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface FileRepository extends JpaRepository<File,Long> {

}
