/******************************************************************************
 *  Copyright 2015 by OLTPBenchmark Project                                   *
 *                                                                            *
 *  Licensed under the Apache License, Version 2.0 (the "License");           *
 *  you may not use this file except in compliance with the License.          *
 *  You may obtain a copy of the License at                                   *
 *                                                                            *
 *    http://www.apache.org/licenses/LICENSE-2.0                              *
 *                                                                            *
 *  Unless required by applicable law or agreed to in writing, software       *
 *  distributed under the License is distributed on an "AS IS" BASIS,         *
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *
 *  See the License for the specific language governing permissions and       *
 *  limitations under the License.                                            *
 ******************************************************************************/

package com.oltpbenchmark.benchmarks.ycsb.procedures;

import com.oltpbenchmark.api.Procedure;
import com.oltpbenchmark.api.SQLStmt;
import com.oltpbenchmark.benchmarks.ycsb.YCSBConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadVLCounter extends Procedure{
    public final SQLStmt showStmt1 = new SQLStmt(
        "SHOW cnt_visibility_level_1"
    );

    public final SQLStmt showStmt2 = new SQLStmt(
            "SHOW cnt_visibility_level_1_blocking"
    );
    
	//FIXME: The value in ysqb is a byteiterator
    public void run(Connection conn, String counter[]) throws SQLException {
        PreparedStatement stmt = this.getPreparedStatement(conn, showStmt1);
        ResultSet r = stmt.executeQuery();
        r.next();
        counter[0] = r.getString(1);
        r.close();

        stmt = this.getPreparedStatement(conn, showStmt2);
        r = stmt.executeQuery();
        r.next();
        counter[1] = r.getString(1);
        r.close();
    }

}
