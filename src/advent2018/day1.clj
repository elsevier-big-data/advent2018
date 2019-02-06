(ns advent2018.day1
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))


(def problem-input
  (->> "day1.txt"
       (io/resource)
       (slurp)
       (string/trim)
       (string/split-lines)))

(def problem-numbers (map #(Integer/parseInt %) problem-input))

(defn total-numbers
  "docstring"
  [xs]
  (reductions + xs))

(defn count-things
  [xs]
  (reduce (fn [acc x]
            (if (acc x)
              (reduced x)
              (conj acc x)))
          #{}
          xs))

(defn day1
  []
  (count-things (total-numbers (cycle problem-numbers))))
