package io.github.avontsftw;

import io.github.avontsftw.listener.BotListener;
import io.github.avontsftw.listener.MessageListener;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class DiscordClient {

    @Getter
    private static JDA jda;

    public static void main(String[] args) {
        try {
            jda = JDABuilder.createDefault(Constants.CLIENT_TOKEN).build();
            /* Registrando eventos */
            jda.addEventListener(new BotListener(), new MessageListener());

            System.out.println("[Client] Conectado com sucesso!");
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
