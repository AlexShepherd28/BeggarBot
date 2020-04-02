package Scripts.BeggarBot;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.*;

public class Bank extends Task<ClientContext> {
    public Bank(ClientContext ctx) {
        super(ctx);
    }
    Actor interacting = ctx.players.local().interacting();

    @Override
    public boolean activate() {
        return ctx.inventory.select().count()>0;
    }

    @Override
    public void execute() {

    }
}
