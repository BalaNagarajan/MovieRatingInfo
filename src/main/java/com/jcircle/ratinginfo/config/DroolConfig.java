package com.jcircle.ratinginfo.config;

import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

@Configuration
@Slf4j
public class DroolConfig {

    private KieServices kieServices = KieServices.Factory.get();

    private static final String RULES_PATH = "rules/";

    /**
     *
     */
    private KieFileSystem getKieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = this.kieServices.newKieFileSystem();
        // kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PATH+"movie.xlsx"));

        for (Resource file : getRuleFiles()) {
            kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PATH + file.getFilename(), "UTF-8"));
        }

        return kieFileSystem;
    }

    private Resource[] getRuleFiles() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.*");
    }




    @Bean
    public KieContainer getKieContainer() throws IOException {
        log.info("Container Initiation");
        this.getKieRepository();
        KieBuilder kieBuilder = kieServices.newKieBuilder(getKieFileSystem());
        kieBuilder.buildAll();
        KieModule kieModule = kieBuilder.getKieModule();
        KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());

        return kieContainer;

    }

    private void getKieRepository() {
        final KieRepository kieRepository = this.kieServices.getRepository();
        kieRepository.addKieModule(new KieModule() {
            @Override
            public ReleaseId getReleaseId() {
                return kieRepository.getDefaultReleaseId();
            }
        });


    }

    @Bean
    public KieSession getKieSession() throws IOException {
        log.info("Session Created...");
        return getKieContainer().newKieSession();
    }

}
