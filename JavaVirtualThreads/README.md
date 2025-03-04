https://www.youtube.com/watch?v=nnpeH92QpeY

https://rockthejvm.com/articles/the-ultimate-guide-to-java-virtual-threads

`
rm -rf ~/.sbt/1.0 ~/.ivy2/cache ~/.coursier/cache project/target/ target/
sbt clean reload compile
sbt package
sbt projects

sbt "javaVirtualThreads/runMain Main"
sbt "kotlinCoroutines/runMain Main2"
`