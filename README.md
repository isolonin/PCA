# PCA
Phone Call Authorization

HowTo: 
* Get **pca-&lt;version&gt;.jar** and config from src/main/resources/**application.properties**
* Copy this files to server. For example /user/local/pca
* Set execution attribute: **chmod 755 pca-<version&gt;.jar**
* Edit config file **application.properties** for setup a channel, asterisk data etc
* Startup **./pca-<version&gt;.jar**
Service will open port defined in application.properties (by default 8080)

For initialize call send GET request to server:port/api/dial?from=&lt;number&gt;&to=&lt;number&gt;. Response will be **true** if call was answered or **false** otherwise
