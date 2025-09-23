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
        property("sonar.coverage.jacoco.xmlReportPaths", "build/jacoco/test.xml")
    }
}

jacoco {
    toolVersion = "0.8.13"
}

checkstyle {
    toolVersion = "10.17.0"
    config = resources.text.fromFile("config/checkstyle/checkstyle.xml")
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        xml.outputLocation.set(file("build/jacoco/test.xml"))
        html.required.set(true)
    }
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}