package com.crimealert.util;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class TemplateUtil {
	private VelocityEngine ve;

	public TemplateUtil() {
	     ve = new VelocityEngine();
	     ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
	     ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
	     ve.init();		
	}
	
	private String getTemplate(final String template, final VelocityContext context) {
	     final Template t = ve.getTemplate(template);
	     StringWriter writer = new StringWriter();
	     t.merge( context, writer );
	     return writer.toString(); 		
	}
	
	public String getEmailTemplate(final String template, final VelocityContext context) {	     
	     String email = getTemplate(template, context);
	     return email;		
	}
}
