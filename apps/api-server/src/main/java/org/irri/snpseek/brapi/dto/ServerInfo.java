package org.irri.snpseek.brapi.dto;

import java.util.List;

/**
 * BrAPI v2.1 ServerInfo result object returned by {@code GET /brapi/v2/serverinfo}.
 */
public record ServerInfo(
        String           contactEmail,
        String           documentationURL,
        String           location,
        String           organizationName,
        String           organizationURL,
        String           serverDescription,
        String           serverName,
        List<ServiceCall> calls
) {
    /**
     * A single supported BrAPI service call.
     *
     * @param service  endpoint path relative to {@code /brapi/v2/} (e.g. {@code "search/variants"})
     * @param methods  HTTP methods supported (e.g. {@code ["GET", "POST"]})
     * @param versions BrAPI versions supported (e.g. {@code ["2.1"]})
     */
    public record ServiceCall(String service, List<String> methods, List<String> versions) {}
}
