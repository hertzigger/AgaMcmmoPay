package com.craftaga.agamcmmowage.concurrent.handlers.timer;

import com.craftaga.agabacbone.commands.SendMessageToPlayerCommand;
import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.commands.queue.SubQueue;
import com.craftaga.agabacbone.concurrent.PlayerQueueConstructor;
import com.craftaga.agamcmmowage.ExperienceApiWrapper;
import com.craftaga.agamcmmowage.commands.CalculatePotentialWageCommand;
import com.craftaga.agamcmmowage.commands.PayPlayerAmountUsingVaultCommand;
import com.craftaga.agamcmmowage.commands.PaymentLargerThanZeroDecisionCommand;
import com.craftaga.agamcmmowage.commands.PlayerAFKDecisionCommand;
import com.craftaga.agamcmmowage.commands.SendPayMessageToPlayerCommand;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;

/**
 * description
 *
 * @author Jonathan
 * @since 10/04/14
 */
public class McmmoPayTimer extends PlayerQueueConstructor {
    private double basePercentage;
    private ExperienceApiWrapper experienceApiWrapper;
    private RegisteredServiceProvider<Economy> registeredServiceProvider;

    public McmmoPayTimer(
            double basePercentage,
            ExperienceApiWrapper experienceApiWrapper,
            RegisteredServiceProvider<Economy> registeredServiceProvider,
            ClassPathXmlApplicationContext context
    ) {
        super(context);
        this.basePercentage = basePercentage;
        this.experienceApiWrapper = experienceApiWrapper;
        this.registeredServiceProvider = registeredServiceProvider;
    }

    @Override
    public CommandQueue getCommandQueue() {
        CommandQueue commandQueue = new CommandQueue(getUserSession().getSessionHandler().getPluginManager());

        CalculatePotentialWageCommand<Double> calculatePotentialWageCommand =
                new CalculatePotentialWageCommand<Double>(
                        commandQueue,
                        getUserSession(),
                        experienceApiWrapper,
                        basePercentage
                );
        SendPayMessageToPlayerCommand sendPayMessageToPlayerCommand =
                new SendPayMessageToPlayerCommand(
                        calculatePotentialWageCommand, getUserSession(),
                        new Double(basePercentage * 100).intValue(),
                        registeredServiceProvider
                );
        SendMessageToPlayerCommand payMsg = new SendMessageToPlayerCommand(
                commandQueue,
                sendPayMessageToPlayerCommand,
                getUserSession().getUser()
        );

        SendMessageToPlayerCommand zeroPowerLevelMsg = new SendMessageToPlayerCommand(
                commandQueue,
                this.getContext().getMessage("pluginTag", new String[]{}, Locale.ENGLISH) + " " +
                        this.getContext().getMessage(
                                "timer.mcmmopaytimer.nonpayment",
                                new String[]{getUserSession().getUser().getName()},
                                Locale.ENGLISH),
                getUserSession().getUser()
        );

        PayPlayerAmountUsingVaultCommand payPlayerAmountUsingVaultCommand =
                new PayPlayerAmountUsingVaultCommand(
                        commandQueue,
                        calculatePotentialWageCommand,
                        getUserSession(),
                        registeredServiceProvider
                );

        SubQueue payLargerThanZero = new SubQueue();
        payLargerThanZero.addCommand(payPlayerAmountUsingVaultCommand);
        payLargerThanZero.addCommand(payMsg);

        SubQueue payZeroOrLess = new SubQueue();
        payZeroOrLess.addCommand(zeroPowerLevelMsg);

        PaymentLargerThanZeroDecisionCommand paymentLargerThanZeroDecisionCommand =
                new PaymentLargerThanZeroDecisionCommand(
                        commandQueue,
                        calculatePotentialWageCommand,
                        payLargerThanZero,
                        payZeroOrLess
                );

        SubQueue notAfkQueue = new SubQueue();
        notAfkQueue.addCommand(paymentLargerThanZeroDecisionCommand);

        SubQueue afkQueue = new SubQueue();

        PlayerAFKDecisionCommand playerAFKDecisionCommand =
                new PlayerAFKDecisionCommand(commandQueue, getUserSession(), afkQueue, notAfkQueue);

        commandQueue.addCommand(calculatePotentialWageCommand);
        commandQueue.addCommand(playerAFKDecisionCommand);
        return commandQueue;
    }
}
