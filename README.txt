Recommended setup:
* Java 8
* Tomcat 8
* Maven 3

------------------------------------
1. Prerequisite:

(1) Spring Social ORCID has not yet been hosted in the Central Maven Repository. 
You need to download the Spring Social ORCID project from https://github.com/yucigou/spring-social-orcid
And then generate the jar file and install it in your local Maven repository: mvn clean install

(2) You need to register an ORCID Application. If not yet, apply for one at:
https://orcid.org/content/register-client-application-production-trusted-party, or
https://orcid.org/content/register-client-application-sandbox

Then you'll be given an ORCID application ID together with its secret. 

Note: be sure that you have added your application's URL, for example for development, http://localhost:8080,
to the list of redirect URLs when applying for your ORCID application ID and secret.

(3) You need to put the application ID with its secret in the following Spring properties file:
/src/main/webapp/WEB-INF/spring/social.properties

Accordingly, you need to configure the ORCID API properties in the same file. Please find below the example
social.properties file for ORCID Production API:

orcid.clientId=xxxxxxxxxxxxxxxxxxx
orcid.clientSecret=xxxxxxxxxxxxxxxxxxx

orcid.frontendUrl=https://orcid.org/
orcid.messageSchemaVersion=1.2
orcid.apiUrl=https://api.orcid.org/v${orcid.messageSchemaVersion}/
orcid.pubApiUrl=https://pub.orcid.org/v${orcid.messageSchemaVersion}/
orcid.authorizeUrl=https://orcid.org/oauth/authorize
orcid.accessTokenUrl=https://api.orcid.org/oauth/token

Another example social.properties file for ORCID Sandbox API:

orcid.clientId=xxxxxxxxxxxxxxxxxxx
orcid.clientSecret=xxxxxxxxxxxxxxxxxxx

orcid.frontendUrl=https://sandbox.orcid.org/
orcid.messageSchemaVersion=1.2
orcid.apiUrl=https://api.sandbox.orcid.org/v${orcid.messageSchemaVersion}/
orcid.pubApiUrl=https://pub.sandbox.orcid.org/v${orcid.messageSchemaVersion}/
orcid.authorizeUrl=https://sandbox.orcid.org/oauth/authorize
orcid.accessTokenUrl=https://api.sandbox.orcid.org/oauth/token

------------------------------------
2. Set up your project in your IDE, such as Eclipse. (Optional)

mvn eclipse:eclipse

------------------------------------
3. Generate the war file, i.e., spring-social-orcid-client-1.0.0.war for example

mvn clean install

------------------------------------
4. Run the web application in Tomcat

You can copy the war file to diretory <TOMCAT_HOME>/webapps/, and start Tomcat:
cd <TOMCAT_HOME>/bin
startup.bat

------------------------------------
5. Try your web application with ORCID integrated

Open your browser, and navigate to http://localhost:8080/spring-social-orcid-client-1.0.0 for instance.

Then you can sign into your application with ORCID. You'll be redirected to the ORCID web site to login.
And if successful, you'll be redirected back to your web application, with your ORCID ID and name shown
on the web page. 

##############################################
Obsolete Readme as below
##############################################
------------------------------------
Prerequisite:

You need to have a Facebook App. If not yet, create one at https://developers.facebook.com/quickstarts/?platform=web 
Then you'll be given a Facebook ID with its secret. You should put them in the following configuration file:
/src/main/webapp/WEB-INF/spring/social.properties

Ensure that you provide Facebook the website URL:

1. Go to https://developers.facebook.com/apps/
2. Click your application to go to its Dashboard
3. Click "Choose a Platform"
4. Click "WWW" website
5. Find the section "Tell us about your website" and enter "http://localhost:8080/" and then "Next"

That's it. Now go to http://localhost:8080/<name-of-spring-social-example-app>/, it should work.

------------------------------------
Steps to run the application:

1. Ensure you have Maven installed.
2. Run 'mvn install'
3. Run 'mvn jetty:run'
4. Navigate to localhost:8080

------------------------------------
Other references:

* Facebook scops:
https://developers.facebook.com/docs/facebook-login/permissions/v2.5

------------------------------------
TODOs:

1. As Facebook is blocked in some countries, we should use some other service providers in this application, for example, GitHub.
2.  