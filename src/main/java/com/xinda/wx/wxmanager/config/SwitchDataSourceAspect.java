package com.xinda.wx.wxmanager.config;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 切换数据源的切面
 *
 * @author lijie
 * @create 2018-13-22:55
 */

@Aspect
@Log4j2
@Component
@Order(1)
public class SwitchDataSourceAspect {

    @Pointcut("@annotation(com.xinda.wx.wxmanager.config.SwitchDataSource)")
    public void annotationPonit() {

    }

    @Before("annotationPonit()")
    public void beforeSwtichDataSource(JoinPoint point) {

        Class<?> className = point.getTarget().getClass();

        String methodName = point.getSignature().getName();

        //得到方法的参数的类型
        Class[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
        DataSourceType dataSourceType = DataSourceType.datasource1;
        try {
            // 得到访问的方法对象
            Method method = className.getMethod(methodName, argClass);

            // 判断是否存在@SwtichDataSource注解
            if (method.isAnnotationPresent(SwitchDataSource.class)) {
                SwitchDataSource annotation = method.getAnnotation(SwitchDataSource.class);

                // 取出注解中的数据源名
                dataSourceType = annotation.type();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("数据源切面方法出问题");
            // throw new BusinessException(Constant.ResultCode.SERVICE_FAIL, "数据源切换切面方法出错");
        }

        // 切换数据源
        DataSourceContentHolder.setDataBaseType(dataSourceType);
    }

    @After("annotationPonit()")
    public void afterSwitchDataSource(JoinPoint point) {
        DataSourceContentHolder.clear();
    }
}
