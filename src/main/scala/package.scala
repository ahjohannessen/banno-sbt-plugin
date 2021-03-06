package com
import sbt._
import Keys._

package object banno {
  import BannoDependenciesVersionFile._

  val bannoDependencies = SettingKey[Seq[ModuleID]]("banno-dependencies")

  def addBannoDependency(artifactId: String,
                         groupId: String = "com.banno",
                         crossVersion: Boolean = true,
                         snapshotVersion: String = "1-SNAPSHOT",
                         scope: String = "compile"): Seq[Setting[_]] = {

    val bannoDepVersion = SettingKey[String]("%s-version".format(artifactId))
    val bannoDepReleasedVersion = SettingKey[String]("%s-released-version".format(artifactId))
    
    appendSnapshotBannoDependencyVersionToFileIfMissing(artifactId)

    val depVersion = bannoDepVersion <<= (version, bannoDepReleasedVersion) { (v, rv) =>
      if (v.trim.endsWith("SNAPSHOT")) {
        snapshotVersion
      } else {
        rv
      }
    }

    val withDep = bannoDependencies <+= (bannoDepVersion) { (bv) =>
      val dep = if (crossVersion) groupId %% artifactId % bv
                else groupId % artifactId % bv
      dep % scope
    }

    Seq(depVersion, withDep)
  }

  def addBannoDependencies(artifactIds: String*) =
    artifactIds.foldLeft(Seq[Setting[_]]()) { (settings, artifactId) =>
      settings ++ addBannoDependency(artifactId)
    }
}
