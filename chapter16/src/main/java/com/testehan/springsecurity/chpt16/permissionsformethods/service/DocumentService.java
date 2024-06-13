package com.testehan.springsecurity.chpt16.permissionsformethods.service;

import com.testehan.springsecurity.chpt16.permissionsformethods.model.Document;
import com.testehan.springsecurity.chpt16.permissionsformethods.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    @PostAuthorize("hasPermission(returnObject, 'ROLE_admin')")
    public Document getDocument(String code) {
        return documentRepository.findDocument(code);
    }
}
