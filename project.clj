(defproject org.lpetit.ring/ring-java-servlet "0.2.0"
  :description "Ring generic servlet for using ring without AOT."
  :url "http://github.com/laurentpetit/ring-java-servlet"
  :dependencies [[ring/ring-servlet "1.1.0" :exclusions [javax.servlet/servlet-api]]]
  :profiles {:provided
                 {:dependencies
                  [[javax.servlet/servlet-api "2.5"]]}}
  :aot [org.lpetit.ring.servlet.RingHttpServlet org.lpetit.ring.servlet.RingServletContextListener])
