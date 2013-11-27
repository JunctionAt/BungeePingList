package at.junction.bungee.pinglist;

import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.ServerPing.PlayerInfo;



/**
 *
 * @author zifnab
 */
public class BungeePingListEvent implements Listener{
    BungeePingList plugin;
    public BungeePingListEvent(BungeePingList plugin){
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onServerPing(ProxyPingEvent ppe){
        //Get the name of the host they connected with.
        String connectedDomain = ppe.getConnection().getVirtualHost().getHostName();
        //Get the actual server name (ie, chaos.junction.at)
        String serverName = connectedDomain.split("\\.")[0];
        if (serverName.equals("x")){
            //Chaos!
            createPing(ppe, "chaos");
        } else {
            //Lobby, display server counts
            PlayerInfo[] info = new PlayerInfo[2];
            int chaosCurrent = plugin.getProxy().getServerInfo("chaos").getPlayers().size();
            int chaosMax = 50;
            String chaos = "Chaos: " + chaosCurrent + "/" + chaosMax;
            info[0] = new PlayerInfo("Junction Minecraft Servers", "");
            info[1] = new PlayerInfo(chaos, "");
            ppe.getResponse().getPlayers().setSample(info);
        }
        
        
        
//        ArrayList<String> serverList = new ArrayList<String>();
//        for (ServerInfo s : plugin.getProxy().getServers().values()){
//            String server = s.getName();
//            serverList.add(server);
//            
//            Collection<ProxiedPlayer> players = s.getPlayers();
//        }
        
        
        
        
    }
    void createPing(ProxyPingEvent ppe, String serverName){
        ServerInfo server = plugin.getProxy().getServerInfo(serverName);
        int playerCount = server.getPlayers().size();
        
        if (playerCount == 0){
            PlayerInfo[] info = new PlayerInfo[1];
            info[0] = new PlayerInfo("No players online :(", "");
            ppe.getResponse().getPlayers().setSample(info);
        } else {
            ProxiedPlayer[] p = new ProxiedPlayer[playerCount];
            server.getPlayers().toArray(p);
            PlayerInfo[] info = new PlayerInfo[playerCount];
            for (int i=0; i<playerCount; i++){
                info[i] = new PlayerInfo(p[i].getName(), "");
            }
            ppe.getResponse().getPlayers().setSample(info);
        }
        
    }
}
