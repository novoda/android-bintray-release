package com.novoda.gradle.release

import com.novoda.gradle.release.rule.TestProjectRule
import org.assertj.core.api.Assertions
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4.class)
class AndroidDifferentGradleVersions {

    @Rule
    public TestProjectRule projectRule = new TestProjectRule(TestProjectRule.Project.ANDROID)

    private GradleRunner runner

    @Before
    void setUp() throws Exception {
        runner = GradleRunner.create()
                .withProjectDir(projectRule.projectDir)
                .withArguments("build", "bintrayUpload", "-PbintrayKey=key", "-PbintrayUser=user")
                .withPluginClasspath()
    }

    /**
     * Android don't support only gradle 4.1 and up with plugin 3.0
     */
    @Test
    void test_withGradle40_shouldFail() {
        runner.withGradleVersion("4.0").buildAndFail()
    }

    @Test
    void test_withGradle41_shouldSucceed() {
        def result = runner.withGradleVersion("4.1").build()

        Assertions.assertThat(result.task(":bintrayUpload").outcome).isEqualTo(TaskOutcome.SUCCESS)
    }

    @Test
    void test_withGradle42_shouldSucceed() {
        def result = runner.withGradleVersion("4.2").build()

        Assertions.assertThat(result.task(":bintrayUpload").outcome).isEqualTo(TaskOutcome.SUCCESS)
    }

    @Test
    void test_withGradle43_shouldSucceed() {
        def result = runner.withGradleVersion("4.3").build()

        Assertions.assertThat(result.task(":bintrayUpload").outcome).isEqualTo(TaskOutcome.SUCCESS)
    }

    @Test
    void test_withGradle44_shouldSucceed() {
        def result = runner.withGradleVersion("4.4").build()

        Assertions.assertThat(result.task(":bintrayUpload").outcome).isEqualTo(TaskOutcome.SUCCESS)
    }

    @Test
    @Ignore("Plugin is currently broken with 4.5. UsageContext have changed...")
    void test_withGradle45_shouldSucceed() {
        def result = runner.withGradleVersion("4.5").build()

        Assertions.assertThat(result.task(":bintrayUpload").outcome).isEqualTo(TaskOutcome.SUCCESS)
    }
}
