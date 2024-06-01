package org.paasplatform.data.dbms;

import com.github.underscore.U;

public class JsonXmlConverter {
    public String xmlToJson(String json) {
//        String json = U.xmlToJson("<option value=\"0\">\n" +
//                "  <a t1=\"v\">b</a>\n" +
//                "</option>");
        System.out.println(json);
        String xml = U.jsonToXml(json);
        System.out.println(xml);

        return xml;

    }
}
