plugins {
    id("java")
    id("org.sonarqube") version "6.3.1.5724"
    jacoco
    checkstyle
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

sonar {
    properties {
        property("sonar.projectKey", "Textile86_java-project-78")
        property("sonar.organization", "textile86")
    }
}

jacoco {
    toolVersion = "0.8.13"
    reportsDirectory = layout.buildDirectory.dir("customJacocoReportDir")
}

checkstyle {
    toolVersion = "10.17.0"
    config = resources.text.fromString("""
        <?xml version="1.0"?>
        <!DOCTYPE module PUBLIC
                "-//Checkstyle//DTD Checkstyle Configuration 1.3//EN"
                "https://checkstyle.org/dtds/configuration_1_3.dtd">
        <module name="Checker">
            <module name="TreeWalker">
                <module name="AvoidStarImport"/>
                <module name="IllegalImport"/>
                <module name="RedundantImport"/>
                <module name="UnusedImports"/>
            </module>
        </module>
    """.trimIndent())
    isIgnoreFailures = false
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}