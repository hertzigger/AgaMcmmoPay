package com.craftaga.agamcmmowage.commands;

import com.craftaga.agabacbone.commands.IValueHolderCommand;
import com.craftaga.agabacbone.commands.Messenger;
import com.craftaga.agabacbone.session.IUserSession;
import com.craftaga.agamcmmowage.ExperienceApiWrapper;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;

/**
 * description
 *
 * @author Jonathan
 * @since 11/04/14
 */
public class SendPayMessageToPlayerCommand implements Messenger {
    private IValueHolderCommand<Double> valueHolderCommand;
    private ClassPathXmlApplicationContext stringContext;
    private IUserSession userSession;
    private Integer percentage;
    private Economy economy;

    public SendPayMessageToPlayerCommand(
            IValueHolderCommand<Double> valueHolderCommand,
            IUserSession userSession,
            Integer percentage,
            RegisteredServiceProvider<Economy> economyRegisteredServiceProvider
    ) {
        ClassLoader cl = this.getClass().getClassLoader();
        stringContext = new ClassPathXmlApplicationContext(new String[]{"i18n/messageContext.xml"}, false);
        stringContext.setClassLoader(cl);
        stringContext.refresh();
        this.valueHolderCommand = valueHolderCommand;
        this.percentage = percentage;
        this.userSession = userSession;
        this.economy = economyRegisteredServiceProvider.getProvider();
    }

    @Override
    public StringBuilder getMessage() {

        String econName = "";
        if (valueHolderCommand.getValue() == 1) {
            econName = economy.currencyNamePlural();
        } else {
            econName = economy.currencyNameSingular();
        }
        return new StringBuilder(stringContext.getMessage("pluginTag", new String[] {}, Locale.ENGLISH) + " " +
            stringContext.getMessage(
                "timer.mcmmopaytimer.payment",
                new String[] {
                    userSession.getUser().getName(),
                    valueHolderCommand.getValue().toString() + " " + econName,
                    percentage.toString()
                },
                Locale.ENGLISH
            )
        );
    }
}
