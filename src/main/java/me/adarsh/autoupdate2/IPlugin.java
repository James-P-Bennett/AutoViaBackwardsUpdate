package me.adarsh.autoupdate2;

import java.io.File;

public interface IPlugin {

    /**
     * Sends any additional output messages to console
     *
     * @param message The message to send to console
     */
    public void sendToConsole(String message);

    /**
     * Run an asynchronous task at a later point in time
     *
     * @param runnable The task to run
     * @param seconds The number of seconds into the future to run the task
     */
    public void runTaskLaterAsync(Runnable runnable, long seconds);

    /**
     * Get the Via Backwards jar file in the plugins directory
     * @return The File of the ViaBackwards.jar or null if it doesn't exist
     */
    public File getViaBackwardsJar();

    /**
     * Get the plugins directory for installing plugins in
     *
     * @return The File of the plugins directory
     */
    public File getPluginsDirectory();
}
