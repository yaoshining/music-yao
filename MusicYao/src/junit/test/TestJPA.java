package junit.test;

import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import junit.framework.TestCase;

import org.apache.openejb.api.LocalClient;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.junit.Before;
import org.junit.Test;

import com.musicyao.po.Music;
import com.musicyao.service.MusicService;
import com.musicyao.util.AudioFileUtil;
@LocalClient
public class TestJPA {
	@EJB
	private MusicService musicService;
	@EJB
	private Caller transactionBean;
	@Before
	public void setUp(){
		final Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
		p.put("log4j.rootLogger", "fatal,C");
		p.put("log4j.category.OpenEJB", "warn");
		p.put("log4j.category.OpenEJB.options", "info");
		p.put("log4j.category.OpenEJB.server", "info");
		p.put("log4j.category.OpenEJB.startup", "info");
		p.put("log4j.category.OpenEJB.startup.service", "warn");
		p.put("log4j.category.OpenEJB.startup.config", "info");
		p.put("log4j.category.OpenEJB.hsql", "info");
		p.put("log4j.category.CORBA-Adapter", "info");
		p.put("log4j.category.Transaction", "warn");
		p.put("log4j.category.org.apache.activemq", "error");
		p.put("log4j.category.org.apache.geronimo", "error");
		p.put("log4j.category.openjpa", "error");
		p.put("log4j.appender.C", "org.apache.log4j.ConsoleAppender");
		p.put("log4j.appender.C.layout", "org.apache.log4j.SimpleLayout");
        
		InitialContext initialContext;
		try {
			initialContext = new InitialContext(p);
			initialContext.bind("inject", this);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void test() throws Exception {
		transactionBean.call(new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				Music music = AudioFileUtil.readFile("E:\\Music\\英语\\Ann Winsborn\\La La Love On My Mind.mp3");
				List<Music> musicList = musicService.random(10);
				System.out.println(musicList.size());
				return null;
			}
		});
	}
	
	public static interface Caller {
        public <V> V call(Callable<V> callable) throws Exception;
    }

    @Stateless
    @TransactionAttribute(REQUIRES_NEW)
    public static class TransactionBean implements Caller {

        public <V> V call(Callable<V> callable) throws Exception {
            return callable.call();
        }
    }
}
