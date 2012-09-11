#Ring java servlet

Allows you to embed ring handlers in existing java web applications without having to add an AOT phase to your build process, by providing you with a generic way to declare servlets in your web.xml, and where the clojure ring handler is located.
It also provides a ServletContextListener which can be configured to run a function when the web app is initialized and destroyed.

##Setup
Add this project as a dependency of your project. By means of transitive dependencies magic, you''ll need to add no other dependency.

ring-java-servlet is distributed on clojars.org as a maven artifact, with following coordinates:

* groupId=org.lpetit.ring
* artifactId=ring-java-servlet
* version=0.2.0 (as of the time where this README was last updated)

ring-java-servlet artifacts are currently only available on clojars.org

##Ring handler example

### Create your Ring handler
Write a clojure ring handler and place it somewhere in your source/resources folder (so that your build system adds it to the webapp classpath, e.g. places it in WEB-INF/classes folder of your webapp) :

    ; file lpetit.ring_test.clj
    (ns lpetit.ring-test)
    (defn hello [req]
      {:status 200
       :headers {"Content-Type" "text/html"}
       :body "Hello world from ring java servlet"})


### Declare the servlet in your web.xml
    <servlet>
            <servlet-name>some-unique-name</servlet-name>
            <servlet-class>org.lpetit.ring.servlet.RingHttpServlet</servlet-class>
            <init-param>
                    <param-name>handler</param-name>
                    <param-value>lpetit.ring-test/hello</param-value>
            </init-param>
    </servlet>
    <servlet-mapping>
            <servlet-name>some-unique-name</servlet-name>
            <url-pattern>*.some-extension</url-pattern>
    </servlet-mapping>

### Test it
That's all. Next time you build your webapp and start it, you should see a "Hello world from ring java servlet" welcome message at http://the-server:the-port/<your-webapp-context>/foo.some-extension

##ServletContextListener example

### Create startup and shutdown functions
Any clojure function with one parameter can be used as the startup or shutdown function. The RingServletContextListener will pass the ServletContext to the function. There is also a small utility function to convert the context parameters to a clojure map.

    ; file lpetit.ring_test.clj
    (ns lpetit.context-test
      (:require [org.lpetit.ring.servlet.util :as util])

    (defn start-up [ctx]
      (println "Starting app with params: " (util/context-params ctx))

    (defn shutdown [ctx]
      (println "Stopping app with params: " (util/context-params ctx))


### Declare the ServletContextListener in your web.xml

Specify which funtions to run at start/stop with the `context-init` and `context-destroy` context parameters. Both parameters are optional.

    <context-param>
       <param-name>context-init</param-name>
       <param-value>lpetit.context-test/start-up</param-value>
    </context-param>
    <context-param>
       <param-name>context-destroy</param-name>
       <param-value>lpetit.context-test/shutdown</param-value>
    </context-param>
    <listener>
       <listener-class>org.lpetit.ring.servlet.RingServletContextListener</listener-class>
    </listener>
