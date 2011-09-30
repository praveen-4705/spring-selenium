package web.controllers;

import java.util.ArrayList;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

import repository.InMemoryProductDao;
import service.SimpleProductManager;
import domain.Product;

public class InventoryControllerTest extends TestCase {
    protected final Log logger = LogFactory.getLog(getClass());
	
	 public void testHandleRequestView() throws Exception{
	        InventoryController controller = new InventoryController();
	        SimpleProductManager spm = new SimpleProductManager();
	        spm.setProductDao(new InMemoryProductDao(new ArrayList<Product>()));
	        controller.setProductManager(spm);	        
	        ModelAndView modelAndView = controller.handleRequest(null, null);
	        assertEquals("web/hello.jsp", modelAndView.getViewName());
	        assertNotNull(modelAndView.getModel());
	        Map modelMap = (Map) modelAndView.getModel().get("model");
	        String nowValue = (String) modelMap.get("now");
	        assertNotNull(nowValue);
	    }
    
    
}