/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.guacamole.net.auth;

import java.util.List;
import java.util.Map;
import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.protocol.GuacamoleConfiguration;

/**
 * Represents a pairing of a GuacamoleConfiguration with a unique,
 * human-readable identifier, and abstracts the connection process. The
 * backing GuacamoleConfiguration may be intentionally obfuscated or tokenized
 * to protect sensitive configuration information.
 *
 * @author Michael Jumper
 */
public interface Connection extends Identifiable, Connectable {

    /**
     * Returns the name assigned to this Connection.
     * @return The name assigned to this Connection.
     */
    public String getName();

    /**
     * Sets the name assigned to this Connection.
     *
     * @param name The name to assign.
     */
    public void setName(String name);

    /**
     * Returns the unique identifier of the parent ConnectionGroup for
     * this Connection.
     * 
     * @return The unique identifier of the parent ConnectionGroup for
     * this Connection.
     */
    public String getParentIdentifier();

    /**
     * Sets the unique identifier of the parent ConnectionGroup for
     * this Connection.
     * 
     * @param parentIdentifier The unique identifier of the parent 
     * ConnectionGroup for this Connection.
     */
    public void setParentIdentifier(String parentIdentifier);

    /**
     * Returns the GuacamoleConfiguration associated with this Connection. Note
     * that because configurations may contain sensitive information, some data
     * in this configuration may be omitted or tokenized.
     *
     * @return The GuacamoleConfiguration associated with this Connection.
     */
    public GuacamoleConfiguration getConfiguration();

    /**
     * Sets the GuacamoleConfiguration associated with this Connection.
     *
     * @param config The GuacamoleConfiguration to associate with this
     *               Connection.
     */
    public void setConfiguration(GuacamoleConfiguration config);

    /**
     * Returns all attributes associated with this connection. The returned map
     * may not be modifiable.
     *
     * @return
     *     A map of all attribute identifiers to their corresponding values,
     *     for all attributes associated with this connection, which may not be
     *     modifiable.
     */
    Map<String, String> getAttributes();

    /**
     * Sets the given attributes. If an attribute within the map is not
     * supported, it will simply be dropped. Any attributes not within the
     * given map will be left untouched.
     *
     * @param attributes
     *     A map of all attribute identifiers to their corresponding values.
     */
    void setAttributes(Map<String, String> attributes);

    /**
     * Returns a list of ConnectionRecords representing the usage history
     * of this Connection, including any active users. ConnectionRecords
     * in this list will be sorted in descending order of end time (active
     * connections are first), and then in descending order of start time
     * (newer connections are first).
     *
     * @return A list of ConnectionRecrods representing the usage history
     *         of this Connection.
     *
     * @throws GuacamoleException If an error occurs while reading the history
     *                            of this connection, or if permission is
     *                            denied.
     */
    public List<? extends ConnectionRecord> getHistory() throws GuacamoleException;

}
