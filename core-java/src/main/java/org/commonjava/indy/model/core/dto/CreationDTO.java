/**
 * Copyright (C) 2022-2023 Red Hat, Inc. (https://github.com/Commonjava/indy-model)
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
package org.commonjava.indy.model.core.dto;

import java.net.URI;

/** DTO encapsulating the result of a POST operation somewhere in the system. These should all have similar features, in the sense of having a 
 * <code>Location</code> HTTP header pointing to the new resource, and a JSON response that contains the serialized form of new resource itself.
 * <br>
 * <b>These are NOT meant to be deserialized by the client API, but baked into the HTTP response headers/status, with the payload JSON in the body.</b>
 */
public class CreationDTO
{

    private final URI uri;

    private final String jsonResponse;

    public CreationDTO( final URI uri )
    {
        this.uri = uri;
        this.jsonResponse = null;
    }

    public CreationDTO( final URI uri, final String jsonResponse )
    {
        this.uri = uri;
        this.jsonResponse = jsonResponse;
    }

    public URI getUri()
    {
        return uri;
    }

    public String getJsonResponse()
    {
        return jsonResponse;
    }

}
