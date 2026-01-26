package de.jardateien.smartchat.listeners;

import de.jardateien.smartchat.SmartChatAddon;
import de.jardateien.smartchat.config.SmartChatConfiguration;
import net.labymod.api.Laby;
import net.labymod.api.client.chat.ChatExecutor;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.event.ClickEvent;
import net.labymod.api.event.Priority;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatMessageSendEvent;

public class AntiCommandChokerListener {

  private final SmartChatConfiguration configuration;
  private static final String CHOKE_TRIGGER = "7";

  public AntiCommandChokerListener(SmartChatAddon addon) {
    this.configuration = addon.configuration();
  }

  @Subscribe(Priority.EARLY)
  public void onChatMessageSend(ChatMessageSendEvent event) {
    if (!this.configuration.enabled().get() || !this.configuration.command().get())
      return;

    String message = event.getOriginalMessage();
    if (!message.startsWith(CHOKE_TRIGGER) || message.length() == 1 || message.charAt(1) == ' ')
      return;

    String command = "/" + message.substring(1);
    if(this.configuration.commandSendInstantly().get()) {
      event.changeMessage(command, event.getHistoryText());
      return;
    }

    event.setCancelled(true);

    ChatExecutor chatExecutor = Laby.references().chatExecutor();
    chatExecutor.displayClientMessage(Component.text(message));
    chatExecutor.displayClientMessage(Component.translatable("smartchat.settings.commandSendInstantly.button.runCommand").clickEvent(ClickEvent.runCommand(command))
        .append(Component.translatable("smartchat.settings.commandSendInstantly.button.clipboard").clickEvent(ClickEvent.copyToClipboard(command))));
  }

}
