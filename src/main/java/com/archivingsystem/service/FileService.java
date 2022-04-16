package com.archivingsystem.service;

import com.archivingsystem.entity.Customer;
import com.archivingsystem.entity.File;

import java.util.List;

public interface FileService {
    List<File> getAllFiles();
    File getFileById(Long id);
    File save(File file);
    File createFile(File file, Customer customer);
    File updateFileById(Long id, File file);
    void deleteFileById(Long fileId);

}
