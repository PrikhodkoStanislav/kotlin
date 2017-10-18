
apply { plugin("kotlin") }

jvmTarget = "1.6"

dependencies {
    compile(project(":core"))
    compile(project(":compiler:util"))
    compile(project(":compiler:container"))
    compile(project(":compiler:resolution"))
    compile(projectDist(":kotlin-script-runtime"))
    compile(commonDep("io.javaslang","javaslang"))
    compile("com.fasterxml.jackson.core:jackson-core:2.0.2")
    compile("com.fasterxml.jackson.core:jackson-databind:2.0.2")
}

sourceSets {
    "main" { projectDefault() }
    "test" {}
}

