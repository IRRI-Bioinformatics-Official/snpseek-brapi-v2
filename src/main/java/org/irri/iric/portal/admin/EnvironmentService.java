package org.irri.iric.portal.admin;

import org.springframework.stereotype.Component;

import io.github.cdimascio.dotenv.Dotenv;

@Component
public class EnvironmentService {

    private final Dotenv dotenv;

    public EnvironmentService() {
        this.dotenv = Dotenv.configure()
            .ignoreIfMalformed()
            .ignoreIfMissing()
            .load();
    }

    public String get(String key) {
        return dotenv.get(key);
    }
}