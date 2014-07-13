package com.craftaga.agamcmmowage;

import com.craftaga.agabacbone.concurrent.IPluginManager;
import com.craftaga.agabacbone.concurrent.PluginManager;
import com.craftaga.agabacbone.session.ScheduledTimerHandler;
import com.craftaga.agamcmmowage.concurrent.handlers.timer.AfkCheck;
import com.craftaga.agamcmmowage.concurrent.handlers.timer.McmmoPayTimer;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * description
 *
 * @author Jonathan
 * @since 09/04/14
 */
public class AgaMcmmoWage extends JavaPlugin {
    private ExperienceApiWrapper experienceApiWrapper;
    private RegisteredServiceProvider<Economy> registeredServiceProvider;
    private ClassPathXmlApplicationContext stringContext;

    public AgaMcmmoWage()
    {

    }
    @Override
    public void onEnable() {
        super.onEnable();
        experienceApiWrapper = new ExperienceApiWrapper();
        registeredServiceProvider = getServer().getServicesManager().getRegistration(Economy.class);
        IPluginManager pluginManager = PluginManager.getInstance();
        ClassLoader cl = this.getClass().getClassLoader();
        stringContext = new ClassPathXmlApplicationContext(new String[]{"i18n/messageContext.xml"}, false);
        stringContext.setClassLoader(cl);
        stringContext.refresh();
        pluginManager.getSessionHandler().scheduleTimerHandlerAtFixedRate(
                new ScheduledTimerHandler(new McmmoPayTimer(0.50, experienceApiWrapper, registeredServiceProvider, stringContext), 300000)
        );
        pluginManager.getSessionHandler().scheduleTimerHandlerAtFixedRate(
                new ScheduledTimerHandler(new AfkCheck(10000, 600000, stringContext), 10000)
        );
    }

    public ExperienceApiWrapper getExperienceApiWrapper() {
        return experienceApiWrapper;
    }

    public void setExperienceApiWrapper(ExperienceApiWrapper experienceApiWrapper) {
        this.experienceApiWrapper = experienceApiWrapper;
    }

    public RegisteredServiceProvider<Economy> getRegisteredServiceProvider() {
        return registeredServiceProvider;
    }

    public void setRegisteredServiceProvider(RegisteredServiceProvider<Economy> registeredServiceProvider) {
        this.registeredServiceProvider = registeredServiceProvider;
    }
}
