(set-env! :resource-paths #{"resources" "src"}
          :source-paths   #{"src"}
          :dependencies   '[#_[org.clojure/clojure "1.8.0"]
                            [org.clojure/clojurescript   "1.9.293"]
                            [adzerk/boot-cljs        "1.7.228-2"]
                            [adzerk/boot-cljs-repl   "0.3.3"] ;; latest release
                            [pandeiro/boot-http      "0.7.6"]
                            [weasel                  "0.7.0"]
                            [com.cemerick/piggieback "0.2.1"]
                            [org.clojure/tools.nrepl "0.2.12"]])

(require '[adzerk.boot-cljs :refer [cljs]]
         '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
         '[pandeiro.boot-http :refer [serve] :as boot-http])

(task-options!
  repl {:middleware '[cemerick.piggieback/wrap-cljs-repl]}
  serve {:port 3000
         :silent true}
  cljs {:optimizations :none})

(deftask dev []
  (comp
    (serve)
    (watch)
    (cljs-repl) ; order is important!!
    (cljs)))

