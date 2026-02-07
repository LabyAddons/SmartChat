package de.jardateien.smartchat.config;

import de.jardateien.smartchat.utils.Sound;
import net.labymod.api.Laby;
import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.ButtonWidget.ButtonSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SliderWidget.SliderSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget.TextFieldSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.dropdown.DropdownWidget.DropdownSetting;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.annotation.IntroducedIn;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingRequires;
import net.labymod.api.configuration.settings.annotation.SettingSection;
import net.labymod.api.util.MethodOrder;

@ConfigName("settings")
public class SmartChatConfiguration extends AddonConfig {

  @IntroducedIn(namespace = "smartchat", value = "1.0.0")
  @SettingSection(value = "general", center = true)
  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @IntroducedIn(namespace = "smartchat", value = "1.2.0")
  @SettingSection(value = "antiCommandChoker", center = true)
  @SwitchSetting
  private final ConfigProperty<Boolean> enabledCommandChoker = new ConfigProperty<>(true);
  @IntroducedIn(namespace = "smartchat", value = "1.2.0")
  @SettingRequires("enabledCommandChoker")
  @SwitchSetting
  private final ConfigProperty<Boolean> commandSendInstantly = new ConfigProperty<>(true);

  @IntroducedIn(namespace = "smartchat", value = "1.1.0")
  @SettingSection(value = "timestamp", center = true)
  @SwitchSetting
  private final ConfigProperty<Boolean> enabledTimestamp = new ConfigProperty<>(true);
  @IntroducedIn(namespace = "smartchat", value = "1.1.0")
  @SettingRequires("enabledTimestamp")
  @TextFieldSetting
  private final ConfigProperty<String> timestampStyle = new ConfigProperty<>("&e{time} &8| &r");
  @IntroducedIn(namespace = "smartchat", value = "1.1.0")
  @SettingRequires("enabledTimestamp")
  @TextFieldSetting
  private final ConfigProperty<String> timestampFormat = new ConfigProperty<>("HH:mm:ss");

  @IntroducedIn(namespace = "smartchat", value = "1.1.5")
  @SettingSection(value = "copy", center = true)
  @SwitchSetting
  private final ConfigProperty<Boolean> enabledCopy = new ConfigProperty<>(true);
  @IntroducedIn(namespace = "smartchat", value = "1.1.5")
  @SettingRequires("enabledCopy")
  @TextFieldSetting
  private final ConfigProperty<String> copyFormat = new ConfigProperty<>(" &7[&a❑&7]");
  @IntroducedIn(namespace = "smartchat", value = "1.1.5")
  @SettingRequires("enabledCopy")
  @TextFieldSetting
  private final ConfigProperty<String> copyHover = new ConfigProperty<>("Click to copy.");

  @IntroducedIn(namespace = "smartchat", value = "1.0.5")
  @SettingSection(value = "chatNotification", center = true)
  @SwitchSetting
  private final ConfigProperty<Boolean> pingSound = new ConfigProperty<>(true);
  @IntroducedIn(namespace = "smartchat", value = "1.0.5")
  @SettingRequires("pingSound")
  @DropdownSetting
  private final ConfigProperty<Sound> type = new ConfigProperty<>(Sound.LABYMOD_MARKER_NOTIFY);
  @IntroducedIn(namespace = "smartchat", value = "1.0.5")
  @SettingRequires("pingSound")
  @SliderSetting(min = 0.1F, max = 2F, steps = 0.1F)
  private final ConfigProperty<Float> volume = new ConfigProperty<>(1F);
  @IntroducedIn(namespace = "smartchat", value = "1.0.5")
  @SettingRequires("pingSound")
  @SliderSetting(min = 0F, max = 2F, steps = 0.1F)
  private final ConfigProperty<Float> pitch = new ConfigProperty<>(1.2F);
  @IntroducedIn(namespace = "smartchat", value = "1.0.5")
  @SettingRequires("pingSound")
  @MethodOrder(after = "pitch")
  @ButtonSetting
  public void playSound() {
    if(!this.enabled.get() && !this.pingSound.get())
      return;

    Sound sound = this.type.get();
    Laby.labyAPI().minecraft().sounds()
        .playSound(ResourceLocation.create(
            sound.getNamespace(),
            sound.getPath()
        ), this.volume.get(), this.pitch.get());
  }

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public ConfigProperty<Boolean> command() {
    return this.enabledCommandChoker;
  }
  public ConfigProperty<Boolean> commandSendInstantly() {
    return this.commandSendInstantly;
  }

  public ConfigProperty<Boolean> timestamp() {
    return this.enabledTimestamp;
  }
  public ConfigProperty<String> timestampFormat() {
    return this.timestampFormat;
  }
  public ConfigProperty<String> timestampStyle() {
    return this.timestampStyle;
  }

  public ConfigProperty<Boolean> copy() {
    return this.enabledCopy;
  }
  public ConfigProperty<String> copyFormat() {
    return this.copyFormat;
  }
  public ConfigProperty<String> copyHover() {
    return this.copyHover;
  }

  public ConfigProperty<Boolean> enabledPing() {
    return this.pingSound;
  }
  public ConfigProperty<Sound> type() {
    return this.type;
  }
  public ConfigProperty<Float> volume() {
    return this.volume;
  }
  public ConfigProperty<Float> pitch() {
    return this.pitch;
  }
}
