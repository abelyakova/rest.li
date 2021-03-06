package com.linkedin.pegasus.gradle


import org.gradle.api.Project
import org.gradle.api.file.FileCollection
import org.gradle.api.file.FileTree

import static com.linkedin.pegasus.gradle.PegasusPlugin.IDL_FILE_SUFFIX
import static com.linkedin.pegasus.gradle.PegasusPlugin.SNAPSHOT_FILE_SUFFIX

class SharedFileUtils
{
  static FileTree getSuffixedFiles(Project project, Object baseDir, String suffix)
  {
    return getSuffixedFiles(project, baseDir, [suffix])
  }

  static FileTree getSuffixedFiles(Project project, Object baseDir, Collection<String> suffixes)
  {
    return project.fileTree(dir: baseDir, includes: suffixes.collect{ "**${File.separatorChar}*${it}".toString() });
  }

  static FileCollection getIdlFiles(Project project, Object destinationDirPrefix)
  {
    return getSuffixedFiles(project, project.file(destinationDirPrefix + 'idl'), IDL_FILE_SUFFIX)
  }

  static FileCollection getSnapshotFiles(Project project, Object destinationDirPrefix)
  {
    return getSuffixedFiles(project, project.file(destinationDirPrefix + 'snapshot'), SNAPSHOT_FILE_SUFFIX)
  }
}
