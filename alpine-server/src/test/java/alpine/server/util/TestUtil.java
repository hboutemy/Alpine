/*
 * This file is part of Alpine.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 * Copyright (c) Steve Springett. All Rights Reserved.
 */

package alpine.server.util;

import alpine.server.persistence.PersistenceManagerFactory;

import javax.jdo.PersistenceManager;
import javax.jdo.datastore.JDOConnection;
import java.sql.Connection;
import java.sql.Statement;

/**
 * @since 1.8.0
 */
public final class TestUtil {

    private TestUtil() {
    }

    public static void resetInMemoryDatabase() throws Exception {
        try (final PersistenceManager pm = PersistenceManagerFactory.createPersistenceManager()) {
            final JDOConnection jdoConnection = pm.getDataStoreConnection();
            try (final Connection conn = (Connection) jdoConnection.getNativeConnection();
                 final Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("DROP ALL OBJECTS DELETE FILES");
            }
        }
    }

}
