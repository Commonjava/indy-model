/**
 * Copyright (C) 2022 Red Hat, Inc. (https://github.com/Commonjava/indy-event-model)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.commonjava.event.store;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.commonjava.event.common.EventMetadata;

import java.util.List;
import java.util.Map;

import static org.commonjava.event.store.StoreEventType.PostUpdate;

/**
 * Event signaling that one or more specified {@link EventStoreKey} instances' configurations were changed. The {@link StoreUpdateType}
 * gives more information about the nature of the update.
 * <br/>
 * This event is fired <b>AFTER</b> the updated {@link EventStoreKey} is actually persisted.
 * <br/>
 * As opposed to the {@link StorePostUpdateEvent}, this one MAY run asynchronously to avoid performance penalties for the user..
 */
public class StorePostUpdateEvent
        extends AbstractStoreUpdateEvent
{

    public StorePostUpdateEvent( final @JsonProperty( "updateType" ) StoreUpdateType type,
                                 final @JsonProperty( "eventMetadata" ) EventMetadata eventMetadata,
                                 final @JsonProperty( "changeMap" )
                                         Map<EventStoreKey, Map<String, List<Object>>> changeMap )
    {
        super( type, eventMetadata, changeMap );
    }

    @Override
    public StoreEventType getEventType()
    {
        return PostUpdate;
    }

    @JsonSetter( "eventType" )
    public final void setEventType( StoreEventType eventType )
    {
        checkEventType( PostUpdate, eventType );
    }
}
