package com.viseo.formation;

import com.viseo.formation.config.MediaPlayerConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = MediaPlayerConfig.class)
public class RunApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MediaPlayerConfig.class);
        // Close the context to force beans destruction
        context.close();
    }
}
