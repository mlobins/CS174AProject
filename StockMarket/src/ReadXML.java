import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

//https://stackoverflow.com/questions/14671896/parse-xml-document-with-dom-parser-with-multiple-elements-for-each-tag

public class ReadXML {

	// public static void readXML() throws Exception {
	public static void main(String[] args) throws Exception {

		XPathFactory xpf = XPathFactory.newInstance();
		XPath xPath = xpf.newXPath();
		XPathExpression titleExpression = xPath.compile("TITLE");
		XPathExpression productionYearExpression = xPath.compile("PRODUCTIONYEAR");
		XPathExpression reviewsExpression = xPath.compile("REVIEWS");
		XPathExpression rankingOrganization;
		// XPathExpression expr = xPath.compile("REVIEWS[@AUTHOR='name_id_2']/@name");
		XPathExpression rankingExpression = xPath.compile("RANKING");
		XPathExpression organizationExpression;

		InputSource inputSource = new InputSource("C:\\Users\\mlobi\\Documents\\StockMarket\\src\\Movies.xml");
		NodeList movieNodes = (NodeList) xPath.evaluate("/MOVIES/MOVIE", inputSource, XPathConstants.NODESET);

		System.out.println("----------------------------");

		for (int x = 0; x < movieNodes.getLength(); x++) {
			Node movieElement = movieNodes.item(x);

			//System.out.println("Movie: " + x);
			
			// Title
			System.out.println("Title: " + titleExpression.evaluate(movieElement, XPathConstants.STRING));

			// Production Year
			System.out.println(
					"Production Year: " + productionYearExpression.evaluate(movieElement, XPathConstants.STRING));
			// Reviews
			NodeList reviews = (NodeList) reviewsExpression.evaluate(movieElement, XPathConstants.NODESET);

			for (int y = 0; y < reviews.getLength(); y++) {
				rankingOrganization = xPath.compile("string(/*/MOVIE[" + (x+1) + "]/REVIEWS[" + (y+1) + "]/@AUTHOR)");
				System.out.println("Movie: " + x + " Review: " + y);
				System.out.println("Review by " + rankingOrganization.evaluate(inputSource, XPathConstants.STRING)
						+ ": " + reviews.item(y).getTextContent());
			}

			// Rankings
			organizationExpression = xPath.compile("string(/*/MOVIE/RANKING/@ORGANIZATION)");
			System.out.println("Rankings in " + organizationExpression.evaluate(inputSource, XPathConstants.STRING)
					+ ": " + rankingExpression.evaluate(movieElement, XPathConstants.STRING));

			System.out.println("----------------------------");
		}
	}

}
