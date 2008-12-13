/* $Id: PluginConfigurationException.java 476205 2006-11-17 16:43:10Z dennisl $
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 

package org.apache.commons.digester.plugins;

/**
 * Thrown when an error occurs due to the way the calling application uses
 * the plugins module. Because the pre-existing Digester API doesn't provide
 * any option for throwing checked exceptions at some points where Plugins
 * can potentially fail, this exception extends RuntimeException so that it
 * can "tunnel" through these points.
 *
 * @since 1.6
 */

public class PluginConfigurationException extends RuntimeException {
    private Throwable cause = null;

    /**
     * @param cause underlying exception that caused this to be thrown
     */
    public PluginConfigurationException(Throwable cause) {
        this(cause.getMessage());
        this.cause = cause;
    }

    /**
     * @param msg describes the reason this exception is being thrown.
     */
    public PluginConfigurationException(String msg) {
        super(msg);
    }

    /**
     * @param msg describes the reason this exception is being thrown.
     * @param cause underlying exception that caused this to be thrown
     */
    public PluginConfigurationException(String msg, Throwable cause) {
        this(msg);
        this.cause = cause;
    }

    /**
     * Return the cause of this exception (if any) as specified in the
     * exception constructor.
     * 
     * @since 1.8
     */
    public Throwable getCause() {
        return cause;
    }
}
