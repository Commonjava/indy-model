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
package org.commonjava.indy;

/**
 * Created by jdcasey on 3/14/16.
 */
public final class IndyContentConstants
{

    // for measuring transfer rates...
    public static final double NANOS_PER_SEC = 1E9;

    // for measuring timing in ms...
    public static final double NANOS_PER_MILLISECOND = 1E6;

    public static final String CHECK_CACHE_ONLY = "cache-only";

    public static final String CASCADE = "cascade";

    private IndyContentConstants(){}

}
