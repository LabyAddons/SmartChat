package de.jardateien.smartchat.utils;

public enum Sound {

  LABYMOD_MARKER_NOTIFY("labymod", "marker.marker_notify", 0),
  LABYMOD_MUSIC_POP("labymod", "misc.pop", 0),
  LABYMOD_LOOTBOX_COMMON("labymod", "lootbox.common", 0),
  LABYMOD_LOOTBOX_SPECIAL("labymod", "lootbox.special", 0),
  LABYMOD_BUTTON_ON("labymod", "ui.switch.on", 0),
  LABYMOD_BUTTON_OFF("labymod", "ui.switch.off", 0),

  LABYMOD_HUDWIDGET_ALIGN("labymod", "hudwidget.align", 0),
  LABYMOD_HUDWIDGET_ATTACH("labymod", "hudwidget.attach", 0),
  LABYMOD_HUDWIDGET_TRASH("labymod", "hudwidget.trash", 0),
  LABYMOD_SERVER_MOVE("labymod", "ui.server.move", 0),

  MINECRAFT_CHICKEN_POP("minecraft", "entity.chicken.egg", 0);

  final String namespace;
  final String path;
  final int protocol;

  Sound(final String namespace, final String path, final int protocol) {
    this.namespace = namespace;
    this.path = path;
    this.protocol = protocol;
  }

  public String getNamespace() {
    return this.namespace;
  }
  public String getPath() {
    return this.path;
  }
}
