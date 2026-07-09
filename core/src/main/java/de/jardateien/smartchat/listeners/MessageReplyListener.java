package de.jardateien.smartchat.listeners;

import de.jardateien.smartchat.SmartChatAddon;
import de.jardateien.smartchat.config.SmartChatConfiguration;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.event.ClickEvent;
import net.labymod.api.client.render.font.ComponentMapper;
import net.labymod.api.event.Priority;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatReceiveEvent;
import net.labymod.api.mojang.GameProfile;

public class MessageReplyListener {

  private final SmartChatConfiguration configuration;
  private final ComponentMapper mapper;

  public MessageReplyListener(SmartChatAddon addon) {
    this.configuration = addon.configuration();
    this.mapper = Laby.references().componentMapper();
  }

  @Subscribe(Priority.EARLY)
  public void onChatReceive(ChatReceiveEvent receiveEvent) {
    if(!this.configuration.enabled().get() || !this.configuration.reply().get()) return;

    String msg = receiveEvent.chatMessage().getPlainText();
    if (msg.trim().isEmpty())
      return;

    GameProfile profile = receiveEvent.chatMessage().getSenderProfile();
    if(profile == null) return;

    String sender = profile.getUsername();
    if(sender.equalsIgnoreCase(Laby.labyAPI().getName()))
      return;

    receiveEvent.setMessage(receiveEvent.message().append(
        Component.text(this.mapper.translateColorCodes(this.configuration.replyFormat().get()))
            .clickEvent(ClickEvent.suggestCommand("@"+sender + " "))
        ));
  }

}
