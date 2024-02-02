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
package org.commonjava.indy.model.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.commonjava.indy.model.core.PathStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static org.commonjava.indy.model.core.PathStyle.base64url;
import static org.commonjava.indy.model.core.PathStyle.hashed;

public class DefaultPathGenerator
{
    private final Logger logger = LoggerFactory.getLogger( getClass() );

    public String getStyledPath(final String rawPath, final PathStyle pathStyle)
    {
        String path = rawPath;
        if ( hashed.equals(pathStyle) )
        {
            File f = new File( path );
            String dir = f.getParent();
            if ( dir == null )
            {
                dir = "/";
            }

            if ( dir.length() > 1 && dir.startsWith( "/" ) )
            {
                dir = dir.substring( 1 );
            }

            String digest = DigestUtils.sha256Hex( dir );

            logger.trace( "Using SHA-256 digest: '{}' for dir: '{}' of path: '{}'", digest, dir, path );

            // For example: 00/11/001122334455667788/gulp-size-1.3.0.tgz
            path = String.format( "%s/%s/%s/%s", digest.substring( 0, 2 ), digest.substring( 2, 4 ), digest, f.getName() );
        }
        else if ( base64url.equals(pathStyle) )
        {
            /*
             * Use case: Generic proxy use base64 to encode remote paths to include query parameters. E.g,
             * "/path/to/simple?version=1.0" is encoded to 'L3BhdGgvdG8vc2ltcGxlP3ZlcnNpb249MS4wCg'. The proxy
             * sends the encoded path to indy. Indy generates storage path by splitting the first 4 letters, as
             * int this example, 'L3/Bh/L3BhdGgvdG8vc2ltcGxlP3ZlcnNpb249MS4wCg'
             */
            File f = new File( path );
            String base64Name = f.getName();
            if ( base64Name.length() >= 4 && Base64.isBase64( base64Name ) )
            {
                logger.trace( "Using split base64 of path: '{}'", path );
                path = String.format( "%s/%s/%s", base64Name.substring( 0, 2 ), base64Name.substring( 2, 4 ), f.getName() );
            }
        }
        return path;
    }
}
