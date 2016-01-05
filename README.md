# mvn-tryout
Dependency tree:
-----------------
```
tomac:maven-test:jar:1.0-SNAPSHOT
[INFO] +- org.apache.lucene:lucene-core:jar:4.10.2:compile
[INFO] +- org.apache.lucene:lucene-analyzers-common:jar:4.10.2:compile
[INFO] +- org.apache.lucene:lucene-highlighter:jar:4.10.2:compile
[INFO] |  +- org.apache.lucene:lucene-memory:jar:4.10.2:compile
[INFO] |  \- org.apache.lucene:lucene-queries:jar:4.10.2:compile
[INFO] +- org.apache.lucene:lucene-queryparser:jar:4.10.2:compile
[INFO] |  \- org.apache.lucene:lucene-sandbox:jar:4.10.2:compile
[INFO] +- org.apache.lucene:lucene-misc:jar:4.10.2:compile
[INFO] \- com.github.flaxsearch:luwak:jar:1.2.0:compile
```
