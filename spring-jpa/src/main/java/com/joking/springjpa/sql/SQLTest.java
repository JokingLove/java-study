package com.joking.springjpa.sql;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLObject;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.*;
import com.alibaba.druid.sql.ast.statement.*;
import com.alibaba.druid.sql.dialect.mysql.ast.expr.MySqlExprImpl;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlInsertStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlASTVisitorAdapter;
import com.alibaba.druid.sql.parser.SQLExprParser;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.alibaba.druid.util.JdbcConstants;
import com.alibaba.druid.util.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLTest {

    static String SQL = "SELECT o.order_code,o.store_code,si.enterprise_name store_name,o.coop_type,o.payable_amount amount,o.status,\n" +
            "      CASE WHEN o.aftersale_status is null THEN 3 ELSE o.aftersale_status END aftersale_status,o.ship_status,\n" +
            "    GROUP_CONCAT(toi.product_name) setMealName,o2.activityType,o.business_code,o.business_name,o.audit_status,o.create_time,\n" +
            "  si.cooperation_mode storeCoopType,si.`status` storeStatus,o.customer_project_code customerProjectCode,o.audit_time auditTime,of1.create_time uploadTime\n" +
            "    from t_order o\n" +
            "    JOIN t_order_item toi on o.order_code = toi.order_code and toi.delete_flag = 0\n" +
            "    JOIN t_store_info si on o.store_code = si.store_code\n" +
            "    LEFT JOIN (select tof.order_code,tof.create_time from t_order o1 JOIN t_order_file tof on o1.order_code = tof.order_code and tof.delete_flag = 0 and tof.type = 2 GROUP BY o1.order_code) of1 on o.order_code = of1.order_code\n" +
            "    LEFT JOIN (SELECT GROUP_CONCAT(DISTINCT oa.acitvity_policy_name) activityType,oa.order_code from  t_order_activity oa where oa.delete_flag = 0 group by oa.order_code) o2 on o2.order_code = o.order_code";

    public static void main(String[] args) {

        // 1. 复杂sql
        String s = interceptSql(SQL);
//        System.out.println(s);


        SQL = "select * from a as aa, b as bb where aa.d = bb.d";
        /*
         *         SELECT *
         *                 FROM a aa, b bb
         *         WHERE aa.d = bb.d
         *         AND aa.organization = 1
         */

        s = interceptSql(SQL);
        System.out.println(s);


        //  insert 语句
        SQL =  "insert into a (id, name, age) values (1, 'zhangsan', 18), (2, 'lisi', 18)";
        /*
         *          INSERT INTO a (id, name, age, organization)
         *            VALUES (1, 'zhangsan', 18, 2),
         *                	(2, 'lisi', 18, 2)
         */

        s = interceptSql(SQL);
        System.out.println(s);


        // union all
        SQL = "select * from a  union all select * from b ";
        /*
         * SELECT *
         * FROM a
         * WHERE organization = 1
         * UNION ALL
         * SELECT *
         * FROM b
         * WHERE organization = 1
         */
        s = interceptSql(SQL);
        System.out.println(s);



    }

    private static String interceptSql(String sql) {
        List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, JdbcConstants.MYSQL);

        ExportTableAliasVisitor visitor = new ExportTableAliasVisitor();
        for (SQLStatement stmt : stmtList) {
            stmt.accept(visitor);
        }

        return SQLUtils.toSQLString(stmtList.get(0));
    }



    public static class ExportTableAliasVisitor extends MySqlASTVisitorAdapter {
        private Map<String, SQLTableSource> aliasMap = new HashMap<>();

        private Map<String, Object> map = new HashMap<>();
        public boolean visit(SQLExprTableSource x) {
            String alias = x.getAlias();
            aliasMap.put(alias, x);
            return true;
        }


        public Map<String, SQLTableSource> getAliasMap() {
            return aliasMap;
        }

        @Override
        public boolean visit(MySqlInsertStatement x) {
            List<SQLInsertStatement.ValuesClause> valuesList = x.getValuesList();
            List<SQLExpr> columns = x.getColumns();
            if(!CollectionUtils.isEmpty(columns)) {
                x.addColumn(SQLUtils.toSQLExpr("organization"));
            }
            if (!CollectionUtils.isEmpty(valuesList)) {
                for (SQLInsertStatement.ValuesClause valuesClause : valuesList) {
                    valuesClause.addValue(SQLUtils.toSQLExpr("2"));
                }
            }
            return true;
        }


        @Override
        public boolean visit(SQLBinaryOpExpr x) {
            return true;
        }


        @Override
        public boolean visit(MySqlSelectQueryBlock x) {
            SQLTableSource from = x.getFrom();
            String alias = from.getAlias();
            if (from instanceof SQLJoinTableSource) {
                alias = findLeftTableSourceAlias(from);
            }
            x.addWhere(getOrganizationExpr(alias));
            return true;
        }

        private String findLeftTableSourceAlias(SQLTableSource from) {
            if (from instanceof SQLJoinTableSource) {
                return findLeftTableSourceAlias(((SQLJoinTableSource) from).getLeft());
            }
            return from.getAlias();
        }

        @Override
        public boolean visit(SQLJoinTableSource x) {
            SQLExpr condition = x.getCondition();
            if (condition != null) {
                List<SQLObject> children = condition.getChildren();
                SQLObject sqlObject = children.get(0);
                if (sqlObject instanceof SQLPropertyExpr) {
                    SQLPropertyExpr propertyExpr = (SQLPropertyExpr) sqlObject;
                    String ownernName = propertyExpr.getOwnernName();
                    SQLExpr sqlExpr = getOrganizationExpr(ownernName);
                }
            }
            return true;
        }

        @Override
        public boolean visit(SQLUnionQueryTableSource x) {
//            System.out.println(x);
            return true;
        }


        @Override
        public boolean visit(SQLValuesTableSource x) {
//            System.out.println(x);
            return true;
        }

        @Override
        public boolean visit(SQLSubqueryTableSource x) {
//            System.out.println(x);
            return true;
        }

        private SQLExpr getOrganizationExpr(String ownernName) {
            SQLExpr sqlExpr = SQLUtils.toSQLExpr("organization = 1", JdbcConstants.MYSQL);
            if (!StringUtils.isEmpty(ownernName)) {
                sqlExpr = SQLUtils.toSQLExpr(String.format("%s.organization = 1", ownernName), JdbcConstants.MYSQL);
            }
            return sqlExpr;
        }

        @Override
        public boolean visit(SQLInsertStatement.ValuesClause x) {
//            System.out.println(x);
            return true;
        }

    }
}
