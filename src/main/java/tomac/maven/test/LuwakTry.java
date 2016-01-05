package tomac.maven.test;

import java.io.IOException;
import java.util.Collection;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import uk.co.flax.luwak.InputDocument;
import uk.co.flax.luwak.Matches;
import uk.co.flax.luwak.Monitor;
import uk.co.flax.luwak.MonitorQuery;
import uk.co.flax.luwak.matchers.HighlightingMatcher;
import uk.co.flax.luwak.matchers.HighlightsMatch;
import uk.co.flax.luwak.presearcher.TermFilteredPresearcher;
import uk.co.flax.luwak.queryparsers.LuceneQueryParser;

/**
 *
 * @author Antonio Tomac <antonio.tomac@mediatoolkit.com>
 */
public class LuwakTry {

	private static String preview(HighlightsMatch.Hit hit) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (; i < hit.startOffset; i++) {
			sb.append(" ");
		}
		sb.append("|");
		for (i++; i < hit.endOffset - 1; i++) {
			sb.append("-");
		}
		sb.append("|");
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		Monitor monitor = new Monitor(
				new LuceneQueryParser("text"),
				new TermFilteredPresearcher()
		);

		monitor.update(new MonitorQuery("1", "Foo Bar"));
		monitor.update(new MonitorQuery("2", "foo b*"));
		monitor.update(new MonitorQuery("3", "here bar"));
		String text = "Bla bla here foo bar goes. Barovi";

		InputDocument document = InputDocument.builder("doc_id_1")
				.addField("text", text, new StandardAnalyzer())
				.build();

		Matches<HighlightsMatch> match = monitor.match(
				document, HighlightingMatcher.FACTORY
		);

		Collection<HighlightsMatch> matches = match.getMatches();
		for (HighlightsMatch queryMatch : matches) {
			System.out.println("Matching query id: " + queryMatch.getQueryId());
			Collection<HighlightsMatch.Hit> hits = queryMatch.getHits("text");
			for (HighlightsMatch.Hit hit : hits) {
				System.out.println("\t" + hit.toString());
				System.out.println("\t" + text);
				System.out.println("\t" + preview(hit));
			}
		}
		MonitorQuery query = monitor.getQuery("1");
		System.out.println(query.getQuery());
	}

}
