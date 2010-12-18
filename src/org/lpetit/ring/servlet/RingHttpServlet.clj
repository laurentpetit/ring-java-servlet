(ns org.lpetit.ring.servlet.RingHttpServlet
  (:gen-class
    :extends javax.servlet.http.HttpServlet
    :init initState
    :state state
    :impl-ns org.lpetit.ring.servlet.RingHttpServlet-impl))
