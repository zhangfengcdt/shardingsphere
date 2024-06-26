/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.proxy.backend.hbase.converter.type;

import lombok.RequiredArgsConstructor;
import org.apache.shardingsphere.infra.binder.context.statement.SQLStatementContext;
import org.apache.shardingsphere.proxy.backend.hbase.bean.HBaseOperation;
import org.apache.shardingsphere.proxy.backend.hbase.converter.HBaseOperationConverter;
import org.apache.shardingsphere.proxy.backend.hbase.converter.operation.HBaseRegionReloadOperation;

import java.util.List;
import java.util.stream.Collectors;

/**
 * HBase region reload operation converter.
 */
@RequiredArgsConstructor
public final class HBaseRegionReloadOperationConverter implements HBaseOperationConverter {
    
    private final SQLStatementContext sqlStatementContext;
    
    @Override
    public HBaseOperation convert() {
        List<String> tables = sqlStatementContext.getTablesContext().getSimpleTables().stream().map(each -> each.getTableName().getIdentifier().getValue()).collect(Collectors.toList());
        return new HBaseOperation(String.join(",", tables), new HBaseRegionReloadOperation());
    }
}
