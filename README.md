### Description

`sbt` project to follow rtjvm video tutorial

Each sub module maps to a rtjvm video

Can compile/nun `Kotlin`, `Scala` or `Java` (or all 3) in sub module

```commandline
rm -rf ~/.sbt/1.0 ~/.ivy2/cache ~/.coursier/cache project/target/ target/
sbt clean reload compile
sbt package
sbt projects

sbt "javaVirtualThreads/runMain Main"
sbt "kotlinCoroutines/runMain Main2"
```
