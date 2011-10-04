package web.controllers;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import repository.inMemory.InMemoryProductDao;
import service.implementation.SimpleProductManager;
import domain.Product;

public class InventoryControllerTest {
    protected final Log logger = LogFactory.getLog(getClass());
	
	 @Test
	public void testHandleRequestView() throws Exception{
	        InventoryController controller = new InventoryController();
	        SimpleProductManager spm = new SimpleProductManager();
	        spm.setProductDao(new InMemoryProductDao(new ArrayList<Product>()));
	        controller.setProductManager(spm);	        
	        ModelAndView modelAndView = controller.home(null, null);
	        AssertJUnit.assertEquals("web/inventory/home.jsp", modelAndView.getViewName());
	        AssertJUnit.assertNotNull(modelAndView.getModel());
	        Map modelMap = (Map) modelAndView.getModel().get("model");
	        String nowValue = (String) modelMap.get("now");
	        AssertJUnit.assertNotNull(nowValue);
	    }   
}