package web.controllers;

import junit.framework.TestCase;

import org.springframework.web.servlet.ModelAndView;

public class InventoryControllerTest extends TestCase {

	 public void testHandleRequestView() throws Exception{
	        InventoryController controller = new InventoryController();
	        ModelAndView modelAndView = controller.handleRequest(null, null);
	        assertEquals("web/hello.jsp", modelAndView.getViewName());
	        assertNotNull(modelAndView.getModel());
	        String nowValue = (String) modelAndView.getModel().get("now");
	        assertNotNull(nowValue);
	    }
    
    
}