FROM sonarqube:5.6.4

ENV JAVA_PLUGIN_VERSION 4.3.0.7717
RUN curl -sLo /opt/sonarqube/extensions/plugins/sonar-java-plugin-${JAVA_PLUGIN_VERSION}.jar \
  https://sonarsource.bintray.com/Distribution/sonar-java-plugin/sonar-java-plugin-${JAVA_PLUGIN_VERSION}.jar

ENV GIT_PLUGIN_VERSION 1.2
RUN curl -sLo /opt/sonarqube/extensions/plugins/sonar-scm-git-plugin-${GIT_PLUGIN_VERSION}.jar \
  https://sonarsource.bintray.com/Distribution/sonar-scm-git-plugin/sonar-scm-git-plugin-${GIT_PLUGIN_VERSION}.jar

ENV COBERTURA_PLUGIN_VERSION 1.7
RUN curl -sLo /opt/sonarqube/extensions/plugins/sonar-cobertura-plugin-${COBERTURA_PLUGIN_VERSION}.jar \
  https://github.com/SonarQubeCommunity/sonar-cobertura/releases/download/${COBERTURA_PLUGIN_VERSION}/sonar-cobertura-plugin-${COBERTURA_PLUGIN_VERSION}.jar
