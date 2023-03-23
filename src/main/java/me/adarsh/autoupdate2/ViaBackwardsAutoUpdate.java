package me.adarsh.autoupdate2;

/**
 * The entrance point of the ViaBackwardsAutoUpdate program
 */
public class ViaBackwardsAutoUpdate {

    private IPlugin plugin;

    public ViaBackwardsAutoUpdate(IPlugin plugin) {
        this.plugin = plugin;

        // Check for an update on startup,
        new UpdateChecker(this).run();
    }

    /**
     * Get the plugin
     * @return The IPlugin for this instance
     */
    public IPlugin getPlugin() {
        return plugin;
    }

    /**
     * Restarts the server after a 5 minute countdown
     */
    public void startRestartCountdown() {
        // 0 seconds from now
        plugin.runTaskLaterAsync(() -> {
            plugin.broadcastMessage("[ViaBackwardsAutoUpdate] Server restart in 5 minutes");
        }, 0);

        // 3 minutes from now
        plugin.runTaskLaterAsync(() -> {
            plugin.broadcastMessage("[ViaBackwardsAutoUpdate] Server restart in 2 minutes");
        }, 3*60);

        // 4 minutes from now
        plugin.runTaskLaterAsync(() -> {
            plugin.broadcastMessage("[ViaBackwardsAutoUpdate] Server restart in 1 minute");
        }, 4*60);

        // 4 minutes and a half from now
        plugin.runTaskLaterAsync(() -> {
            plugin.broadcastMessage("[ViaBackwardsAutoUpdate] Server restart in 30 seconds");
        }, 4*60 + 30);

        // 5 minutes from now
        plugin.runTaskLaterAsync(() -> {
            plugin.restart();
        }, 5*60);
    }
}