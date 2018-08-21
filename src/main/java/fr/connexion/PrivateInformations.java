package fr.connexion;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public abstract class PrivateInformations extends ListenerAdapter {
    protected enum CATEGORY {NO_CAT} // authorised categories
    protected enum CHANNEL {NO_CHAN} // authorised channels


    /* private informations */
    protected static String token = "Your-Token-Goes-Here";
    private static String[] categoryIds = {""};
    private static String[] channelIds = {""};

    /* Use this static block to re-initialise private informations */
    static {
    }



    /** Return if the message was received in the given category.
     * @param event when message was received
     * @param category
     * @return
     */
    protected boolean isOnCategory(MessageReceivedEvent event, CATEGORY category) {
        String categoryID;
        int ordinal = category.ordinal();
        if (ordinal < categoryIds.length)
            categoryID = categoryIds[ordinal];
        else
            categoryID = categoryIds[0];
        return event.getTextChannel().getParent().getId().equals(categoryID);
    }


    /** Return if the message was received in the given channel.
     * @param channel where message was received
     * @param salon
     * @return
     */
    protected boolean isOnSalon(MessageChannel channel, CHANNEL salon) {
        String salonID;
        int ordinal = salon.ordinal();
        if (ordinal < channelIds.length)
            salonID = channelIds[ordinal];
        else
            salonID = channelIds[0];
        return channel.getId().equals(salonID);
    }
}
