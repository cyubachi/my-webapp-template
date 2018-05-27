(ns {{name}}.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clojure.java.io :as io]
            [clojure.data.json :as json]
            [clj-time.core :as t]
            [clj-time.format :as f]
            [ring.middleware.cookies :refer [wrap-cookies]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defn json-response
  "Returns application/json response."
  [body]
  (let [status (:response-status body)]
    {
     :status  (if status
                status
                200)
     :headers {"Content-type" "application/json"}
     :body    (json/write-str body)
     }))



(defroutes app-routes
  (GET "/" _
       {:status  200
        :headers {"Content-Type" "text/html; charset=utf-8"}
        :body    (io/input-stream (io/resource "public/index.html"))})
  (GET "/api/hello" {params :params} (json-response {:success true :message "Hello api"}))
  (route/not-found "Not Found"))

(def dev-app (-> #'app-routes
                 (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))
                 (wrap-cookies)
                 (wrap-reload)))
