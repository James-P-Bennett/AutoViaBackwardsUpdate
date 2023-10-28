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
}
