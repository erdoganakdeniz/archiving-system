package com.archivingsystem.service;

import com.archivingsystem.entity.Customer;
import com.archivingsystem.entity.File;
import com.archivingsystem.repository.CustomerRepository;
import com.archivingsystem.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FileServiceImpl implements FileService{

    @Autowired
    private FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }

    @Override
    public File getFileById(Long id) {
        return fileRepository.getById(id);
    }

    @Override
    public File save(File file) {
        return fileRepository.save(file);
    }

    @Override
    public File updateFileById(Long id, File newFile) {
        Optional<File> file=fileRepository.findById(id);
        if (file.isPresent()) {
            File foundFile=file.get();
            foundFile.setFileName(newFile.getFileName());
            foundFile.setFileDescription(newFile.getFileDescription());
            foundFile.setCustomer(newFile.getCustomer());
            fileRepository.save(foundFile);
            return foundFile;
        }else{
            return null;
        }
    }

    @Override
    public void deleteFileById(Long fileId) {
        fileRepository.deleteById(fileId);

    }
    @Override
    public File createFile(File file, Customer customer) {
        File newFile=new File();
        newFile.setCustomer(customer);
        newFile.setFileName(file.getFileName());
        newFile.setFileDescription(file.getFileDescription());
        return newFile;
    }
}
