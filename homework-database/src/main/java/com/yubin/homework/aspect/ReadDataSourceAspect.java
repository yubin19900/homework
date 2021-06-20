package com.yubin.homework.aspect;

import com.yubin.homework.database.factory.DataSourceFactory;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-06-20 17:21
 **/
@Aspect
@Component
@Slf4j
public class ReadDataSourceAspect {
    @Autowired
    private DataSourceFactory dataSourceFactory;

    @Pointcut("@annotation(com.yubin.homework.annotation.ReadDataSource)")
    public void read() {
    }

    @Around("read()")
    public List<Map<String, Object>> setDataSource(ProceedingJoinPoint point) throws Throwable {
        Object[] argv = point.getArgs();
        argv[0] = dataSourceFactory.getSlaveDataSource();
        return (List<Map<String, Object>>) point.proceed(argv);
    }
}
