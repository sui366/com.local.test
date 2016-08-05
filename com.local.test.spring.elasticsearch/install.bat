SET MAVEN_OPTS= -Xms512M -Xmx512M -XX:PermSize=128M -XX:MaxPermSize=128M -XX:ReservedCodeCacheSize=64M
mvn clean install -Dmaven.test.skip=true 