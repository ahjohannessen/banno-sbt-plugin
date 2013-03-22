package com
import sbt._
import Keys._

package object banno {
  val bannoDependencies = SettingKey[Seq[ModuleID]]("banno-dependencies")
  val bannoDependenciesFileName = "versions-banno-deps.sbt"

  def addBannoDependency(artifactId: String,
                         groupId: String = "com.banno",
                         snapshotVersion: String = "1.0-SNAPSHOT"): Seq[Setting[_]] = {

    val bannoDepVersion = SettingKey[String]("%s-version".format(artifactId))
    val bannoDepReleasedVersion = SettingKey[String]("%s-released-version".format(artifactId))

    val defaultBannoDepReleasedVersion = bannoDepReleasedVersion := "undefined"

    val depVersion = bannoDepVersion <<= (version, bannoDepReleasedVersion) { (v, rv) =>
      if (v.trim.endsWith("SNAPSHOT")) {
        snapshotVersion
      } else {
        rv
      }
    }

    val withDep = bannoDependencies <+= (bannoDepVersion) { (bv) =>
      groupId %% artifactId % bv
    }

    Seq(defaultBannoDepReleasedVersion, depVersion, withDep)
  }

  def addBannoDependencies(artifactIds: String*) =
    artifactIds.foldLeft(Seq[Setting[_]]()) { (settings, artifactId) =>
      settings ++ addBannoDependency(artifactId)
    }
}