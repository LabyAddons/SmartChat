package de.jardateien.smartchat.listeners;

import de.jardateien.smartchat.SmartChatAddon;
import de.jardateien.smartchat.config.SmartChatConfiguration;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatReceiveEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampChatReceiveListener {

  private final SmartChatConfiguration configuration;
  private final String pattern;

  public TimestampChatReceiveListener(SmartChatAddon addon) {
    this.configuration = addon.configuration();
    this.pattern = "([dMyHhmsaSE]+|'.*?'|[^dMyHhmsaSE'])*";
  }

  @Subscribe
  public void onChatReceive(ChatReceiveEvent receiveEvent) {
    if(!this.configuration.enabled().get() || !this.configuration.timestamp().get()) return;

    String msg = receiveEvent.chatMessage().getPlainText();
    if (msg.trim().isEmpty())
      return;

    String timestampFormat = this.configuration.timestampFormat().get();
    if(!timestampFormat.matches(this.pattern))
      return;

    SimpleDateFormat dataFormat = new SimpleDateFormat(timestampFormat);

    String timestampStyle = this.configuration.timestampStyle().get();
    if(!timestampStyle.contains("{time}"))
      return;

    timestampStyle = timestampStyle.replace("{time}", dataFormat.format(new Date()));

    receiveEvent.setMessage(Component.text(
        Laby.references().componentMapper().translateColorCodes(timestampStyle))
        .append(receiveEvent.message()));
  }

}
