FROM openjdk:8-jdk-alpine

COPY target/gpsProject.jar /home/gpsProject.jar

ENTRYPOINT ["sh","-c","java -jar /home/gpsProject.jar com.gabryellr.gpstrack.information.GpsTrackInformationProjectApplication.class"]