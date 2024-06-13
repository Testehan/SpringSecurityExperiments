package com.testehan.springsecurity.chpt16.permissionsformethods.controller;

import com.testehan.springsecurity.chpt16.permissionsformethods.model.Document;
import com.testehan.springsecurity.chpt16.permissionsformethods.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentController {
    @Autowired
    private DocumentService documentService;
    @GetMapping("/documents/{code}")
    public Document getDetails(@PathVariable String code) {
        return documentService.getDocument(code);
    }
}
