package Scripts.BeggarBot;

import org.powerbot.script.MessageEvent;
import org.powerbot.script.MessageListener;
import org.powerbot.script.rt4.*;
import org.powerbot.script.rt4.Player;


public class Trade extends Task<ClientContext> implements MessageListener {

    public static final String ACCEPT_TEXT = "Other player has accepted.";
    public final static int
            TRADE_SCREEN_1_WIDGET = 335,
            TRADE_SCREEN_2_WIDGET = 334,
            SCREEN_1_ACCEPT_BUTTON = 11,
            SCREEN_2_ACCEPT_BUTTON = 13,
            OTHER_PLAYER_ACCEPTED_1 = 30,
            OTHER_PLAYER_ACCEPTED_2 = 4;

    public static String msg;
    Player player;


    public Trade(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public void messaged(MessageEvent event) {
        msg = event.text().toLowerCase();
    }

    @Override
    public boolean activate() {
        return !isTrading() && player.valid();
    }

    @Override
    public void execute() {
        tradeWith(player);
        if(hasPlayerAccepted() == true); {
            accept();
            ctx.chat.sendInput("thank you");
        }
    }
    public void tradeWith(Player player) {
        if(msg.contains("wishes to trade with you.")) {
            player.interact("Scripts.BeggarBot.BeggarBot.Trade with", player.name());
        }
    }
    public boolean isTrading() {
        return firstTradeScreen() || secondTradeScreen();
    }
    public boolean accept() {
        return isTrading() && click(firstTradeScreen() ? TRADE_SCREEN_1_WIDGET : TRADE_SCREEN_2_WIDGET);
    }
    private boolean firstTradeScreen() {
        return ctx.widgets.component(TRADE_SCREEN_1_WIDGET, OTHER_PLAYER_ACCEPTED_1).visible();
    }
    private boolean secondTradeScreen() {
        return ctx.widgets.component(TRADE_SCREEN_2_WIDGET, OTHER_PLAYER_ACCEPTED_2).visible();
    }
    private boolean click(int component)     {
        return getWidget().component(component).click();
    }
    private Widget getWidget() {
        return ctx.widgets.widget(secondTradeScreen() ? TRADE_SCREEN_2_WIDGET : TRADE_SCREEN_1_WIDGET);
    }
    public boolean hasPlayerAccepted() {
        return isTrading() && getWidget().component(firstTradeScreen()
                ? SCREEN_1_ACCEPT_BUTTON : SCREEN_2_ACCEPT_BUTTON)
                .text().equals(ACCEPT_TEXT);
    }
}
