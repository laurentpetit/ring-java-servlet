(ns org.lpetit.ring.servlet.util
  (:import [javax.servlet ServletContext]))

(defn require-and-resolve [symbol-str]
  (let [[n h] (map symbol ((juxt namespace name) (symbol symbol-str)))]
    (require n)
    (ns-resolve (the-ns n) h)))

(defn context-params [^ServletContext ctx]
  "Given a servlet context, it returns a map with all the context parameters. Keys are keywords."
  (into {} (for [param (enumeration-seq (.getInitParameterNames ctx))
                 :let [value (.getInitParameter ctx param)]]
             [(keyword param) value])))
