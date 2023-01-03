package com.neo4j.utils;

import io.quarkus.test.junit.main.LaunchResult;
import io.quarkus.test.junit.main.QuarkusMainLauncher;
import io.quarkus.test.junit.main.QuarkusMainTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusMainTest
public class MainTest {



    @Test
    public void testWithQueryArgument(QuarkusMainLauncher launcher) {
        LaunchResult result = launcher.launch("-u neo4j", "-p test1234", "-a neo4j://localhost:7687", "match (n) return count(*);");
        assertEquals(0, result.exitCode());
        assertTrue(result.getOutput().contains("count(*)"));
    }


    /*
    @Test
    @Launch(value = {}, exitCode = 1)
    public void testLaunchCommandFailed() {
    }
    */
}
