(ns org.lpetit.ring.servlet.RingHttpServlet-impl
  (:require [ring.util.servlet :as s])
  (:use org.lpetit.ring.servlet.util)
  (:import org.lpetit.ring.servlet.RingHttpServlet))

(defn -initState [] [[] (atom {})])

(defn -init-void [this]
  (let [resolved-fn (require-and-resolve (.getInitParameter this "handler"))]
    (swap!
      (.state this)
      assoc
      :service-fn
      (s/make-service-method resolved-fn))))

(defn -service [this req resp]
  ((-> this .state deref :service-fn) this req resp))

