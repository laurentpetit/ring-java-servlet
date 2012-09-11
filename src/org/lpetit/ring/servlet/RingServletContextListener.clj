(ns org.lpetit.ring.servlet.RingServletContextListener
  (:use org.lpetit.ring.servlet.util)
  (:gen-class :implements [javax.servlet.ServletContextListener])
  (:import [javax.servlet ServletContextEvent]))

(defn run [contextEvent symbol-str]
  (let [ctx (.getServletContext contextEvent)]
    (if-let [the-fn (.getInitParameter ctx symbol-str)]
      (let [resolved-fn (require-and-resolve the-fn)]
        (resolved-fn ctx)))))

(defn -contextInitialized [this ^ServletContextEvent contextEvent]
  (run contextEvent "context-init"))

(defn -contextDestroyed [this ^ServletContextEvent contextEvent]
  (run contextEvent "context-destroy"))



