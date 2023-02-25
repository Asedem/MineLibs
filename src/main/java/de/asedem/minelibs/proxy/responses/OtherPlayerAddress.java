package de.asedem.minelibs.proxy.responses;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;

public record OtherPlayerAddress(
        String username,
        String address,
        int port
) {

    public static final Queue<CompletableFuture<OtherPlayerAddress>> REQUESTS = new LinkedList<>();
}
