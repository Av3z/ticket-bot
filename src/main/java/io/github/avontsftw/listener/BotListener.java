package io.github.avontsftw.listener;

import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class BotListener extends ListenerAdapter {

    public void onReady(@NotNull ReadyEvent event) {
        event.getJDA().getPresence().setActivity(Activity.playing("ticket bot."));
        event.getJDA().setAutoReconnect(true);

        System.out.println("[Ready] Presença atualizada com sucesso!");
    }
}
