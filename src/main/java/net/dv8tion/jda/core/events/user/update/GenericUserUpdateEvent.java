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

package net.dv8tion.jda.core.events.user.update;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.UpdateEvent;
import net.dv8tion.jda.core.events.user.GenericUserEvent;

public abstract class GenericUserUpdateEvent<T> extends GenericUserEvent implements UpdateEvent<User, T>
{
    protected final T previous;
    protected final T next;
    protected final String identifier;

    public GenericUserUpdateEvent(
        JDA api, long responseNumber, User user,
        T previous, T next, String identifier)
    {
        super(api, responseNumber, user);
        this.previous = previous;
        this.next = next;
        this.identifier = identifier;
    }

    @Override
    public User getEntity()
    {
        return getUser();
    }

    @Override
    public String getPropertyIdentifier()
    {
        return identifier;
    }

    @Override
    public T getOldValue()
    {
        return previous;
    }

    @Override
    public T getNewValue()
    {
        return next;
    }

    @Override
    public String toString()
    {
        return "UserUpdate[" + getPropertyIdentifier() + "](" + getOldValue() + "->" + getNewValue() + ')';
    }
}
