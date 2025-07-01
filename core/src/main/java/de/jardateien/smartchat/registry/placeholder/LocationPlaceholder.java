package de.jardateien.smartchat.registry.placeholder;

import de.jardateien.smartchat.SmartChatAddon;
import de.jardateien.smartchat.api.SmartChatPlaceholder;
import net.labymod.api.Laby;
import net.labymod.api.client.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LocationPlaceholder extends SmartChatPlaceholder {

  public LocationPlaceholder(SmartChatAddon addon) {
    super(addon, "location");
  }

  @Override
  public @NotNull String getVersion() {
    return "1.1.0";
  }

  @Override
  public @Nullable String parse() {
    Player player = Laby.labyAPI().minecraft().getClientPlayer();
    if(player == null) return null;
    return "[x: " + DECIMAL_FORMAT.format(player.position().getX()) +
        ", y: " + DECIMAL_FORMAT.format(player.position().getY()) +
        ", z: " + DECIMAL_FORMAT.format(player.position().getZ()) +
        ", Yaw: " + DECIMAL_FORMAT.format(player.getRotationYaw()) +
        ", Pitch: " + DECIMAL_FORMAT.format(player.getRotationPitch()) + "]";
  }
}
