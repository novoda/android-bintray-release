package com.novoda.gradle.release.artifacts

class PublishExtension {

    String repoName = 'maven'
    String userOrg

    String groupId
    String artifactId

    /**
     *  @deprecated due to conflicts with gradle project.version. replaced by {@link #publishVersion}
     *  https://github.com/novoda/bintray-release/issues/43
     */
    @Deprecated
    String version
    String publishVersion;

    Map<String, String> versionAttributes = [:]

    String[] licences = ['Apache-2.0']

    String uploadName = ''

    /**
     *  @deprecated due to conflicts with gradle project.description. replaced by {@link #desc}
     *  https://github.com/novoda/bintray-release/issues/46
     */
    @Deprecated
    String description
    String desc

    String website = ''
    String issueTracker = ''
    String repository = ''
    boolean autoPublish = true

    String bintrayUser = ''
    String bintrayKey = ''
    boolean dryRun = true

    String[] publications

}
