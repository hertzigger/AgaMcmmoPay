package com.craftaga.agamcmmowage.commands;

import com.craftaga.agabacbone.commands.Command;
import com.craftaga.agabacbone.commands.IValueHolderCommand;
import com.craftaga.agabacbone.commands.Messenger;
import com.craftaga.agabacbone.commands.decision.IDecisionCommand;
import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.commands.queue.SubQueue;
import com.craftaga.agabacbone.session.IUserSession;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;

/**
 * description
 *
 * @author Jonathan
 * @since 10/04/14
 */
public class UpdateAfkStatusCommand extends Command implements Messenger
{
    private ClassPathXmlApplicationContext stringContext;
    private IUserSession userSession;
    private long calledEveryMiliSeconds;
    private long afkAfterMiliSeconds;
    private Long afkTime;

    public UpdateAfkStatusCommand(
            CommandQueue commandQueue,
            IUserSession userSession,
            long calledEveryMiliSeconds,
            long afkAfterMiliSeconds
    ) {
        super(commandQueue);
        ClassLoader cl = this.getClass().getClassLoader();
        stringContext = new ClassPathXmlApplicationContext(new String[]{"i18n/messageContext.xml"}, false);
        stringContext.setClassLoader(cl);
        stringContext.refresh();
        this.userSession = userSession;
        this.calledEveryMiliSeconds = calledEveryMiliSeconds;
        this.afkAfterMiliSeconds = afkAfterMiliSeconds;
    }

    @Override
    public void execute() {
        if ((userSession.getUser() instanceof Player)) {
            Player player = (Player) userSession.getUser();
            if (userSession.getState("savedPosition") == null) {
                userSession.setState("savedPosition", player.getLocation());
                resetAfkTick();
            }
            Location location = (Location)userSession.getState("savedPosition");
            if (locationsMatch(player.getLocation(), location)) {
                long currentAfk = (Long)userSession.getState("afkMiliSeconds");
                currentAfk += calledEveryMiliSeconds;
                userSession.setState("afkMiliSeconds", currentAfk);
                afkTime = currentAfk;
                if (currentAfk > afkAfterMiliSeconds) {
                    userSession.setState("afk", true);
                } else {
                    userSession.setState("savedPosition", player.getLocation());
                }
            } else {
                resetAfkTick();
                userSession.setState("savedPosition", player.getLocation());
            }
        }
    }

    private void resetAfkTick()
    {
        userSession.setState("afkMiliSeconds", new Long(0));
        userSession.setState("afk", false);
        afkTime = new Long(0);
    }

    private boolean locationsMatch(Location location1, Location location2)
    {
        if (
            (location1.getX() == location2.getX()) &&
            (location1.getZ() == location2.getZ()) &&
            (location1.getY() == location2.getY()) &&
            (location1.getWorld().getName() == location2.getWorld().getName())
        )
        {
            return true;
        }
        return false;
    }

    @Override
    public StringBuilder getMessage() {
        return new StringBuilder(stringContext.getMessage("pluginTag", new String[] {}, Locale.ENGLISH) + " " +
                stringContext.getMessage(
                        "timer.afkCheck.afk",
                        new String[] {
                                userSession.getUser().getName(),
                                afkTime.toString()
                        },
                        Locale.ENGLISH
                )
        );
    }
}
