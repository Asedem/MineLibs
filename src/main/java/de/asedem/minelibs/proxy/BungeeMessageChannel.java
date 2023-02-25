package de.asedem.minelibs.proxy;

import com.google.common.annotations.Beta;
import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import de.asedem.minelibs.proxy.responses.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class BungeeMessageChannel implements PluginMessageListener {

    protected final Plugin plugin;
    protected Player emptyPlayer;
    protected final String bungeeCord = "BungeeCord";

    public BungeeMessageChannel(@NotNull Plugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, this.bungeeCord);
        plugin.getServer().getMessenger().registerIncomingPluginChannel(plugin, this.bungeeCord, this);
        emptyPlayer = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
    }

    public void unregister() {
        this.plugin.getServer().getMessenger().unregisterOutgoingPluginChannel(this.plugin);
        this.plugin.getServer().getMessenger().unregisterIncomingPluginChannel(this.plugin);
    }

    /**
     * Connects a player to said subserver
     *
     * @param player The player to connect
     * @param server The subserver
     */
    @Beta
    public void connect(@NotNull Player player, @NotNull String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        player.sendPluginMessage(this.plugin, this.bungeeCord, out.toByteArray());
    }

    /**
     * Connect a named player to said subserver
     *
     * @param player The player to connect
     * @param server The subserver
     */
    @Beta
    public void connectOther(@NotNull Player player, @NotNull String server) {
        this.connectOther(player.getName(), server);
    }

    /**
     * Connect a named player to said subserver
     *
     * @param other The player to connect
     * @param server The subserver
     */
    @Beta
    public void connectOther(@NotNull String other, @NotNull String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ConnectOther");
        out.writeUTF(other);
        out.writeUTF(server);
        this.emptyPlayer.sendPluginMessage(this.plugin, this.bungeeCord, out.toByteArray());
    }

    /**
     * Get the (real) IP of a player.
     *
     * @param player The play where to get the ip from
     * @return The Players ip
     */
    @Beta
    public CompletableFuture<PlayerAddress> ip(@NotNull Player player) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("IP");
        CompletableFuture<PlayerAddress> completableFuture = new CompletableFuture<>();
        PlayerAddress.REQUESTS.add(completableFuture);
        player.sendPluginMessage(this.plugin, this.bungeeCord, out.toByteArray());
        return completableFuture;
    }

    /**
     * Get the (real) IP of another player
     *
     * @param player The play where to get the ip from
     * @return The Players ip
     */
    @Beta
    public CompletableFuture<OtherPlayerAddress> ipOther(@NotNull Player player) {
        return this.ipOther(player.getName());
    }

    /**
     * Get the (real) IP of another player
     *
     * @param other The play where to get the ip from
     * @return The Players ip
     */
    @Beta
    public CompletableFuture<OtherPlayerAddress> ipOther(@NotNull String other) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("IPOther");
        out.writeUTF(other);
        CompletableFuture<OtherPlayerAddress> completableFuture = new CompletableFuture<>();
        OtherPlayerAddress.REQUESTS.add(completableFuture);
        this.emptyPlayer.sendPluginMessage(this.plugin, this.bungeeCord, out.toByteArray());
        return completableFuture;
    }

    /**
     * Get the amount of players on ALL the servers
     *
     * @return The amount
     */
    @Beta
    public CompletableFuture<PlayerCount> playerCount() {
        return this.playerCount("ALL");
    }

    /**
     * Get the amount of players on a certain server
     *
     * @param server The server where to count the players
     * @return The amount
     */
    @Beta
    public CompletableFuture<PlayerCount> playerCount(@NotNull String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerCount");
        out.writeUTF(server);
        CompletableFuture<PlayerCount> completableFuture = new CompletableFuture<>();
        PlayerCount.REQUESTS.add(completableFuture);
        this.emptyPlayer.sendPluginMessage(this.plugin, this.bungeeCord, out.toByteArray());
        return completableFuture;
    }

    /**
     * Get a list of players connected on ALL the servers
     *
     * @return The list of the Player names
     */
    @Beta
    public CompletableFuture<PlayerList> playerList() {
        return this.playerList("ALL");
    }

    @Beta
    public CompletableFuture<PlayerList> playerList(@NotNull Player player) {
        return this.playerList(player, "ALL");
    }

    /**
     * Get a list of players connected on a certain server
     *
     * @return The list of the Player names
     */
    @Beta
    public CompletableFuture<PlayerList> playerList(@NotNull String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerList");
        out.writeUTF(server);
        CompletableFuture<PlayerList> completableFuture = new CompletableFuture<>();
        PlayerList.REQUESTS.add(completableFuture);
        this.emptyPlayer.sendPluginMessage(this.plugin, this.bungeeCord, out.toByteArray());
        return completableFuture;
    }

    @Beta
    public CompletableFuture<PlayerList> playerList(@NotNull Player player, @NotNull String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerList");
        out.writeUTF(server);
        CompletableFuture<PlayerList> completableFuture = new CompletableFuture<>();
        PlayerList.REQUESTS.add(completableFuture);
        player.sendPluginMessage(this.plugin, this.bungeeCord, out.toByteArray());
        return completableFuture;
    }

    /**
     * Get a list of server name strings, as defined in BungeeCord's config.yml
     *
     * @return The list of the defined servers
     */
    @Beta
    public CompletableFuture<GetServers> getServers() {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("GetServers");
        CompletableFuture<GetServers> completableFuture = new CompletableFuture<>();
        GetServers.REQUESTS.add(completableFuture);
        this.emptyPlayer.sendPluginMessage(this.plugin, this.bungeeCord, out.toByteArray());
        return completableFuture;
    }

    /**
     * Send a message (as in, a chat message) to all players
     *
     * @param message The message to send to all players
     */
    @Beta
    public void message(@NotNull String message) {
        this.message("ALL", message);
    }

    /**
     * Send a message (as in, a chat message) to the specified player
     *
     * @param player The name of the player to send the chat message
     * @param message The message to send to the player
     */
    @Beta
    public void message(@NotNull Player player, @NotNull String message) {
        this.message(player.getName(), message);
    }

    /**
     * Send a message (as in, a chat message) to the specified player
     *
     * @param player The name of the player to send the chat message
     * @param message The message to send to the player
     */
    @Beta
    public void message(@NotNull String player, @NotNull String message) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Message");
        out.writeUTF(player);
        out.writeUTF(message);
        this.emptyPlayer.sendPluginMessage(this.plugin, this.bungeeCord, out.toByteArray());
    }

    /**
     * Send a raw message (as in, a chat message) to all players
     *
     * @param message The message to send to all players
     */
    @Beta
    public void messageRaw(@NotNull String message) {
        this.messageRaw("ALL", message);
    }

    /**
     * Send a raw message (as in, a chat message) to the specified player
     *
     * @param player The name of the player to send the chat message
     * @param message The message to send to the player
     */
    @Beta
    public void messageRaw(@NotNull Player player, @NotNull String message) {
        this.messageRaw(player.getName(), message);
    }

    /**
     * Send a raw message (as in, a chat message) to the specified player
     *
     * @param player The name of the player to send the chat message
     * @param message The message to send to the player
     */
    @Beta
    public void messageRaw(@NotNull String player, @NotNull String message) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("MessageRaw");
        out.writeUTF(player);
        out.writeUTF(message);
        this.emptyPlayer.sendPluginMessage(this.plugin, this.bungeeCord, out.toByteArray());
    }

    /**
     * Get this server's name, as defined in BungeeCord's config.yml
     *
     * @return The Server name from the config
     */
    @Beta
    public CompletableFuture<GetServer> getServer() {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("GetServers");
        CompletableFuture<GetServer> completableFuture = new CompletableFuture<>();
        GetServer.REQUESTS.add(completableFuture);
        this.emptyPlayer.sendPluginMessage(this.plugin, this.bungeeCord, out.toByteArray());
        return completableFuture;
    }

    @Beta
    @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, byte @NotNull [] message) {
        if (!channel.equals("BungeeCord")) return;
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subChannel = in.readUTF();
        switch (subChannel) {
            case "IP" -> {
                if (PlayerAddress.REQUESTS.isEmpty()) return;
                Objects.requireNonNull(PlayerAddress.REQUESTS.poll())
                        .complete(new PlayerAddress(in.readUTF(), in.readInt()));
            }
            case "IPOther" -> {
                if (OtherPlayerAddress.REQUESTS.isEmpty()) return;
                Objects.requireNonNull(OtherPlayerAddress.REQUESTS.poll())
                        .complete(new OtherPlayerAddress(in.readUTF(), in.readUTF(), in.readInt()));
            }
            case "PlayerCount" -> {
                if (PlayerCount.REQUESTS.isEmpty()) return;
                Objects.requireNonNull(PlayerCount.REQUESTS.poll())
                        .complete(new PlayerCount(in.readUTF(), in.readInt()));
            }
            case "PlayerList" -> {
                if (PlayerList.REQUESTS.isEmpty()) return;
                Objects.requireNonNull(PlayerList.REQUESTS.poll())
                        .complete(new PlayerList(in.readUTF(), in.readUTF().split(", ")));
            }
            case "GetServers" -> {
                if (GetServers.REQUESTS.isEmpty()) return;
                Objects.requireNonNull(GetServers.REQUESTS.poll())
                        .complete(new GetServers(in.readUTF().split(", ")));
            }
            case "GetServer" -> {
                if (GetServer.REQUESTS.isEmpty()) return;
                Objects.requireNonNull(GetServer.REQUESTS.poll())
                        .complete(new GetServer(in.readUTF()));
            }
        }
    }
}
