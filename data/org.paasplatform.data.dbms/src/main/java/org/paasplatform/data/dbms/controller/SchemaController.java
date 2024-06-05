package org.paasplatform.data.dbms.controller;

import java.io.IOException;
import java.io.InputStream;

import org.paasplatform.data.dbms.HBMSchemaService;
import org.paasplatform.data.dbms.ISchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.underscore.U;

@RestController
@RequestMapping("/dbms")
@CrossOrigin(origins = "http://localhost:6060")
public class SchemaController {
    @Autowired
    ISchemaService schemaService;

    @GetMapping("/schema/execute")
    public String execute() {
        this.schemaService.execute();
        return  "ok";
    }

    @GetMapping("/schema/{name}")
    public String schema(@PathVariable("name") String name) {
        byte[] bytes = new byte[0];
        try (InputStream inputStream = HBMSchemaService.class
                .getResourceAsStream(String.format("/config/%s.hbm.xml", name))) {
            bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String xmlContent = new String(bytes);
        String json = U.xmlToJson(xmlContent);

        return json;
    }
}
