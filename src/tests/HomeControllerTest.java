package tests;


import org.junit.Test;


import controllers.HomeController;

public class HomeControllerTest {
	@Test
	public void testHomePage() throws Exception {
		HomeController controller = new HomeController();
		//assertEquals("home", controller.home());
		System.out.println(System.getProperty("java.class.path"));
	}
}