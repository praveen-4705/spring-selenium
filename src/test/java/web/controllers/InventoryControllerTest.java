package web.controllers;

import java.util.Map;

import junit.framework.TestCase;

import org.springframework.web.servlet.ModelAndView;

import service.SimpleProductManager;

public class InventoryControllerTest extends TestCase {

	 public void testHandleRequestView() throws Exception{
	        InventoryController controller = new InventoryController();
	        controller.setProductManager(new SimpleProductManager());	        
	        ModelAndView modelAndView = controller.handleRequest(null, null);
	        assertEquals("web/hello.jsp", modelAndView.getViewName());
	        assertNotNull(modelAndView.getModel());
	        Map modelMap = (Map) modelAndView.getModel().get("model");
	        String nowValue = (String) modelMap.get("now");
	        assertNotNull(nowValue);
	    }
    
    
}