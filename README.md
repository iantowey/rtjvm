rm -rf ~/.sbt/1.0 ~/.ivy2/cache ~/.coursier/cache project/target/ target/
sbt clean reload compile
sbt package
sbt projects

sbt "javaVirtualThreads/runMain Main"
sbt "kotlinCoroutines/runMain Main2"