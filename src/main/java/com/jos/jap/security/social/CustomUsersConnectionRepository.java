package com.jos.jap.security.social;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
public class CustomUsersConnectionRepository implements UsersConnectionRepository {
    @Override
    public List<String> findUserIdsWithConnection(Connection<?> connection) {
        return Arrays.asList("admin");
    }

    @Override
    public Set<String> findUserIdsConnectedTo(String s, Set<String> set) {
        return null;
    }

    @Override
    public ConnectionRepository createConnectionRepository(String s) {
        return null;
    }
}
