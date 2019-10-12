package com.baomidou.mybatisplus.samples.crud.framework.mp.parser;

import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.reflection.MetaObject;

import java.util.List;

/**
 * 动态表名处理器,支持数据分散到多个表中
 *
 * @author joking
 * @since 2019-10-12
 */
public interface IMultiTableNameHandler {

    /**
     * 表名 SQL 处理
     *
     * @param metaObject 元对象
     * @param sql        当前执行 SQL
     * @param tableName  表名
     * @return 最终的 sql 语句
     */
    default String process(MetaObject metaObject, String sql, String tableName) {
        List<String> dynamicTableNames = dynamicTableName(metaObject, sql, tableName);
        StringBuilder sqlBuilder = new StringBuilder();
        MappedStatement mappedStatement = SqlParserHelper.getMappedStatement(metaObject);
//        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
//        MapperMethod.ParamMap paramMap = (MapperMethod.ParamMap) boundSql.getParameterObject();
//        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        if(dynamicTableNames != null && !dynamicTableNames.isEmpty()) {
            if(SqlCommandType.SELECT == sqlCommandType) {
                int size = dynamicTableNames.size();
                for (int i = 0; i < size; i++) {
                    String dynamicTableName = dynamicTableNames.get(i);
                    if (StringUtils.isNotEmpty(dynamicTableName)) {
                        String sqlItem = sql.replaceAll(tableName, dynamicTableName);
                        sqlBuilder.append(sqlItem);
                        deleteLastBlank(sqlBuilder);
                        int length = sqlBuilder.length();
                        if (sqlBuilder.lastIndexOf(";") == length - 1) {
                            sqlBuilder.deleteCharAt(length - 1);
                        }
                        if (i != size - 1) {
                            sqlBuilder.append(" UNION ALL \n");
                        } else {
                            sqlBuilder.append("; \n");
                        }
                    }
                }
            } else {
                String dynamicTableName = dynamicTableNames.get(0);
                return sql.replaceAll(tableName, dynamicTableName);
            }
            return sqlBuilder.toString();
        }
        return sql;
    }

    /**
     * 去掉字符串后面的  \r \s \n
     *
     * @param builder {@link StringBuilder}
     */
    static void deleteLastBlank(StringBuilder builder) {
        int position = builder.length() - 1;
        while(position >= 0) {
            char a = builder.charAt(position);
            if(a == '\r' || a == ' ' || a == '\n') {
                builder.deleteCharAt(position--);
            } else {
                break;
            }
        }
    }

    /**
     * 生成动态表名，无改变返回 NULL
     *
     * @param metaObject 元对象
     * @param sql        当前执行 SQL
     * @param tableName  表名
     * @return String
     */
    List<String> dynamicTableName(MetaObject metaObject, String sql, String tableName);
}
