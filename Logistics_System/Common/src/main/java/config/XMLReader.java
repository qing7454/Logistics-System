package config;
import java.io.File;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 
 * @author Martin3000
 * 
 */
public class XMLReader {
	// 配置文件名
	private static String filename = "config.xml";
	private static IPconfig config;

	/**
	 * 从配置文件中读取参数并保存到Config类中,
	 * 很多时候程序中会多次使用到配置中的参数, 
	 * 于是设置成静态方法,读取一次后就一直保存其中的参数，
	 * 不再反复读取
	 * 
	 * @return
	 */
	public static IPconfig loadconfig() {
		if (config == null)
			config = getconfig();
		return config;
	}

	private static IPconfig getconfig() {
		IPconfig config = new IPconfig();
		try {
			File f = new File(filename);
			if (!f.exists()) {
				System.out.println("  Error : Config file doesn't exist!");
				System.exit(1);
			}
			SAXReader reader = new SAXReader();
			Document doc = reader.read(f);
			Element root = doc.getRootElement();
			Element data;
			Iterator<?> itr = root.elementIterator("IPconfig");
			data = (Element) itr.next();

			config.IP = data.elementText("IPaddress").trim();
			config.PORT = data.elementText("Port").trim();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return config;

	}
}

