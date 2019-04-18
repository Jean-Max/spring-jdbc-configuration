package com.viseo.formation;

import com.viseo.formation.config.MediaPlayerConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) // Allowed to have a Spring application context created when test starts
@ContextConfiguration(classes = {MediaPlayerConfig.class}) // Tells Spring to load the configuration
public class JavaConfigTest {

    @Test
    public void should_test_configuration (){

    }
}
