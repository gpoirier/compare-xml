logLevel := Level.Warn

resolvers ++= Seq(
  "Maven repository" at "http://repo1.maven.org/maven2/",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)

addSbtPlugin("com.typesafe.sbt" % "sbt-scalariform" % "1.3.0")