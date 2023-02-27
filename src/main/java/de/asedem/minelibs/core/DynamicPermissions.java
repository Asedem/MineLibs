package de.asedem.minelibs.core;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DynamicPermissions {

    private DynamicPermissions() {
    }

    /**
     * Gives you a list of permissions that the player has, which starts with a specific word
     *
     * @param player   The player who should be checked if he has the permissions
     * @param argument The permission argument (word in front of the permission)
     * @return A List of the available permissions
     */
    public static List<String> getByFirstArgument(@NotNull Player player, @NotNull String argument) {
        return player.getEffectivePermissions()
                .stream()
                .map(PermissionAttachmentInfo::getPermission)
                .filter(permission -> permission.startsWith(argument))
                .map(permission -> permission.substring(permission.lastIndexOf('.')).replace(".", ""))
                .toList();
    }
}
