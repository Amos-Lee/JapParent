package com.jos.jap.security.social;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;

import java.util.List;
import java.util.Set;

public class CustomUsersConnectionRepository implements UsersConnectionRepository {
    @Override
    public List<String> findUserIdsWithConnection(Connection<?> connection) {
        return null;
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
