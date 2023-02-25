package de.asedem.minelibs.proxy.responses;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;

public record PlayerCount(
        String server,
        int playerCount
) {

    public static final Queue<CompletableFuture<PlayerCount>> REQUESTS = new LinkedList<>();
}
