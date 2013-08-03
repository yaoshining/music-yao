//package junit.test;
//
//import java.util.List;
//import java.util.Properties;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//
//import org.apache.cxf.jaxrs.client.WebClient;
//import org.apache.openejb.jee.WebApp;
//import org.apache.openejb.junit.ApplicationComposer;
//import org.apache.openejb.junit.Classes;
//import org.apache.openejb.junit.EnableServices;
//import org.apache.openejb.junit.Module;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import com.musicyao.controller.MusicController;
//import com.musicyao.po.Music;
//
//@SuppressWarnings("deprecation")
//@EnableServices(value = "jaxrs")
//@RunWith(ApplicationComposer.class)
//public class TestREST {
//	@Module
//	@Classes(MusicController.class)
//	public WebApp app() {
//		return new WebApp().contextRoot("test");
//	}
//	@Before
//	public void setUp() {
//	  final Properties p = new Properties();
////	  p.put("dictionaryDatabase", "new://Resource?type=DataSource");
////      p.put("dictionaryDatabase.JdbcDriver", "org.h2.Driver");
////      p.put("dictionaryDatabase.JdbcUrl", "jdbc:h2:tcp://localhost/~/dictionarydb");
//      p.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
//      try {
//			InitialContext initialContext = new InitialContext(p);
//			initialContext.bind("inject", this);
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
//	@Test
//	public void test() {
//		final List<Music> message = WebClient.create("http://localhost:8080").path("/music/radom/10").accept("text/xml").get(List.class);
//		System.out.println(message);
//	}
//}
