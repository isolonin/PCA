# PCA
Phone Call Authorization

HowTo: 
1. Get pca-<version>.jar and config from src/main/resources/application.properties
2. Copy this files to server. For example /user/local/pca
3. Set execution attribute: chmod 755 pca-<version>.jar
4. Edit config file application.properties for setup a channel, asterisk data etc
5. Startup ./pca-<version>.jar
Service will open port defined in application.properties (by default 8080)

For initialize call send GET request to server:port/api/dial?from=<number>&to=<number>. Response will be true if call was answered or false otherwice
