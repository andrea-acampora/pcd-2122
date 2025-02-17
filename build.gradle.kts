import java.io.ByteArrayOutputStream
plugins {
    // Apply the java-library plugin to add support for Java Library
    java
    `java-library`
}

repositories {
    mavenCentral()
}

sourceSets {
    main {
        java {
            srcDir("src")
        }
    }
}

dependencies {
    // Use JUnit test framework
    testImplementation("junit:junit:4.13.2")
    implementation(files("JPF/jpf-core/build/jpf-classes.jar"))
    /* module 2.2 - async programming with event-loops - vert.x */
    implementation("io.vertx:vertx-core:4.2.6")
    implementation("io.vertx:vertx-web:4.2.6")
    implementation("io.vertx:vertx-web-client:4.2.6")
    /* module 3.1 - akka actors */
    implementation(platform("com.typesafe.akka:akka-bom_2.13:2.6.19"))
    implementation("com.typesafe.akka:akka-actor-typed_2.13")

    implementation("org.slf4j:slf4j-api:1.7.25")
    implementation("org.slf4j:slf4j-jdk14:1.7.36")

    implementation("com.github.javaparser:javaparser-symbol-solver-core:3.24.2")

    implementation("io.reactivex.rxjava3:rxjava:3.1.4")
    implementation("com.rabbitmq:amqp-client:5.14.2")


}
/*
tasks.register("jpfVerify") {
    group = "Verification"
    description = "Run JPF verification with ./gradlew jpfVerify /path/of/your/jpf"
    val stdout = ByteArrayOutputStream()
    val pwd = System.getProperty("user.dir")
    val folder = File(pwd)
    val files = folder.listFiles { a -> !(a.name.startsWith(".") || a.name == "build") }
    // Get all the folders and file of this project and bind them with the docker image
    // NB! .gradle and build should not be included, the compile process should be done with the internal jdk
    val toMount: Array<Any> = files.map { it -> "type=bind,source=${it.absolutePath},target=/home/${it.name}" }
        .flatMap { it ->  listOf("--mount", it) }
        .toTypedArray()
    // Get the container in execution
    exec {
        commandLine("docker", "container", "ps")
        standardOutput = stdout
    }
    // If there isn't the project container, the process should clean the environment (i.e. kill the previous container and starts a new one)
    if(!stdout.toString().contains("jpf_run_${project.name}")) {
        println("Create the jpf container...")
        exec {
            commandLine("docker", "container", "rm", "jpf_run_${project.name}", "--force")
            setIgnoreExitValue(true)
            standardOutput = stdout
        }
        exec {
            commandLine("docker", "run", "--name", "jpf_run_${project.name}",
                *toMount,
                "-d", "gianlucaaguzzi/pcd-jpf:latest", "sleep", "infinity")
            setIgnoreExitValue(true)
            standardOutput = stdout
        }
        println("Done!")
    }
    doFirst {
        if(!properties.containsKey("file")) {
            error("""you have to pass the file that you want to verify. Use: -Pfile="/your/path/file.jpf" """)
        }
    }
    doLast {
        exec { commandLine("docker", "exec", "jpf_run_${project.name}", "./gradlew", "build") }
        exec { commandLine("docker", "exec", "jpf_run_${project.name}", "java", "-jar", "/usr/lib/JPF/jpf-core/build/RunJPF.jar", properties["file"])}
    }
}
*/
