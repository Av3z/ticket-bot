package io.github.avontsftw.listener;

import io.github.avontsftw.Constants;
import io.github.avontsftw.exception.CategoryNotFoundException;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {

    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
        /* Entidades */
        Guild guild = event.getGuild();
        Member member = event.getMember();
        Category category = event.getGuild().getCategoryById(Constants.CATEGORY_ID);

        MessageChannel channel = event.getChannel();
        MessageReaction.ReactionEmote reaction = event.getReaction().getReactionEmote();

        /* ID da mensagem */
        String messageID = event.getMessageId();

        /* Verificando se o membro & categoria existem. */
        if (category == null) new CategoryNotFoundException().printStackTrace();

        /* Verificando se o ID do canal/mensagem é igual ao que está nas Constantes */
        if (!channel.getId().equalsIgnoreCase(Constants.CHANNEL_ID)) return;
        if (!messageID.equalsIgnoreCase(Constants.MESSAGE_ID)) return;

        if (reaction.getName().equalsIgnoreCase(Constants.EMOJI_NAME)) {
            for (TextChannel t : guild.getTextChannels()) {
                if (t.getName().equalsIgnoreCase("ticket-" + member.getId())) return;
            }

            TextChannel textChannel = guild.createTextChannel("ticket-" + member.getId(), category).complete();
            textChannel.createPermissionOverride(member)
                    .setAllow(Permission.VIEW_CHANNEL, Permission.MESSAGE_WRITE, Permission.MESSAGE_READ, Permission.MESSAGE_HISTORY, Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_ATTACH_FILES, Permission.MESSAGE_ADD_REACTION, Permission.MESSAGE_EXT_EMOJI)
                    .queue();
        }
    }
}
