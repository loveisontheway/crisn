package com.crisn.common.utils.crisn;

import com.crisn.common.exception.ServiceException;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.function.BiFunction;

/**
 * Mybatis Batch
 *
 * @author jjl
 * @date 2022/5/16
 */
@Component
public class BatchUtil {

    /**
     * 每次处理1000条
     */
    private static final int BATCH_SIZE = 1000;

    /**
     * 批量处理修改或者插入
     *
     * 调用：
     * int result = batchUtil.batchInsertOrUpdate(sqlSessionFactory, list, LibMapper.class, (item, libMapper) -> libMapper.update(item));
     *
     * @param sqlSessionFactory Mybatis Object
     * @param data              需要被处理的数据
     * @param mapperClass       Mybatis的Mapper类
     * @param function          自定义处理逻辑
     * @return int 影响的总行数
     */
    public <T, U, R> int batchInsertOrUpdate(SqlSessionFactory sqlSessionFactory, List<T> data, Class<U> mapperClass, BiFunction<T, U, R> function) {
        int i = 1;
        SqlSession batchSqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            U mapper = batchSqlSession.getMapper(mapperClass);
            int size = data.size();
            for (T element : data) {
                function.apply(element, mapper);
                if ((i % BATCH_SIZE == 0) || i == size) {
                    batchSqlSession.flushStatements();
                }
                i++;
            }
            // 非事务环境下强制commit，事务情况下该commit相当于无效
            batchSqlSession.commit(!TransactionSynchronizationManager.isSynchronizationActive());
        } catch (Exception e) {
            batchSqlSession.rollback();
            throw new ServiceException(e.toString());
        } finally {
            batchSqlSession.close();
        }
        return i - 1;
    }

}
