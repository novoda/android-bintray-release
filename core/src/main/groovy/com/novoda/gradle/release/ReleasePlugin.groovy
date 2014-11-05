package com.novoda.gradle.release

import com.jfrog.bintray.gradle.BintrayPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.maven.MavenPublication

class ReleasePlugin implements Plugin<Project> {

    void apply(Project project) {
        PublishExtension extension = project.extensions.create('publish', PublishExtension)

        project.apply([plugin: 'maven-publish'])
        attachArtifacts(project)

        new BintrayPlugin().apply(project)

        project.afterEvaluate {
            new BintrayConfiguration(extension).configure(project)
        }
    }

    void attachArtifacts(Project project) {
        Artifacts artifacts = project.plugins.hasPlugin('com.android.library') ? new AndroidArtifacts() : new JavaArtifacts()
        project.publishing {
            publications {
                maven(MavenPublication) {
                    groupId project.publish.groupId
                    artifactId project.publish.artifactId
                    version project.publish.version

                    artifacts.all(project).each {
                        delegate.artifact it
                    }

                    from artifacts.from(project)
                }
            }
        }
    }

}
