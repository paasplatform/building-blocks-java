package org.paasplatform.data.dbms;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "paasplatform.dbms")
public class DBMSProperties {
    private String testVar;

    public String getVar() {
        return testVar;
    }

    public void setVar(String testVar) {
        this.testVar = testVar;
    }
}
