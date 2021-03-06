package com.asd.aem.ktln.core.servlets

import com.asd.aem.ktln.core.CredentialsService
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.SlingHttpServletResponse
import org.apache.sling.api.servlets.HttpConstants
import org.apache.sling.api.servlets.SlingAllMethodsServlet
import org.apache.sling.commons.json.JSONObject
import org.osgi.framework.Constants
import org.osgi.service.component.annotations.Component
import org.osgi.service.component.annotations.Reference
import javax.servlet.Servlet

@Component(service = arrayOf(Servlet::class),
        property = arrayOf(Constants.SERVICE_DESCRIPTION + "=Simple Kotlin Demo Servlet",
                "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                "sling.servlet.paths=" + "/services/kotlin/demo/servlet"))
class SimpleKotlinServlet : SlingAllMethodsServlet() {


    @Reference
    lateinit var credentialsService: CredentialsService;

    override fun doGet(request: SlingHttpServletRequest, response: SlingHttpServletResponse) {
        val jsonResponse = JSONObject()
                .put("name", "kotlin servlet")
                .put("version", "1.1.2-5")
                .put("password", credentialsService.password)
                .put("login", credentialsService.login)
                .put("useSecret", credentialsService.isUseSecret).toString();
        response.writer.write(jsonResponse)
    }
}