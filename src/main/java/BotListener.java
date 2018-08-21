/*
 *     Copyright 2015-2018 Austin Keener & Michael Ritter & Florian Spieß
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import fr.connexion.PrivateInformations;
import game.connexion.Profil;
import game.connexion.SingletonJoueurs;
import net.dv8tion.jda.client.entities.Group;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import javax.security.auth.login.LoginException;
import java.util.HashMap;
import java.util.Map;

import static fr.connexion.C_COULEUR.*;

public class BotListener extends PrivateInformations
{
    private static SingletonJoueurs singleton = SingletonJoueurs.getOccurence();
    private static Map<String, Profil> profilsConnected;

    public static void main(String[] args)
    {
        try
        {
            JDA jda = new JDABuilder(AccountType.BOT)
                    .setToken(token)           //The token of the account that is logging in.
                    .addEventListener(new BotListener())  //An instance of a class that will handle events.
                    .buildBlocking();  //There are 2 ways to login, blocking vs async. Blocking guarantees that JDA will be completely loaded.
        }
        catch (LoginException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        profilsConnected = new HashMap();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        JDA jda = event.getJDA();                       //JDA, the core of the api.
        long responseNumber = event.getResponseNumber();//The amount of discord events that JDA has received since the last reconnect.

        //Event specific information
        User author = event.getAuthor();                //The user that sent the message
        Message message = event.getMessage();           //The message that was received.
        MessageChannel channel = event.getChannel();    //This is the MessageChannel that the message was sent to.
                                                        //  This could be a TextChannel, PrivateChannel, or Group!

        String msg = message.getContentDisplay();              //This returns a human readable version of the Message. Similar to
                                                        // what you would see in the client.

        boolean bot = author.isBot();                    //This boolean is useful to determine if the User that
                                                        // sent the Message is a BOT or not!

        if (event.isFromType(ChannelType.TEXT))         //If this message was sent to a Guild TextChannel
        {
            Guild guild = event.getGuild();             //The Guild that this message was sent in. (note, in the API, Guilds are Servers)
            TextChannel textChannel = event.getTextChannel(); //The TextChannel that this message was sent to.
            Member member = event.getMember();          //This Member that sent the message. Contains Guild specific information about the User!

            String name;
            if (message.isWebhookMessage())
            {
                name = author.getName();                //If this is a Webhook message, then there is no Member associated
            }                                           // with the User, thus we default to the author for name.
            else
            {
                name = member.getEffectiveName();       //This will either use the Member's nickname if they have one,
            }                                           // otherwise it will default to their username. (User#getName())

            System.out.printf(C_GREEN + "(%s)[%s]<%s>: %s\n" + C_BASIC, guild.getName(), textChannel.getName(), name, msg);
        }
        else if (event.isFromType(ChannelType.PRIVATE)) //If this message was sent to a PrivateChannel
        {
            PrivateChannel privateChannel = event.getPrivateChannel();

            if (bot)
                System.out.printf(C_YELLOW + "[PRIV]<%s>: %s\n" + C_BASIC, author.getName(), msg);
            else
                System.out.printf("[PRIV]<%s>: %s\n", author.getName(), msg);

            Profil user;
            String id = author.getId();
            if (profilsConnected.containsKey(id)) // already connected
            {
                if (msg.length() == 1) {
                    try {
                        user = profilsConnected.get(id);
                        channel.sendMessage(user.execute(new Integer(msg))).queue();
                        if (!user.isConnected()) {
                            profilsConnected.remove(id);
                            System.out.println(C_BLUE + "connected " + C_BASIC + profilsConnected.keySet());
                        }
                    } catch (NumberFormatException e) {}
                }
            }
            else if (msg.startsWith("!connect"))
            {
                try {
                    if (singleton.createUser(id, msg.split(" ")[1]))
                        channel.sendMessage("Compte créé avec succès.").queue();
                    user = new Profil(id, msg.split(" ")[1]);
                    profilsConnected.put(id, user);
                    System.out.println(C_BLUE + "connected " + C_BASIC + profilsConnected.keySet());
                    channel.sendMessage(user.execute(0)).queue();
                } catch (IllegalArgumentException e) {
                    channel.sendMessage("Mauvais password.").queue();
                } catch (ArrayIndexOutOfBoundsException e) {
                    channel.sendMessage("Pas de password donné.").queue();
                }
            }
        }
        else if (event.isFromType(ChannelType.GROUP))   //If this message was sent to a Group. This is CLIENT only!
        {
            Group group = event.getGroup();
            String groupName = group.getName() != null ? group.getName() : "";  //A group name can be null due to it being unnamed.

            System.out.printf(C_GREEN + "[GRP: %s]<%s>: %s\n" + C_BASIC, groupName, author.getName(), msg);
        }


        if (msg.equals("!ping"))
        {
            channel.sendMessage("pong!").queue();
        }
        else if (msg.equals("!help"))
        {
            channel.sendMessage("Commandes :\n" +
                    "*!help*\tObtenir la liste des commandes autorisées\n" +
                    "*!connect <mdp>*\tSe connecter\n" +
                    "").queue();
        }
    }
}
