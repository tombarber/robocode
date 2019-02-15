# robocode

Installing robocode jars to local maven repo:

1. Download + install robocode from https://sourceforge.net/projects/robocode/files/robocode/1.9.3.0/
Need both: robocode-1.9.3.0-setup.jar and robocode.testing-1.9.3.0-setup.jar
2. Open command prompt and cd to the libs dir
3. Run the following:

mvn install:install-file -Dfile=robocode.jar -DartifactId=robocode  -DgroupId=net.sourceforge.robocode -Dversion=1.9.3.0 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=robocode.testing.jar -DartifactId=robocode.testing -DgroupId=net.sourceforge.robocode  -Dversion=1.9.3.0 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=robocode.battle-1.9.3.0.jar -DartifactId=robocode.battle -DgroupId=net.sourceforge.robocode -Dversion=1.9.3.0 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=robocode.core-1.9.3.0.jar   -DartifactId=robocode.core   -DgroupId=net.sourceforge.robocode -Dversion=1.9.3.0 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=robocode.host-1.9.3.0.jar   -DartifactId=robocode.host   -DgroupId=net.sourceforge.robocode -Dversion=1.9.3.0 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=robocode.repository-1.9.3.0.jar -DartifactId=robocode.repository   -DgroupId=net.sourceforge.robocode -Dversion=1.9.3.0 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=picocontainer-2.14.2.jar -DgroupId=net.sourceforge.robocode -DartifactId=picocontainer -Dversion=2.14.2 -Dpackaging=jar -DgeneratePom=true
