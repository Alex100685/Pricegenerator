package ua.autoshop.config;

import org.hibernate.engine.spi.SessionOwner;
import org.hibernate.jpa.internal.EntityManagerImpl;
import org.hibernate.jpa.spi.AbstractEntityManagerImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import ua.autoshop.dal.Dao;
import ua.autoshop.dal.DaoImpl;
import ua.autoshop.dal.annotation.AllowNullResult;
import ua.autoshop.dal.annotation.EntityManagerTransaction;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Пользователь on 19.11.2016.
 */
public class EntityManagerTransactionPostProcessor implements BeanPostProcessor {

    private String PACKAGE = "ua.autoshop.dal.impl";
    private Map<String, Class> map = new HashMap<String, Class>();

    @Autowired
    EntityManager entityManager;

    Object newBean;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        if (clazz.getName().startsWith(PACKAGE)) {
            map.put(beanName, clazz);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {
        Class clazz = map.remove(beanName);

        if (clazz != null) {
            return newBean = Proxy.newProxyInstance(clazz.getClassLoader(), DaoImpl.class.getInterfaces(), new InvocationHandler() {


                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    Object retVal = null;
                    boolean hasEntityManagerTransactionAnnotation = bean.getClass().getMethod(method.getName(), method.getParameterTypes()).isAnnotationPresent(EntityManagerTransaction.class);
                    boolean hasAllowNullResultAnnotation = bean.getClass().getMethod(method.getName(), method.getParameterTypes()).isAnnotationPresent(AllowNullResult.class);
                    TransactionExecutor executor = new TransactionTemplate().getTransactionTemplate(hasEntityManagerTransactionAnnotation);
                    try {
                        executor.startTransaction();
                        retVal = method.invoke(bean, args);
                        executor.finishTransaction();
                    } catch (Exception e) {
                        if(hasAllowNullResultAnnotation){
                            if(e.getCause() instanceof NoResultException){
                                return null;
                            }
                        }
                        executor.rollBackTransaction();
                        throw e;
                    }
                    return retVal;
                }
            });
        }
        return bean;
    }


    private class TransactionTemplate{

        TransactionExecutor getTransactionTemplate(boolean isTransactional){
            TransactionExecutor executor = null;
            if (isTransactional) {
                executor = new TransactionExecutor() {
                    @Override
                    public void startTransaction() {
                        entityManager.getTransaction().begin();
                    }

                    @Override
                    public void finishTransaction() {
                        entityManager.getTransaction().commit();
                        entityManager.clear();
                    }

                    @Override
                    public void rollBackTransaction() {
                        entityManager.getTransaction().rollback();
                    }
                };
            } else {
                executor = new TransactionExecutor() {
                    @Override
                    public void startTransaction() {

                    }

                    @Override
                    public void finishTransaction() {

                    }

                    @Override
                    public void rollBackTransaction() {

                    }
                };
            }
            return executor;
        }

    }

    private interface TransactionExecutor{

        void startTransaction();

        void finishTransaction();

        void rollBackTransaction();

    }

}
