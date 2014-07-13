package com.craftaga.agamcmmowage.commands;

import com.craftaga.agabacbone.commands.Command;
import com.craftaga.agabacbone.commands.decision.IDecisionCommand;
import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.commands.queue.SubQueue;
import com.craftaga.agabacbone.session.IUserSession;

/**
 * description
 *
 * @author Jonathan
 * @since 11/04/14
 */
public class PlayerAFKDecisionCommand extends Command implements IDecisionCommand {

    private IUserSession userSession;
    private SubQueue afkQueue;
    private SubQueue notAfkQueue;

    public PlayerAFKDecisionCommand(
            CommandQueue commandQueue,
            IUserSession userSession,
            SubQueue afkQueue,
            SubQueue notAfkQueue
    ) {
        super(commandQueue);
        this.userSession = userSession;
        this.afkQueue = afkQueue;
        this.notAfkQueue = notAfkQueue;
    }

    @Override
    public void execute() {

        if (userSession.getState("afk") != null) {
            Boolean afk = (Boolean) userSession.getState("afk");
            if (afk) {
                this.afkQueue.execute();
            } else {
                this.notAfkQueue.execute();
            }
        }
    }

    @Override
    public void setSuccess(SubQueue afkQueue) {
        this.afkQueue = afkQueue;
    }

    @Override
    public SubQueue getSuccess() {
        return afkQueue;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setFailure(SubQueue notAfkQueue) {
        this.notAfkQueue = notAfkQueue;
    }

    @Override
    public SubQueue getFailure() {
        return notAfkQueue;
    }
}
