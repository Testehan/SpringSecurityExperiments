package com.testehan.springsecurity.chpt16.permissionsformethods.security;

import com.testehan.springsecurity.chpt16.permissionsformethods.model.Document;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class DocumentsPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        Document document = (Document) targetDomainObject;
        String p = (String) permission;

        boolean isAdmin =
                authentication.getAuthorities()
                        .stream()
                        .anyMatch(a -> a.getAuthority().equals(p));

        return isAdmin || document.getOwner().equals(authentication.getName());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;   // we don't implement this becaus we don't use it
    }
}
