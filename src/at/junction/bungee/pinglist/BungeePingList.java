package at.junction.bungee.pinglist;

import net.md_5.bungee.api.plugin.Plugin;

//Hacked together by zifnab, please ignore the shitty code
public class BungeePingList extends Plugin {
    @Override
    public void onEnable() {
            getProxy().getPluginManager().registerListener(this, new BungeePingListEvent(this));
            super.onEnable();
	}
	@Override
	public void onDisable() {
            super.onDisable();
	}
}
