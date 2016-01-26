(ns hello-clojure.core
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

(def click-results
  (fn [text] (println text))
)

(defn rotate-list
  [list] (concat (rest list) [(first list)])
)

(def color-collection (atom (range 0 50 1)))

(defn cell-component [color]
  [:div {:style {:backgroundColor color :width "10px" :height "10px"}}]
)

(defn hello-world []
  [:div
    [:h1 (:text @app-state)]
    [:input {:type "text"}]
    [:button {:on-click #(swap! color-collection rotate-list)}]
    [:div
      (for [i @color-collection]
        ; (do (println i)
          ^{:key i}[cell-component (str "#23" (mod i 9))]
        ; )
      )
    ]
  ]
)

(. js/window (setInterval #(swap! color-collection rotate-list) 100))

(click-results "jdshkjfhds")

(reagent/render-component [hello-world]
                          (. js/document (getElementById "app")))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
