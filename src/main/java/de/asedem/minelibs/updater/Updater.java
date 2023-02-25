package de.asedem.minelibs.updater;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.CompletableFuture;

public class Updater {

    private String newVersion;

    private Updater() {
    }

    public CompletableFuture<UpdateResult> checkVersionFromSpigot(Plugin plugin, int resourceId) {
        return checkVersionFromSpigot(plugin, resourceId, 2000);
    }

    public CompletableFuture<UpdateResult> checkVersionFromSpigot(Plugin plugin, int resourceId, int timeout) {
        CompletableFuture<UpdateResult> completableFuture = new CompletableFuture<>();
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () ->
                completableFuture.complete(checkVersionFromSpigotSync(plugin, resourceId, timeout)));
        return completableFuture;
    }

    public UpdateResult checkVersionFromSpigotSync(Plugin plugin, int resourceId) {
        return checkVersionFromSpigotSync(plugin, resourceId, 2000);
    }

    public UpdateResult checkVersionFromSpigotSync(Plugin plugin, int resourceId, int timeout) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(
                    "https://api.spigotmc.org/legacy/update.php?resource=" + resourceId)
                    .openConnection();
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            this.newVersion = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
            connection.disconnect();
            return internalVersionCheck(plugin);
        } catch (IOException exception) {
            return UpdateResult.FAIL_SPIGOT;
        }
    }

    public void updateFromSpigot(Plugin plugin, int resourceId) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                updateFromSpigotSync(plugin, resourceId);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });
    }

    public void updateFromSpigotSync(Plugin plugin, int resourceId) throws IOException {
        update(new URL("https://api.spiget.org/v2/resources/" + resourceId + "/download"),
                new File(Bukkit.getServer().getUpdateFolderFile(), plugin.getDescription().getName() + ".jar"));
    }

    public void updateFromJenkins(Plugin plugin, String site, String projectName) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                updateFromJenkinsSync(plugin, site, projectName);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });
    }

    public void updateFromJenkinsSync(Plugin plugin, String site, String projectName) throws IOException {
        update(new URL("http://" + site + "/job/" + projectName + "/lastSuccessfulBuild/artifact/" + projectName
                        + "/target/" + projectName + ".jar"),
                new File(Bukkit.getServer().getUpdateFolderFile(), plugin.getDescription().getName() + ".jar"));
    }

    public void updateFromCustomSite(Plugin plugin, String site) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                updateFromCustomSiteSync(plugin, site);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });
    }

    public void updateFromCustomSiteSync(Plugin plugin, String site) throws IOException {
        update(new URL(site), new File(Bukkit.getServer().getUpdateFolderFile(), plugin.getDescription().getName()));
    }

    private void update(URL url, File file) throws IOException {
        file.getParentFile().mkdirs();
        file.createNewFile();
        ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        fileOutputStream.close();
        readableByteChannel.close();
    }

    private UpdateResult internalVersionCheck(Plugin plugin) {
        plugin.getDescription();
        if (newVersion == null) return UpdateResult.FAIL_NO_VERSION;
        return plugin.getDescription().getVersion().equalsIgnoreCase(newVersion) ? UpdateResult.NO_UPDATE : UpdateResult.UPDATE_AVAILABLE;
    }
}
