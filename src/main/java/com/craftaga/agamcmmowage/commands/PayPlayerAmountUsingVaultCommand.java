package com.craftaga.agamcmmowage.commands;

import com.craftaga.agabacbone.commands.Command;
import com.craftaga.agabacbone.commands.IValueHolderCommand;
import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.session.IUserSession;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * description
 *
 * @author Jonathan
 * @since 10/04/14
 */
public class PayPlayerAmountUsingVaultCommand extends Command {

    private IValueHolderCommand<Double> amount;
    private Economy economy = null;
    private IUserSession userSession;
    private RegisteredServiceProvider<Economy> economyRegisteredServiceProvider;

    public PayPlayerAmountUsingVaultCommand(
            CommandQueue commandQueue,
            IValueHolderCommand<Double> amount,
            IUserSession userSession,
            RegisteredServiceProvider<Economy> economyRegisteredServiceProvider
    ) {
        super(commandQueue);
        this.amount = amount;
        this.userSession = userSession;
        this.economyRegisteredServiceProvider = economyRegisteredServiceProvider;
    }

    @Override
    public void execute() {
        if (economyRegisteredServiceProvider != null) {
            economy = economyRegisteredServiceProvider.getProvider();
            if (amount.getValue() > 0) {
                economy.depositPlayer(userSession.getUser().getName(), amount.getValue());
            }
        }
    }
}
