package com.archivingsystem.controller;


import com.archivingsystem.entity.Customer;
import com.archivingsystem.entity.File;
import com.archivingsystem.http.header.HeaderGenerator;
import com.archivingsystem.service.CustomerService;
import com.archivingsystem.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private HeaderGenerator headerGenerator;

    @GetMapping
    public ResponseEntity<List<File>> getAllFiles(){
        List<File> files=fileService.getAllFiles();
        if (!files.isEmpty()) {
            return new ResponseEntity<List<File>>(
                    files,
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<List<File>>(
                headerGenerator.getHeadersForError(),
                HttpStatus.NOT_FOUND
        );

    }
    @GetMapping(value = "/{fileId}")
    public ResponseEntity<File> getFileById(@PathVariable("fileId") Long id){
        File file=fileService.getFileById(id);
        if (file!=null) {
            return new ResponseEntity<File>(
                    file,
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<File>(
                headerGenerator.getHeadersForError(),
                HttpStatus.NOT_FOUND
        );
    }
    @PostMapping(value = "/{customerId}")
    public ResponseEntity<File> addFile(@RequestBody File file,@PathVariable("customerId") Long customerId){
        Customer customer=customerService.getCustomerById(customerId);
        if(file != null && customer!=null){
            File newFile=fileService.createFile(file,customer);
            fileService.save(newFile);
            return new ResponseEntity<File>(
                    newFile,
                    HttpStatus.CREATED);
        }
        return new ResponseEntity<File>(HttpStatus.BAD_REQUEST);

    }



    @PutMapping(value = "/{fileId}")
    public ResponseEntity<File> updateFileById(@PathVariable("fileId") Long id,@RequestBody File file){
        File newFile=fileService.updateFileById(id,file);
        if (newFile!=null) {
            return new ResponseEntity<File>(
                    newFile,
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<File>(
                headerGenerator.getHeadersForError(),
                HttpStatus.NOT_FOUND
        );
    }
    @DeleteMapping(value = "/{fileId}")
    public void deleteFileById(@PathVariable("fileId") Long id){
        fileService.deleteFileById(id);
    }


}
