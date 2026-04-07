package org.irri.snpseek.brapi.controller;

import org.irri.snpseek.brapi.dto.BrapiResponse;
import org.irri.snpseek.brapi.dto.ServerInfo;
import org.irri.snpseek.brapi.dto.ServerInfo.ServiceCall;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Implements {@code GET /brapi/v2/serverinfo} — publicly accessible,
 * no authentication required.
 */
@RestController
@RequestMapping("/brapi/v2/serverinfo")
public class ServerInfoController {

    private static final BrapiResponse<ServerInfo> RESPONSE = buildResponse();

    @GetMapping
    public BrapiResponse<ServerInfo> serverInfo() {
        return RESPONSE;
    }

    private static BrapiResponse<ServerInfo> buildResponse() {
        List<ServiceCall> calls = List.of(
                new ServiceCall("serverinfo",      List.of("GET"),         List.of("2.1")),
                new ServiceCall("search/variants", List.of("POST", "GET"), List.of("2.1"))
        );

        ServerInfo info = new ServerInfo(
                "l.h.barboza@cgiar.org",
                "https://snp-seek.irri.org",
                "Los Baños, Philippines",
                "International Rice Research Institute (IRRI)",
                "https://www.irri.org",
                "SNP-Seek BrAPI v2.1 service for rice genomic variant data",
                "SNP-Seek BrAPI Server",
                calls
        );

        return BrapiResponse.of(info);
    }
}
