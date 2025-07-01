package de.jardateien.smartchat.utils;

public enum Sound {

  LABYMOD_MARKER_NOTIFY("labymod", "marker.marker_notify"),
  LABYMOD_MUSIC_POP("labymod", "misc.pop"),
  LABYMOD_LOOTBOX_COMMON("labymod", "lootbox.common"),
  LABYMOD_LOOTBOX_SPECIAL("labymod", "lootbox.special"),
  LABYMOD_BUTTON_ON("labymod", "ui.switch.on"),
  LABYMOD_BUTTON_OFF("labymod", "ui.switch.off"),

  MINECRAFT_CHICKEN_POP("minecraft", "entity.chicken.egg");

  final String namespace;
  final String path;

  Sound(String namespace, String path) {
    this.namespace = namespace;
    this.path = path;
  }

  public String getNamespace() {
    return this.namespace;
  }

  public String getPath() {
    return this.path;
  }
}
