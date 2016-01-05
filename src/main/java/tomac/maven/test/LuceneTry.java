package tomac.maven.test;

import java.io.IOException;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

/**
 *
 * @author Antonio Tomac <antonio.tomac@mediatoolkit.com>
 */
public class LuceneTry {
	public static void main(String[] args) throws IOException {
		Directory directory = new RAMDirectory();
		
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(
				Version.LATEST, 
				new StandardAnalyzer()
		);
		IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
		Document document = new Document();
		document.add(new TextField("text", "bla bla foo bar", Field.Store.YES));
		indexWriter.addDocument(document);
		indexWriter.commit();
		
		IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(directory));
		Query query = new TermQuery(new Term("text", "foo"));
		TopDocs topDocs = indexSearcher.search(query, 1);
		System.out.println("TotalHits: "+topDocs.totalHits);
		for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
			System.out.println(indexSearcher.doc(scoreDoc.doc));
		}
	}
}
