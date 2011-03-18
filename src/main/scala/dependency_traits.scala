import sbt._
import sbt_akka_bivy._
import reaktor.scct.ScctProject

trait BannoCommonDeps extends BasicScalaProject {
  lazy val jodaTime = "joda-time" % "joda-time" % "1.6"
  lazy val scalajCollection = "org.scalaj" % "scalaj-collection_2.8.0" % "1.0"
}

trait BannoAkkaProject extends AkkaProject {
  lazy val akkaRemote = akkaModule("remote")

  // necessary since we depend on akka-kernel
  // which has it's own dependencies (eg. terrastore-javaclient, aws-java-sdk, et al) that also depend on another version of jackson
  // this causes ivy to do something stupid and choose the wrong transitive dependency
  // this breaks akka-remote actors
  override def ivyXML =
  <dependencies>
    <override org="org.codehaus.jackson" module="jackson-mapper-asl" rev="1.4.3"/>
    <override org="org.codehaus.jackson" module="jackson-core-asl" rev="1.4.3"/>
  </dependencies>
}

trait HueDeps extends BasicScalaProject {
  lazy val htmlunit = "net.sourceforge.htmlunit" % "htmlunit" % "2.8"
  lazy val hue = "be.roam.hue" % "hue" % "1.1"
}

trait ScalaTestDeps extends BasicScalaProject {
  lazy val scalaTest = "org.scalatest" % "scalatest" % "1.3" % "test"
  lazy val awaitility = "com.jayway.awaitility" % "awaitility" % "1.3.1" % "test"
  lazy val scalaAwaitility = "com.jayway.awaitility" % "awaitility-scala" % "1.3.1" % "test"
}

trait SpecsTestDeps extends BasicScalaProject {
  lazy val specs = "org.scala-tools.testing" % "specs_2.8.0" % "1.6.5" % "test"
}

trait LiftDeps extends BasicScalaProject {
  lazy val liftVersion = "2.2"
  lazy val liftWebKit = "net.liftweb" %% "lift-webkit" % liftVersion
  lazy val liftWizard = "net.liftweb" %% "lift-wizard" % liftVersion
  lazy val liftMapper = "net.liftweb" %% "lift-mapper" % liftVersion
  lazy val liftWidgets = "net.liftweb" %% "lift-widgets" % liftVersion
  lazy val liftRecord = "net.liftweb" %% "lift-record" % liftVersion
  lazy val liftSquerylRecord = "net.liftweb" %% "lift-squeryl-record" % liftVersion

  lazy val log4j = "log4j" % "log4j" % "1.2.16"
  lazy val slf4j = "org.slf4j" % "slf4j-log4j12" % "1.6.1"

  lazy val jettyServer = "org.mortbay.jetty" % "jetty" % "6.1.22" % "test"
  lazy val liftTestKit =  "net.liftweb" %% "lift-testkit" % liftVersion % "test"
}