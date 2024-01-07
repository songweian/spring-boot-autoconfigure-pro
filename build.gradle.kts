import org.gradle.api.publish.maven.MavenPublication
import java.util.*

plugins {
	// add maven plugin
	`maven-publish`
	signing
	java
	id("org.springframework.boot") version "3.2.1"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "org.opengear.spring"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.register<Jar>("javadocJar") {
	archiveClassifier.set("javadoc")
	from(tasks.getByName<Javadoc>("javadoc").destinationDir)
}

tasks.register<Jar>("sourcesJar") {
	archiveClassifier.set("sources")
	from(sourceSets["main"].allSource)
}

artifacts {
	archives(tasks.getByName("javadocJar"))
	archives(tasks.getByName("sourcesJar"))
}

signing {
	sign(configurations["archives"])
}


publishing {
	publications {
		create<MavenPublication>("mavenJava") {
			from(components["java"])
			version = project.version.toString()

			artifact(tasks["javadocJar"])
			artifact(tasks["sourcesJar"])

			pom {
				name.set("SpringBoot Autoconfigure pro configuration")
				description.set("SpringBoot Autoconfigure extension for pro configuration")
				url.set("https://spring.opengear.org")

				scm {
					connection.set("scm:git:https://github.com/songweian/spring-boot-autoconfigure-pro")
					developerConnection.set("scm:git:https://github.com/songweian/spring-boot-autoconfigure-pro")
					url.set("https://github.com/songweian/spring-boot-autoconfigure-pro")
				}

				licenses {
					license {
						name.set("The Apache License, Version 2.0")
						url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
					}
				}

				developers {
					developer {
						id.set("weian404")
						name.set("weian404")
						email.set("weian404@gmail.com")
					}
				}
			}
		}
	}

	repositories {
		val ossUsername = findProperty("oss.sonatype.username") as String
		val ossPassword = findProperty("oss.sonatype.password") as String
		maven {
			name = "OSSRH"
			url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
			credentials {
				username = ossUsername
				password = ossPassword
			}
		}

		maven {
			name = "OSSRHSnapshot"
			url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
			credentials {
				username = ossUsername
				password = ossPassword
			}
		}
	}
}

signing {
	val keyId: String = findProperty("org.opengear.signing.keyId") as String
	val password: String = findProperty("org.opengear.signing.password") as String
	val secretKeyBase64: String = findProperty("org.opengear.signing.secretKeyBase64") as String
	val secretKey: String = String(Base64.getDecoder().decode(secretKeyBase64))

	useInMemoryPgpKeys(keyId, secretKey, password)
	sign(publishing.publications["mavenJava"])
}




