package de.jardateien.smartchat.listeners;

import de.jardateien.smartchat.SmartChatAddon;
import de.jardateien.smartchat.config.SmartChatConfiguration;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.event.ClickEvent;
import net.labymod.api.client.component.event.HoverEvent;
import net.labymod.api.client.render.font.ComponentMapper;
import net.labymod.api.event.Priority;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatReceiveEvent;

public class CopyChatReceiveListener {

  private final SmartChatConfiguration configuration;

  public CopyChatReceiveListener(SmartChatAddon addon) {
    this.configuration = addon.configuration();
  }

  @Subscribe(Priority.EARLY)
  public void onChatReceive(ChatReceiveEvent receiveEvent) {
    if(!this.configuration.enabled().get() || !this.configuration.copy().get()) return;

    String msg = receiveEvent.chatMessage().getPlainText();
    if (msg.trim().isEmpty())
      return;

    ComponentMapper mapper = Laby.references().componentMapper();

    receiveEvent.setMessage(receiveEvent.message()
        .append(
            Component.text(mapper.translateColorCodes(this.configuration.copyFormat().get()))
                .clickEvent(ClickEvent.copyToClipboard(msg))
                .hoverEvent(HoverEvent.showText(Component.text(mapper.translateColorCodes(this.configuration.copyHover().get()))))
        ));
  }

}
