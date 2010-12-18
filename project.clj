(defproject org.lpetit.ring/ring-java-servlet "0.1.0"
  :description "Ring generic servlet for using ring without AOT."
  :url "http://github.com/laurentpetit/ring-java-servlet"
  :dependencies [[ring/ring-servlet "0.3.5" :exclusions [javax.servlet/servlet-api]]]
  :dev-dependencies [[javax.servlet/servlet-api "2.5"]]
  :aot [org.lpetit.ring.servlet.RingHttpServlet])
