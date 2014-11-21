import scalariform.formatter.preferences._

name := "compare-xml"

version := "1.0"

scalaVersion := "2.11.4"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.1.6" % "test"

libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.0.1"

defaultScalariformSettings

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(AlignSingleLineCaseStatements, false)
  .setPreference(DoubleIndentClassDeclaration, true)
  .setPreference(PreserveDanglingCloseParenthesis, true)