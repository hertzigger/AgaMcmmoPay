package com.craftaga.agamcmmowage.commands;


import com.craftaga.agabacbone.commands.Command;
import com.craftaga.agabacbone.commands.IValueHolderCommand;
import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.session.IUserSession;
import com.craftaga.agamcmmowage.ExperienceApiWrapper;
import com.gmail.nossr50.api.ExperienceAPI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * description
 *
 * @author Jonathan
 * @since 11/04/14
 */
public class CalculatePotentialWageCommand<D> extends Command implements IValueHolderCommand<Double>{

    private ExperienceApiWrapper experienceAPI;
    private IUserSession userSession;
    private Double percentage;

    private double wage;

    public CalculatePotentialWageCommand(
            CommandQueue commandQueue,
            IUserSession userSession,
            ExperienceApiWrapper experienceAPI,
            Double percentage
    ) {
        super(commandQueue);
        this.userSession = userSession;
        this.experienceAPI = experienceAPI;
        this.percentage = percentage;
    }

    @Override
    public void execute() {
        if (userSession.getUser() instanceof Player) {
            try {
                wage = ExperienceAPI.getPowerLevel((Player)userSession.getUser()) * percentage;
            } catch(IndexOutOfBoundsException e) {
                //cancel exception as there is no other way to check whether a player is loaded in mcmmo
            }
        }
    }

    @Override
    public void setValue(Double wage) {
        this.wage = wage;
    }

    @Override
    public Double getValue() {
        return wage;
    }
}
