package ua.autoshop.config;

/**
 * Created by Пользователь on 08.10.2015.
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import ua.autoshop.dal.Dao;
import ua.autoshop.dal.impl.*;
import ua.autoshop.dal.manager.Manager;
import ua.autoshop.dal.manager.ManagerImpl;
import ua.autoshop.model.*;
import ua.autoshop.utils.savers.impl.csv.PriceAutotechnixReader;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan("ua.autoshop")
@EnableWebMvc
//@EnableScheduling
@ImportResource("WEB-INF/spring-security.xml")
@EnableAspectJAutoProxy
public class AppConfig {



    @Bean
    PriceAutotechnixReader autotechnixReader(){
        return new PriceAutotechnixReader(PriceAutotechnix.class);
    }

    @Bean
    public Dao<User> daoUser() {
        return new UserDaoImpl();
    }

    @Bean
    public Dao<Margin> daoMargin() {
        return new MarginDaoImpl();
    }

    @Bean
    public Dao<PriceAutoshop> daoPriceAshop() {
        return new PriceAutoshopDaoImpl();
    }

    @Bean(name = "readerManager")
    public Manager readerManager() {
        return new ManagerImpl();
    }

    @Bean(name = "manager")
    public Manager manager() {
        return new ManagerImpl();
    }

    @Bean
    public Dao<Updates> daoUpdates() {
        return new UpdatesDaoImpl();
    }

    @Bean
    public Dao <PriceGerasimenko> daoPriceG() {
        return new PriceGerasimenkoDaoImpl();
    }

    @Bean
    public Dao <PriceVlad> daoPriceV() {
        return new PriceVladDaoImpl();
    }

    @Bean
    public Dao <PriceAutotechnix> daoPriceA() {
        return new PriceAutotechnixDaoImpl();
    }

    @Bean
    public Dao <PriceIntercarsi> daoPriceI() {
        return new PriceIntercarsiDaoImpl();
    }

    @Bean
    public EntityManager entityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Pricegenerator");
        return emf.createEntityManager();
    }

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        resolver.setOrder(1);
        return resolver;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }

}
