package com.jos.jap.core.security.permission;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;

import java.util.HashSet;
import java.util.Set;

@Component
public class PublicPermissionOperationPlugin implements OperationBuilderPlugin {
    private final Set<PublicPermission> publicPermissions = new HashSet();
    @Value("${server.servlet.context-path:}")
    private String contextPath;

    public PublicPermissionOperationPlugin() {
    }

    @Override
    public void apply(OperationContext context) {
//        Permission permission = (Permission)context.findAnnotation(Permission.class).orNull();
//        if (permission != null && (permission.permissionPublic() || permission.permissionSign())) {
//            this.publicPermissions.add(new PublicPermission(this.contextPath + context.requestMappingPattern(), context.httpMethod()));
//        }

    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return true;
    }

    public Set<PublicPermission> getPublicPaths() {
        return this.publicPermissions;
    }
}
