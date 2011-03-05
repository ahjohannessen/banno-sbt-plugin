import sbt._

class Project(info: ProjectInfo) extends PluginProject(info) {
  val akkaRepo = "Akka Repo" at "http://akka.io/repository"
  val akkaPlugin = "se.scalablesolutions.akka" % "akka-sbt-plugin" % "1.0"

  val bumRepo    = "Bum Networks Release Repository" at "http://repo.bumnetworks.com/releases"
  val sbtAkkaBivy = "net.evilmonkeylabs" % "sbt-akka-bivy" % "0.2.0"

  val scctRepo = "scct-repo" at "http://mtkopone.github.com/scct/maven-repo/"
  lazy val scctPlugin = "reaktor" % "sbt-scct-for-2.8" % "0.1-SNAPSHOT"

  val sbtIdeaRepo = "sbt-idea-repo" at "http://mpeltonen.github.com/maven/"
  val sbtIdea = "com.github.mpeltonen" % "sbt-idea-plugin" % "0.2.0"

  val t8Repo = "Banno Internal Repo" at "http://10.3.0.26:8081/nexus/content/repositories/snapshots/"
  override def managedStyle = ManagedStyle.Maven

  lazy val publishTo = Resolver.sftp("Banno Maven Repo", "10.3.0.26", "/data/sonatype-work/nexus/storage/snapshots/")
//  lazy val publishTo = t8Repo
//  Credentials.add("Banno Internal Repo", "10.3.0.26:8081", "admin", "")
  override def compileAction = task {None}
}
