package com.craftaga.agamcmmowage.concurrent.handlers.timer;

import com.craftaga.agabacbone.commands.MessageConsoleCommand;
import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.concurrent.PlayerQueueConstructor;
import com.craftaga.agamcmmowage.commands.UpdateAfkStatusCommand;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * description
 *
 * @author Jonathan
 * @since 10/04/14
 */
public class AfkCheck extends PlayerQueueConstructor {
    private long calledEveryMiliSeconds;
    private long afkAfterMiliSeconds;

    public AfkCheck(long calledEveryMiliSeconds, long afkAfterMiliSeconds, ClassPathXmlApplicationContext context) {
        super(context);
        this.calledEveryMiliSeconds = calledEveryMiliSeconds;
        this.afkAfterMiliSeconds = afkAfterMiliSeconds;
    }

    @Override
    public CommandQueue getCommandQueue() {
        CommandQueue commandQueue = new CommandQueue(getUserSession().getSessionHandler().getPluginManager());

        UpdateAfkStatusCommand updateAfkStatusCommand = new UpdateAfkStatusCommand(
                commandQueue,
                getUserSession(),
                calledEveryMiliSeconds,
                afkAfterMiliSeconds);


        MessageConsoleCommand afkMessage = new MessageConsoleCommand(
                commandQueue,
                updateAfkStatusCommand
        );

        commandQueue.addCommand(updateAfkStatusCommand);
        commandQueue.addCommand(afkMessage);

        return commandQueue;
    }
}
