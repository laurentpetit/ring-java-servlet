(ns org.lpetit.ring.servlet.RingHttpServlet-impl
  (:require [ring.util.servlet :as s])
  (:import org.lpetit.ring.servlet.RingHttpServlet))

(defn -initState [] [[] (atom {})])

(defn -init-void [this]
  (let [[n h] (map symbol ((juxt namespace name) (symbol (.getInitParameter this "handler"))))]
    (require n)
    (swap! 
      (.state this)
      assoc
      :service-fn
      (s/make-service-method (ns-resolve (the-ns n) h)))))

(defn -service [this req resp]
  ((-> this .state deref :service-fn) this req resp))

