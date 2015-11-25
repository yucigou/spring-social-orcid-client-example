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