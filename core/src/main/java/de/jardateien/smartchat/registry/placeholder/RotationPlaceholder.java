package de.jardateien.smartchat.registry.placeholder;

import de.jardateien.smartchat.api.SmartChatPlaceholder;
import net.labymod.api.Laby;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RotationPlaceholder extends SmartChatPlaceholder {

  public RotationPlaceholder(@NotNull LabyAddon<?> addon) {
    super(addon, "rotation");
  }

  @Override
  public @NotNull String getVersion() {
    return "1.1.0";
  }

  @Override
  public @Nullable String parse() {
    Player player = Laby.labyAPI().minecraft().getClientPlayer();
    if(player == null) return null;
    return "[Yaw: " + DECIMAL_FORMAT.format(player.getRotationYaw()) + ", Pitch: " + DECIMAL_FORMAT.format(player.getRotationPitch()) + "]";
  }
}
