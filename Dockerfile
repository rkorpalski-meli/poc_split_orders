FROM hub.furycloud.io/mercadolibre/java:1.11-runtime-mini

ENV GRADLE_RUN="bootRun"

ENV GRADLE_PACKAGE="bootJar"
ENV GRADLE_PACKAGE_PATH="app/build/libs/*.jar"
ENV GRADLE_OUTPUT_PATH="/output/application.jar"

ENV GRADLE_TEST="test -i jacocoRootReport"
ENV GRADLE_UNIT_TEST="test -i copyJunitXml"

ENV GRADLE_COV_REPORT_UNIT_PATH="build/reports/jacoco/jacocoRootReport/jacocoRootReport.xml"