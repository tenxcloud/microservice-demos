# How to add nacos-client-tenxcloud sdk
1、maven install tenxcloud nacos-client sdk to repository
mvn install:install-file -Dfile=tenxcloud-lib/nacos-client-tenxcloud-1.4.2.jar -DgroupId=com.alibaba.nacos -DartifactId=nacos-client-tenxcloud -Dversion=1.4.2 -Dpackaging=jar

# How to build
```
./build.sh
```


'mvn package' can run with different options for docker build:

* -DskipDockerBuild to skip image build
* -DskipDockerTag to skip image tag
* -DskipDockerPush to skip image push
* -DskipDocker to skip any Docker goals

```

build jar package and no docker image

* mvn clean package -Dmaven.test.skip=true -DskipDocker  

build jar package , docker image, push docker image to docker registry

you private registry without authentication

* mvn clean package -Dmaven.test.skip=true

you private registry with authentication

* docker login --username xxx --password hub.docker.com
* mvn clean package -Dmaven.test.skip=true


```


Then you'll get the jar files in the 'target' directory of each sub module.

# How to run
* In your IDE, you can run from Run button on the main class file
* In console, you can use 'java -jar target/xxx.jar