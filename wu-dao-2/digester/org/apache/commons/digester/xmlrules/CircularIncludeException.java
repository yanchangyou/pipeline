/* $Id: CircularIncludeException.java 471661 2006-11-06 08:09:25Z skitching $
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


package org.apache.commons.digester.xmlrules;


/**
 * Thrown when parsing XML into Digester rules, if a circular inclusion occurred
 * in the xml digester rules files.
 *
 * @since 1.2
 */

public class CircularIncludeException extends XmlLoadException {

    /**
     * @param fileName the name of the XML file suspected of causing the
     * circular inclusion
     */
    public CircularIncludeException(String fileName) {
        super("Circular file inclusion detected for file: " + fileName);
    }

}
