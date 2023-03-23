package me.adarsh.autoupdate2;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AutoUpdate extends JavaPlugin implements IPlugin {

    @Override
    public void onEnable() {
        new ViaBackwardsAutoUpdate(this);
    }

    @Override
    public void broadcastMessage(String message) {
        getServer().broadcastMessage(message);
    }

    @Override
    public void runTaskLaterAsync(Runnable runnable, long seconds) {
        long ticks = seconds*20;
        getServer().getScheduler().runTaskLaterAsynchronously(this, runnable, ticks);
    }

    @Override
    public void restart() {
        try {
            // Use spigot's restart() method if it exists
            if (getServer().getClass().getMethod("spigot") != null) {
                getServer().spigot().restart();
                return;
            }
        } catch (NoSuchMethodException expected) {}

        // Otherwise assume the server knows how to restart itself
        getServer().shutdown();
    }

    @Override
    public File getViaBackwardsJar() {
        Plugin viaBackwards = getServer().getPluginManager().getPlugin("ViaBackwards");
        if (viaBackwards == null || !(viaBackwards instanceof JavaPlugin)) {
            return null;
        } else {
            // getFile() is only in JavaPlugins
            JavaPlugin jViaBackwards = (JavaPlugin) viaBackwards;
            try {
                // getFile() method is protected in bukkit, force access to it
                Method m = jViaBackwards.getClass().getSuperclass().getDeclaredMethod("getFile");
                m.setAccessible(true);
                return (File) m.invoke(jViaBackwards);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    @Override
    public File getPluginsDirectory() {
        // Assume the directory that this plugin is in is the plugins directory
        return getFile().getParentFile();
    }
}