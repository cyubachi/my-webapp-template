(ns leiningen.new.my-webapp
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "my-webapp"))

(defn my-webapp
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' my-webapp project.")
    (->files data
             ["src/clj/{{sanitized}}/core.clj" (render "src/clj/core.clj" data)]
             ["project.clj" (render "project.clj" data)]
             ["README.md" (render "README.md" data)]
             ["resources/public/index.html" (render "resources/public/index.html" data)]
             ["src/cljs/{{sanitized}}/core.cljs" (render "src/cljs/core.cljs" data)]
             ["src/cljs/{{sanitized}}/db.cljs" (render "src/cljs/db.cljs" data)]
             ["src/cljs/{{sanitized}}/config.cljs" (render "src/cljs/config.cljs" data)]
             ["src/cljs/{{sanitized}}/subs.cljs" (render "src/cljs/subs.cljs" data)]
             ["src/cljs/{{sanitized}}/events.cljs" (render "src/cljs/events.cljs" data)]
             ["src/cljs/{{sanitized}}/views.cljs" (render "src/cljs/views.cljs" data)])))
