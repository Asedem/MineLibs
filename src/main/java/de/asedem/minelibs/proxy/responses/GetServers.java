package de.asedem.minelibs.proxy.responses;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;

public record GetServers(
        String[] serverList
) {

    public static final Queue<CompletableFuture<GetServers>> REQUESTS = new LinkedList<>();
}
