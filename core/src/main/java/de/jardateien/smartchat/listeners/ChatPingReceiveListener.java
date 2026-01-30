package de.jardateien.smartchat.listeners;

import de.jardateien.smartchat.SmartChatAddon;
import de.jardateien.smartchat.config.SmartChatConfiguration;
import de.jardateien.smartchat.utils.Sound;
import net.labymod.api.Laby;
import net.labymod.api.LabyAPI;
import net.labymod.api.client.chat.ChatMessage;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatReceiveEvent;
import java.util.Objects;

public class ChatPingReceiveListener {

  private final SmartChatConfiguration configuration;

  public ChatPingReceiveListener(SmartChatAddon addon) {
    this.configuration = addon.configuration();
  }

  @Subscribe()
  public void onChatReceive(ChatReceiveEvent receiveEvent) {
    if(!this.configuration.enabled().get() || !this.configuration.enabledPing().get()) return;
    ChatMessage chatMessage = receiveEvent.chatMessage();

    LabyAPI labyAPI = Laby.labyAPI();
    if(Objects.equals(chatMessage.getSenderUniqueId(), labyAPI.getUniqueId())) return;

    String message = chatMessage.getFormattedText();
    if(!message.contains(labyAPI.getName())) return;

    Sound type = this.configuration.type().get();

    labyAPI.minecraft().sounds()
        .playSound(ResourceLocation.create(type.getNamespace(), type.getPath()),
            this.configuration.volume().get(), this.configuration.pitch().get());
  }

}
