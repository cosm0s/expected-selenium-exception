package com.cosm0s.expected.test;

import org.apache.catalina.LifecycleException;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public abstract class AbstractTomcatEmbedded {

    @BeforeClass
    public static void initializeTomcat() throws IOException, LifecycleException {
        File war = new File(com.cosm0s.embedded.TomcatEmbedded.getTmpDir(), "showcase-6.0.war");
        if(!war.exists()) {
            FileUtils
                    .copyInputStreamToFile(
                            new URL("http://repository.primefaces.org/org/primefaces/showcase/6.0/showcase-6.0.war").openConnection().getInputStream(), war
                    );
        }
        com.cosm0s.embedded.TomcatEmbedded.getInstance("showcase-6.0");
    }

    @AfterClass
    public static void end() throws LifecycleException {
        com.cosm0s.embedded.TomcatEmbedded.stopTomcat();
    }

}
