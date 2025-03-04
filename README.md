sbt project to follow rtjvm videos - each sub module maps to a video , can run kotlin, scala and java in the one module

rm -rf ~/.sbt/1.0 ~/.ivy2/cache ~/.coursier/cache project/target/ target/
sbt clean reload compile
sbt package
sbt projects

sbt "javaVirtualThreads/runMain Main"
sbt "kotlinCoroutines/runMain Main2"
