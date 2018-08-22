scalac -classpath ujson_2.12-0.6.6.jar TestJSONParsing.scala

jar cvfm TestJSONParsing.jar META-INF/MANIFEST.MF scala-library-2.12.6.jar ujson_2.12-0.6.6.jar $(ls *.class)

java -jar TestJSONParsing.jar
