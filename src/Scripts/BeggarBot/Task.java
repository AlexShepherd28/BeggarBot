package Scripts.BeggarBot;

import org.powerbot.script.ClientAccessor;
import org.powerbot.script.ClientContext;

public abstract class Task<C extends ClientContext> extends ClientAccessor<C> {
    public Task(C ctx) {
        super(ctx);
        ctx.dispatcher.add(this);
    }


    public abstract boolean activate();
    public abstract void execute();
}
