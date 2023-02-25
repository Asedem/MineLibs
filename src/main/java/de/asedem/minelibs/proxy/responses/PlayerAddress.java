package de.asedem.minelibs.proxy.responses;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;

public record PlayerAddress(
        String ip,
        int port
) {

    public static final Queue<CompletableFuture<PlayerAddress>> REQUESTS = new LinkedList<>();
}
