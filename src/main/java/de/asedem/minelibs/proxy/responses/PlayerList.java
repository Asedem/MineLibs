package de.asedem.minelibs.proxy.responses;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;

public record PlayerList(
        String server,
        String[] playerList
) {

    public static final Queue<CompletableFuture<PlayerList>> REQUESTS = new LinkedList<>();
}
