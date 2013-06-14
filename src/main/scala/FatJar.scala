package com.banno
import sbt._
import Keys._
import sbtassembly._
import sbtassembly.Plugin
import Plugin.AssemblyKeys._

object FatJar {
  val settings: Seq[Project.Setting[_]] = Plugin.assemblySettings ++ Seq(
    packagedArtifacts <<= (packagedArtifacts, assembly, name) map ( (arts, file, n) => arts.updated(Artifact(n, "assembly"), file) ),
    artifacts <+= (name) { n => Artifact(n, "assembly") },
    test in assembly := { }
  )
}
