package de.jardateien.smartchat.registry.placeholder;

import de.jardateien.smartchat.SmartChatAddon;
import de.jardateien.smartchat.api.SmartChatPlaceholder;
import net.labymod.api.Laby;
import net.labymod.api.client.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LocationYPlaceholder extends SmartChatPlaceholder {

  public LocationYPlaceholder(SmartChatAddon addon) {
    super(addon, "y");
  }

  @Override
  public @NotNull String getVersion() {
    return "1.0.0";
  }

  @Override
  public @Nullable String parse() {
    Player player = Laby.labyAPI().minecraft().getClientPlayer();
    if(player == null) return null;
    return DECIMAL_FORMAT.format(player.position().getY());
  }
}
