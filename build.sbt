import lmcoursier.internal.shaded.coursier.core.Configuration.provided

ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.12"

lazy val commonSettings = Seq(
  Compile / unmanagedSourceDirectories += baseDirectory.value / "src" / "main" / "kotlin" ,
  Test / unmanagedSourceDirectories += baseDirectory.value / "src" / "test" / "kotlin",
  libraryDependencies ++= Seq(
    "org.slf4j" % "slf4j-api" % "2.0.16",
    "org.projectlombok" % "lombok" % "1.18.34",
    "ch.qos.logback" % "logback-classic" % "1.5.11",
    "org.jetbrains.kotlinx" % "kotlinx-coroutines-core-linuxx64" % "1.9.0"
  )
)

lazy val javaVirtualThreads = (project in file("JavaVirtualThreads"))
  .settings(commonSettings)
  .settings(
    name := "java-virtual-threads"
  )

lazy val kotlinCoroutines = (project in file("KotlinCoroutines"))
  .settings(commonSettings)
  .settings(
    name := "kotlin-coroutines"
  )

lazy val scalaVsKotlin = (project in file("ScalaVsKotlin"))
  .settings(commonSettings)
  .settings(
    name := "scala-vs-kotlin"
  )

lazy val root = (project in file("."))
  .settings(commonSettings)
  .enablePlugins(KotlinPlugin)
  .aggregate(javaVirtualThreads, kotlinCoroutines, scalaVsKotlin)
  .settings(
    name := "rtjvm"
  )

javacOptions ++= Seq("-processor", "lombok.launch.AnnotationProcessorHider$AnnotationProcessor")