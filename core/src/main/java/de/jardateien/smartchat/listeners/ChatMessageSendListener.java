package de.jardateien.smartchat.listeners;

import de.jardateien.smartchat.SmartChatAddon;
import de.jardateien.smartchat.config.SmartChatConfiguration;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatMessageSendEvent;

public class ChatMessageSendListener {
  private final SmartChatConfiguration configuration;

  public ChatMessageSendListener(SmartChatAddon addon) {
    this.configuration = addon.configuration();
  }

  @Subscribe(125)
  public void onChatReceive(ChatMessageSendEvent receiveEvent) {
    if (!this.configuration.enabled().get() || receiveEvent.isCancelled()) return;

    receiveEvent.changeMessage(
        SmartChatAddon.placeholderRegistry().replacePlaceholders(receiveEvent.getMessage()),
        receiveEvent.getHistoryText()
    );
  }


}
