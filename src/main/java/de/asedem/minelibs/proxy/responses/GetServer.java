package de.asedem.minelibs.proxy.responses;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;

public record GetServer(
        String serverName
) {

    public static final Queue<CompletableFuture<GetServer>> REQUESTS = new LinkedList<>();
}
